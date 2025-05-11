import { ColumnDef } from "@tanstack/react-table";
import { Button } from "@/components/ui/button";
import SortableHeader from "@/components/table/sortable-header";
import { PerformanceScheduleDTO } from "@/lib/types/performance-schedule/performance-schedule.dto";

export function getColumns(
  onEditClick: (schedule: PerformanceScheduleDTO) => void,
): ColumnDef<PerformanceScheduleDTO>[] {
  return [
    {
      accessorKey: "performance.name",
      header: (context) => {
        return (
          <SortableHeader context={context}>
            Name
          </SortableHeader>
        )
      },
    },
    {
      accessorKey: "site.name",
      header: "Site"
    },
    {
      accessorKey: "performance.type",
      header: "Type"
    },
    {
      accessorKey: "performanceDate",
      header: "Date"
    },
    {
      accessorKey: "performanceTime",
      header: "Time"
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