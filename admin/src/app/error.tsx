"use client";

import { useEffect } from "react";
import { useRouter } from "next/navigation";

type ErrorProps = {
  error: unknown;
  reset: () => void;
};

export default function GlobalError({ error }: ErrorProps) {
  const router = useRouter();
  useEffect(() => {
    router.replace('/');
    console.log(error)
  }, [error]);

  return (
    <>
      error....
    </>
  )
}