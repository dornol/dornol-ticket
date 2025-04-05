import { ColumnDef } from "@tanstack/react-table";
import SortableHeader from "@/components/table/sortable-header";
import { SiteListDto } from "@/lib/types/site/site.dto";
import { Button } from "@/components/ui/button";

export function getColumns({
  onEditClick
}: {
  onEditClick: (site: SiteListDto) => void;
}): ColumnDef<SiteListDto>[] {
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
        return `(${row.original.address.zipCode}) ${row.original.address.mainAddress} ${row.original.address.detailAddress}`
      },
      header: (context) => {
        return (
          <SortableHeader context={context}>
            Address
          </SortableHeader>
        )
      },
    },
    {
      id: 'actions',
      accessorFn: originalRow => originalRow.id,
      cell: ({ row }) => {
        return <Button type="button" variant="outline" onClick={() => onEditClick(row.original)}>Edit</Button>
      },
      header: 'Actions',
    },
  ]
}