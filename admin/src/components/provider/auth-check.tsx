"use client";

import { ReactNode, useEffect, useState } from "react";
import useAuthStore from "@/lib/store/auth";
import { useRouter } from "next/navigation";
import { useQuery } from "@tanstack/react-query";
import { authService, tokenProvider } from "@/lib/service/auth/auth-service";

export default function AuthCheck({ children }: { children: ReactNode }) {
  const [accessToken, setAccessToken] = useState<string | null>(null)
  const [initialized, setInitialized] = useState(false)
  const { userInfo } = useAuthStore();
  const { data, isSuccess, isError } = useQuery({
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
  }, [initialized, userInfo, isSuccess, isError, accessToken])

  useEffect(() => {
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