"use client";

import { cn } from "@/lib/utils"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardDescription, CardHeader, CardTitle, } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { useRouter } from "next/navigation";
import Link from "next/link";

import React, { useRef, useState } from "react";
import { useMutation } from "@tanstack/react-query";
import { authService } from "@/lib/service/auth/auth-service";

export function LoginForm({
  className,
  ...props
}: React.ComponentPropsWithoutRef<"div">) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const passwordInput = useRef<HTMLInputElement>(null);

  const router = useRouter();
  const mutation = useMutation({
    mutationFn: async (login: { username: string, password: string }) => await authService.login(login.username, login.password),
    onSuccess: () => {
      router.replace('/');
    },
    onError: () => {
      setPassword("");
      if (passwordInput.current) {
        passwordInput.current.focus();
      }
    }
  })

  return (
    <div className={cn("flex flex-col gap-6", className)} {...props}>
      <Card>
        <CardHeader>
          <CardTitle className="text-2xl">Sign in</CardTitle>
          <CardDescription>
            {
              mutation.isError
                ? <p className="text-red-500">Check your username or password</p>
                : <p>Enter your Username below to login to your account</p>
            }

          </CardDescription>
        </CardHeader>
        <CardContent>
          <form
            action={async () => {
              if (!username && !password) {
                return;
              }
              mutation.mutate({ username: username!, password: password! })
            }}
          >
            <div className="flex flex-col gap-6">
              <div className="grid gap-2">
                <Label htmlFor="username">Username</Label>
                <Input
                  id="username"
                  name="username"
                  type="text"
                  placeholder=""
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                  required
                />
              </div>
              <div className="grid gap-2">
                <div className="flex items-center">
                  <Label htmlFor="password">Password</Label>
                </div>
                <Input
                  id="password" name="password" type="password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  ref={passwordInput}
                  required/>
              </div>
              <Button type="submit" className="w-full">
                Login
              </Button>
            </div>
            <div className="mt-4 text-center text-sm">
              Don&apos;t have an account?{" "}
              <Link href="/join" className="underline underline-offset-4">
                Sign up
              </Link>
            </div>
          </form>
        </CardContent>
      </Card>
    </div>
  )
}