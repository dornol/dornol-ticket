import { DatabaseIcon, HelpCircleIcon, LayoutDashboardIcon, SearchIcon, SettingsIcon } from "lucide-react";
import { ManagerRole } from "@/lib/types/auth/auth";
import { Menus } from "@/lib/types/menu/menu";

export const menus: Menus = {
  main: [
    {
      title: "Dashboard",
      url: "/",
      icon: LayoutDashboardIcon,
      isActive: false,
    },
  ],
  projects: [
    {
      title: "Managers",
      url: "#",
      icon: DatabaseIcon,
      scopes: [
        ManagerRole.SCOPE_SYSTEM_ADMIN
      ],
      isActive: false,
      items: [
        {
          title: "Managers",
          url: "/managers",
          isActive: false,
        }
      ]
    },
    {
      title: "Sites",
      url: "#",
      icon: DatabaseIcon,
      scopes: [
        ManagerRole.SCOPE_SYSTEM_ADMIN,
        ManagerRole.SCOPE_BUSINESS_ADMIN,
      ],
      isActive: false,
      items: [
        {
          title: "Sites",
          url: "/sites",
          isActive: false,
          items: [
            {
              title: 'Add new site',
              url: '/sites/new',
              isActive: false,
            },
            {
              title: 'Edit site',
              url: '/sites/.*/edit',
              isActive: false,
            }
          ],
        },
        {
          title: "Seats",
          url: "/seats",
          isActive: false,
          items: [],
        }
      ]
    },
  ],
  secondary: [
    {
      title: "Settings",
      url: "#",
      icon: SettingsIcon,
      isActive: false,
    },
    {
      title: "Get Help",
      url: "#",
      icon: HelpCircleIcon,
      isActive: false,
    },
    {
      title: "Search",
      url: "#",
      icon: SearchIcon,
      isActive: false,
    },
  ],
}