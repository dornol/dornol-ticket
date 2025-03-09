'use client'

import { useSession } from "next-auth/react";

export default function DashboardPage() {
  const session = useSession();

  return (
    <>
      DashBoard!!
      {JSON.stringify(session)}
    </>
  )
}