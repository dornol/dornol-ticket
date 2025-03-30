import { create } from "zustand";
import { Menus, MenusState } from "@/lib/types/menu/menu";
import { menus } from "@/lib/data/menus/menus";

const getMenus = (menus: Menus, pathname: string): Menus => ({
  ...menus,
  main: menus.main.map((it) => ({...it, isActive: it.url === pathname})),
  projects: menus.projects.map((it) => ({
    ...it,
    isActive: it.url === pathname || it.items.some(sub => sub.subUrls.indexOf(pathname) >= 0),
    items: it.items.map(item => ({
      ...item,
      isActive: item.url === pathname || item.subUrls.indexOf(pathname) >= 0,
    }))
  })),
  secondary: menus.secondary.map((it) => ({...it, isActive: it.url === pathname}))
})

const useMenusStore = (pathname: string) => create<MenusState>((set) => ({
  menus: getMenus(menus, pathname),
  setMenus: (menus: Menus, pathname: string) => set({ menus: getMenus(menus, pathname) }),
}))();

export default useMenusStore;