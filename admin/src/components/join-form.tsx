"use client";

import { cn } from "@/lib/utils"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardDescription, CardHeader, CardTitle, } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage, } from "@/components/ui/form"
import { useRouter } from "next/navigation";
import Link from "next/link";

import React, { useState } from "react";
import { useMutation } from "@tanstack/react-query";
import joinService from "@/lib/service/join-service";
import { Dialog } from "@/lib/alert/alert";
import { ApiError } from "@/lib/type/common/api-error";
import { z } from "zod";

import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";

const formSchema = z.object({
  username: z.string()
    .min(4, { message: "아이디는 4글자 이상으로 입력해 주세요.", })
    .max(16, { message: "아이디는 16글자 이내로 입력해 주세요." })
    .regex(/^[a-zA-Z0-9]{4,16}$/, { message: "아이디는 영문 또는 숫자만 가능합니다." })
    .refine(async (username) => {
      return !!username && await joinService.checkUsername(username);
    }, { message: "이미 사용중인 아이디 입니다." }),
  password: z.string()
    .min(4, { message: "비밀번호는 4글자 이상으로 입력해 주세요." })
    .max(32, { message: "비밀번호는 32글자 이내로 입력해 주세요." }),
  name: z.string()
    .min(2, { message: "이름은 2글자 이상으로 입력해 주세요.", })
    .max(10, { message: "이름은 10글자 이내로 입력해 주세요.", })
    .regex(/[가-힣]{2,10}/, { message: "이름은 한글로 입력해 주세요.", }),
  email: z.string()
    .email({ message: "이메을 형식을 확인해 주세요." }),
  phone: z.string()
    .regex(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/, { message: "올바른 전화번호를 입력해 주세요." })
})

export function JoinForm({
  className,
  ...props
}: React.ComponentPropsWithoutRef<"div">) {
  const [error, setError] = useState<ApiError | null>(null)
  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      username: "",
      password: "",
      name: "",
      email: "",
      phone: "",
    },
  });
  const router = useRouter();
  const mutation = useMutation({
    mutationFn: joinService.join,
    onSuccess: async () => {
      await Dialog.alert("가입되었습니다.");
      router.replace('/');
    },
    onError: (error: ApiError) => {
      setError(error)
    }
  })

  const onSubmit = (values: z.infer<typeof formSchema>) => {
    console.log(values)
    mutation.mutate(values);
  }

  return (
    <div className={cn("flex flex-col gap-6", className)} {...props}>
      <Card>
        <CardHeader>
          <CardTitle className="text-2xl">Sign up</CardTitle>
          <CardDescription>
            {
              error
                ? <p className="accent-red-500">{error.message}</p>
                : <p>'Fill out the form below to sign up'</p>
            }
          </CardDescription>
        </CardHeader>
        <CardContent>
          <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8">
              <FormField
                control={form.control}
                name="username"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>ID</FormLabel>
                    <FormControl>
                      <Input placeholder="" {...field} />
                    </FormControl>
                    <FormMessage/>
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="password"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Password</FormLabel>
                    <FormControl>
                      <Input placeholder="" {...field} />
                    </FormControl>
                    <FormMessage/>
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="name"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Name</FormLabel>
                    <FormControl>
                      <Input placeholder="" {...field} />
                    </FormControl>
                    <FormMessage/>
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="email"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Email</FormLabel>
                    <FormControl>
                      <Input placeholder="" {...field} />
                    </FormControl>
                    <FormMessage/>
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="phone"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Phone</FormLabel>
                    <FormControl>
                      <Input placeholder="" {...field} />
                    </FormControl>
                    <FormMessage/>
                  </FormItem>
                )}
              />
              <Button type="submit">Submit</Button>
            </form>
            <div className="mt-4 text-center text-sm">
              Already have an account?{" "}
              <Link href="/login" className="underline underline-offset-4">
                Sign in
              </Link>
            </div>
          </Form>
        </CardContent>
      </Card>
    </div>
  )
}