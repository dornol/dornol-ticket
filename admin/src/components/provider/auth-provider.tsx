"use client";

import AuthCheck from "@/app/(authenticated)/auth-check";
import { SessionProvider } from "next-auth/react";

export default function AuthProvider({ children }: { children: React.ReactNode }) {
  return (
    <>
      <SessionProvider>
        <AuthCheck/>
        {children}
      </SessionProvider>
    </>
  )
}