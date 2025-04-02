import { create } from "zustand";
import { Menus, MenusState, ProjectsMenuItem } from "@/lib/types/menu/menu";
import { menus } from "@/lib/data/menus/menus";

const urlMatch = (url: string, pathname: string) => {
  if (url.indexOf("*") >= 0) {
    return new RegExp(`^${url}$`).test(pathname);
  }
  return url === pathname;
}

const markActiveProjectMenus = (menu: ProjectsMenuItem, pathname: string): boolean => {
  menu.items?.forEach((item) => markActiveProjectMenus(item, pathname));
  menu.isActive = menu.items?.some(item => item.isActive) === true || urlMatch(menu.url, pathname);
  return menu.isActive;
}

const getMenus = (menus: Menus, pathname: string): Menus => {
  menus.projects.forEach((item) => markActiveProjectMenus(item, pathname));
  return {
    ...menus,
    main: menus.main.map((it) => ({...it, isActive: urlMatch(it.url, pathname)})),
    projects: menus.projects,
    secondary: menus.secondary.map((it) => ({...it, isActive: urlMatch(it.url, pathname)}))
  }
}

const useMenusStore = (pathname: string) => create<MenusState>((set) => ({
  menus: getMenus(menus, pathname),
  setMenus: (menus: Menus, pathname: string) => set({ menus: getMenus(menus, pathname) }),
}))();

export default useMenusStore;