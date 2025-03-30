"use client"

import * as React from "react"
import { useEffect } from "react"
import { ArrowUpCircleIcon, } from "lucide-react"
import { NavMain } from "@/components/layout/nav-main"
import { NavSecondary } from "@/components/layout/nav-secondary"
import { NavUser } from "@/components/layout/nav-user"
import {
  Sidebar,
  SidebarContent,
  SidebarFooter,
  SidebarHeader,
  SidebarMenu,
  SidebarMenuButton,
  SidebarMenuItem,
} from "@/components/ui/sidebar"
import useAuthStore from "@/lib/store/auth-store";
import { NavProjects } from "@/components/layout/nav-projects";
import { usePathname } from "next/navigation";
import useMenusStore from "@/lib/store/menus-store";

export function AppSidebar({ ...props }: React.ComponentProps<typeof Sidebar>) {
  const pathname = usePathname();
  const { userInfo } = useAuthStore();
  const { menus, setMenus } = useMenusStore(pathname);

  useEffect(() => {
    setMenus(menus, pathname);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [pathname]);

  return (
    <Sidebar collapsible="offcanvas" {...props}>
      <SidebarHeader>
        <SidebarMenu>
          <SidebarMenuItem>
            <SidebarMenuButton
              asChild
              className="data-[slot=sidebar-menu-button]:!p-1.5"
            >
              <a href="#">
                <ArrowUpCircleIcon className="h-5 w-5" />
                <span className="text-base font-semibold">Dornol Ticket Admin</span>
              </a>
            </SidebarMenuButton>
          </SidebarMenuItem>
        </SidebarMenu>
      </SidebarHeader>
      <SidebarContent>
        <NavMain items={menus.main} />
        <NavProjects title={'projects'} items={menus.projects} />
        <NavSecondary items={menus.secondary} className="mt-auto" />
      </SidebarContent>
      <SidebarFooter>
        <NavUser user={userInfo!} />
      </SidebarFooter>
    </Sidebar>
  )
}
