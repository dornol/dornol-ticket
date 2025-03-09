"use client";

import { signOut, useSession } from "next-auth/react";
import { ReactNode } from "react";

export default function Layout({children}: {children: ReactNode}) {
  // const session = useSession();
  //
  // if (!!session.data?.error) {
  //   signOut();
  //   return <></>;
  // }

  return (
    <>
      {children}
    </>
  )
}