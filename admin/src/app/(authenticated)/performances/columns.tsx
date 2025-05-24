import { ColumnDef } from "@tanstack/react-table";
import { Button } from "@/components/ui/button";
import SortableHeader from "@/components/table/sortable-header";
import { PerformanceListDto } from "@/lib/types/performance/performance.dto";

export function getColumns(
  onEditClick: (performance: PerformanceListDto) => void,
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