import { ComponentType } from "react";
import { ManagerRole } from "@/lib/types/auth/auth";

export interface MenusState {
  menus: Menus;
  setMenus: (menus: Menus, pathname: string) => void;
}

export interface Menus {
  main: DefaultMenu[];
  projects: ProjectsMenu[]
  secondary: DefaultMenu[]
}

export interface DefaultMenu {
  title: string;
  url: string;
  icon: ComponentType;
  isActive: boolean;
}

export interface ProjectsMenu extends DefaultMenu {
  scopes: ManagerRole[];
  items: ProjectsMenuItem[];
}

export interface ProjectsMenuItem {
  title: string;
  url: string;
  isActive: boolean;
  items?: ProjectsMenuItem[];
}