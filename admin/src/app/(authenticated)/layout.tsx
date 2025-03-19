import { ReactNode } from "react";

import { AppSidebar } from "@/components/layout/app-sidebar"
import { SidebarInset, SidebarProvider, } from "@/components/ui/sidebar"
import AuthCheck from "@/components/provider/auth-check";
import { SiteHeader } from "@/components/layout/site-header";

export default async function Layout({ children }: { children: ReactNode }) {
  return (
    <>
      <AuthCheck>
        <SidebarProvider>
          <AppSidebar variant="inset" />
          <SidebarInset>
            <SiteHeader />
            <div className="flex flex-1 flex-col">
              <div className="@container/main flex flex-1 flex-col gap-2">
                <div className="flex flex-col gap-4 py-4 md:gap-6 md:py-6">
                  <div className="px-4 lg:px-6">
                    {children}
                  </div>
                </div>
              </div>
            </div>
          </SidebarInset>
        </SidebarProvider>
      </AuthCheck>
    </>
  )
}