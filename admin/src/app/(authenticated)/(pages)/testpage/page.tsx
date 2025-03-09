import getDefaultServerSession from "@/lib/auth/server-session";

export default async function TestPage() {
  // const session = await getDefaultServerSession();

  // fetch(`${process.env.NEXT_PUBLIC_API_URL}/join/exists-username`, {
  //   method: "POST",
  //   headers: {
  //     "Content-Type": "application/json",
  //     Authentication: `Bearer ${session?.user?.accessToken}`
  //   }
  // })

  return (
    <>
      serverSession
      <p>
        {/*{JSON.stringify(session)}*/}
      </p>
    </>
  )
}