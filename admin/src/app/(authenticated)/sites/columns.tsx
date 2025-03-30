import { ColumnDef } from "@tanstack/react-table";
import SortableHeader from "@/components/table/sortable-header";
import { SiteListDto } from "@/lib/types/site/site.dto";

export function getColumns(): ColumnDef<SiteListDto>[] {
  return [
    {
      accessorKey: "name",
      header: (context) => {
        return (
          <SortableHeader context={context}>
            Name
          </SortableHeader>
        )
      },
    },
    {
      accessorKey: "address",
      cell: ({ row }) => {
        return `${row.original.address.mainAddress} ${row.original.address.detailAddress}`
      },
      header: (context) => {
        return (
          <SortableHeader context={context}>
            Address
          </SortableHeader>
        )
      },
    },
  ]
}