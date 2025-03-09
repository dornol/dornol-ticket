"use client";

import { signOut } from "next-auth/react";
import Image from "next/image";

export default function SignOut() {
  return (
    <form
      action={async () => {
        await signOut()
      }}
    >
      <button type="submit"
              className="rounded-full border border-solid border-transparent transition-colors flex items-center justify-center bg-foreground text-background gap-2 hover:bg-[#383838] dark:hover:bg-[#ccc] text-sm sm:text-base h-10 sm:h-12 px-4 sm:px-5">
        <Image
          className="dark:invert"
          src="/vercel.svg"
          alt="Vercel logomark"
          width={20}
          height={20}
        />
        Sign Out
      </button>
    </form>
  )
}