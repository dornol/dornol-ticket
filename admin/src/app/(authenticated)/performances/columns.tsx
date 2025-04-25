import { ColumnDef } from "@tanstack/react-table";
import { Button } from "@/components/ui/button";
import SortableHeader from "@/components/table/sortable-header";
import { PerformanceListDto } from "@/lib/types/performance/performance.dto";

export function getColumns(
  onEditClick: (performances: PerformanceListDto) => void,
): ColumnDef<PerformanceListDto>[] {
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
      accessorKey: "type",
      header: (context) => {
        return (
          <SortableHeader context={context}>
            Type
          </SortableHeader>
        )
      },
    },
    {
      accessorKey: "site.name",
      header: (context) => {
        return (
          <SortableHeader context={context}>
            Site
          </SortableHeader>
        )
      },
    },
    {
      id: "site.address.zipCode",
      accessorFn: originalRow => originalRow.site.address.zipCode,
      cell: ({ row }) => `(${row.original.site.address.zipCode}) ${row.original.site.address.mainAddress} ${row.original.site.address.detailAddress}`,
      header: (context) => {
        return (
          <SortableHeader context={context}>
            Site address
          </SortableHeader>
        )
      },
    },
    {
      id: 'actions',
      accessorFn: originalRow => originalRow.id,
      header: "Actions",
      cell: ({ row }) => {
        return (
          <>
            <Button type="button" variant="outline" onClick={() => onEditClick(row.original)}>Edit</Button>
          </>
        )
      },
    },
  ]
}