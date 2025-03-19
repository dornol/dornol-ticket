"use client";

import useAuthStore from "@/lib/store/auth";

export default function DashboardPage() {
  const { userInfo } = useAuthStore();

  return (
    <>
      DashBoard!!
      <p>data: {JSON.stringify(userInfo)}</p>
    </>
  )
}