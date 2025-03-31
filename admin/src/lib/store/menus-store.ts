import { create } from "zustand";
import { Menus, MenusState, ProjectsMenu, ProjectsMenuItem } from "@/lib/types/menu/menu";
import { menus } from "@/lib/data/menus/menus";

const markActiveProjectMenus = (menu: ProjectsMenuItem, pathname: string): boolean => {
  menu.items?.forEach((item) => markActiveProjectMenus(item, pathname));
  menu.isActive = menu.items?.some(item => item.isActive) === true || menu.url === pathname;
  return menu.isActive;
}

const getMenus = (menus: Menus, pathname: string): Menus => {
  menus.projects.forEach((item) => markActiveProjectMenus(item, pathname));
  return {
    ...menus,
    main: menus.main.map((it) => ({...it, isActive: it.url === pathname})),
    projects: menus.projects,
    secondary: menus.secondary.map((it) => ({...it, isActive: it.url === pathname}))
  }
}

const useMenusStore = (pathname: string) => create<MenusState>((set) => ({
  menus: getMenus(menus, pathname),
  setMenus: (menus: Menus, pathname: string) => set({ menus: getMenus(menus, pathname) }),
}))();

export default useMenusStore;