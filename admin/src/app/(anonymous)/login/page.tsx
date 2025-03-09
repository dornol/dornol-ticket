"use client";

import { LoginForm } from "@/components/sign-in-form";

export default function LoginPage() {
  return (
    <>
      <section>
        <div className="flex min-h-svh w-full items-center justify-center p-6 md:p-10">
          <div className="w-full max-w-sm">
            <LoginForm/>
          </div>
        </div>
      </section>
    </>
  )
}