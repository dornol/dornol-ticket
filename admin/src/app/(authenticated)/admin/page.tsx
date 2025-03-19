"use client";

import useAuthStore from "@/lib/store/auth";

export default function AdminListPage() {
  const { userInfo } = useAuthStore();

  return (
    <>
      관리자 관리..
      <p>data: {JSON.stringify(userInfo)}</p>
    </>
  )
}