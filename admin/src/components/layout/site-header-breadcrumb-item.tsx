import { BreadcrumbItem, BreadcrumbLink, BreadcrumbPage, BreadcrumbSeparator } from "@/components/ui/breadcrumb";
import { ProjectsMenuItem } from "@/lib/types/menu/menu";
import Link from "next/link";

export default function SiteHeaderBreadcrumbItem({ menu }: {
  menu: ProjectsMenuItem
}) {

  const subMenu = menu.items?.find(it => it.isActive);

  return (
    <>
      <BreadcrumbSeparator />
      <BreadcrumbItem>
        {
          !!subMenu && (
            <BreadcrumbLink href="#" asChild={true}>
              <Link href={menu.url ?? '/'}>
                {menu.title}
              </Link>
            </BreadcrumbLink>
          )
        }
        {
          !subMenu && (
            <BreadcrumbPage>{menu.title}</BreadcrumbPage>
          )
        }
      </BreadcrumbItem>
      {
        subMenu && <SiteHeaderBreadcrumbItem menu={subMenu} />
      }
    </>
  )
}