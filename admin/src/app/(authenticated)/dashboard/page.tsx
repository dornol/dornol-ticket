"use client";

import { useQuery } from "@tanstack/react-query";
import apiClient from "@/lib/axios/api";

export default function DashboardPage() {
  const { data } = useQuery({
    queryKey: ['userInfo'],
    queryFn: () => apiClient.get('/user/me')
  });


  return (
    <>
      DashBoard!!
      <p>data: {JSON.stringify(data)}</p>
    </>
  )
}