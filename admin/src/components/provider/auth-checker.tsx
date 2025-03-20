"use client";

import { useRouter } from "next/navigation";
import useAuthStore from "@/lib/store/auth";
import { ReactNode, useEffect, useState } from "react";
import { useQuery } from "@tanstack/react-query";
import { authService, tokenProvider } from "@/lib/service/auth/auth-service";

export default function AuthChecker({ children }: { children: ReactNode }) {
  const [accessToken, setAccessToken] = useState<string | null>(null)
  const [initialized, setInitialized] = useState(false)
  const { userInfo } = useAuthStore();
  const { isSuccess, isError } = useQuery({
    queryKey: ['auth-check'],
    queryFn: () => authService.loadUser(),
    enabled: !!accessToken
  });
  const router = useRouter();

  useEffect(() => {
    if (initialized && !accessToken) {
      router.replace('/login');
      return;
    }
    if (isError || (isSuccess && !userInfo)) {
      router.replace('/login');
      return;
    }
  }, [initialized, userInfo, isSuccess, isError, accessToken, router])

  useEffect(() => {
    // tokenProvider.accessToken를 useEffect 밖에서 하면 서버 사이드에서 localStorage 를 찾는 경우가 있음
    setAccessToken(tokenProvider.accessToken)
    setInitialized(true);
  }, []);

  return (
    <>
      {
        !!userInfo && children
      }
    </>
  )
}