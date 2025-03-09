"use client";

import { signOut, useSession } from "next-auth/react";


export default function AuthCheck() {
  const { data: session } = useSession();

  if (session?.error) {
    signOut();
  }

  return (
    <></>
  )
}