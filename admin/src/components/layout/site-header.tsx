"use client"

import { Separator } from "@/components/ui/separator"
import { SidebarTrigger } from "@/components/ui/sidebar"
import ThemeToggle from "@/components/layout/theme-toggle";
import useMenusStore from "@/lib/store/menus-store";
import { usePathname } from "next/navigation";
import { Breadcrumb, BreadcrumbItem, BreadcrumbList, BreadcrumbPage } from "@/components/ui/breadcrumb";
import { DefaultMenu, Menus, ProjectsMenuItem } from "@/lib/types/menu/menu";
import SiteHeaderBreadcrumbItem from "@/components/layout/site-header-breadcrumb-item";

const getSelectedMenu = (menus: Menus) => {
  return menus.main.find(it => it.isActive) || menus.projects.find(it => it.isActive) || menus.secondary.find(it => it.isActive) || menus.main[0];
}

const getSelectedSubMenu = (menus: Menus) => {
  return menus.projects.find((it) => it.isActive)?.items.find((it) => it.isActive)
}

export function SiteHeader() {
  const pathname = usePathname();
  const { menus } = useMenusStore(pathname);

  const mainMenu: DefaultMenu = getSelectedMenu(menus);
  const subMenu: ProjectsMenuItem | undefined = getSelectedSubMenu(menus);

  return (
    <header
      className="group-has-data-[collapsible=icon]/sidebar-wrapper:h-12 flex h-12 shrink-0 items-center gap-2 border-b transition-[width,height] ease-linear">
      <div className="flex w-full items-center gap-1 px-4 lg:gap-2 lg:px-6">
        <SidebarTrigger className="-ml-1" />
        <ThemeToggle />
        <Separator
          orientation="vertical"
          className="mx-2 data-[orientation=vertical]:h-4"
        />

        <Breadcrumb>
          <BreadcrumbList>
            <BreadcrumbItem>
              <BreadcrumbPage>{mainMenu.title}</BreadcrumbPage>
            </BreadcrumbItem>
            {
              !!subMenu && <SiteHeaderBreadcrumbItem menu={subMenu} />
            }
          </BreadcrumbList>
        </Breadcrumb>
      </div>
    </header>
  )
}
