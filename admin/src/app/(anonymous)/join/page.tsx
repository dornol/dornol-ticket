import { JoinForm } from "@/components/join-form";

export default function JoinPage() {
  return (
    <>
      <section>
        <div className="flex min-h-svh w-full items-center justify-center p-6 md:p-10">
          <div className="w-full max-w-sm">
            <JoinForm/>
          </div>
        </div>
      </section>
    </>
  )
}