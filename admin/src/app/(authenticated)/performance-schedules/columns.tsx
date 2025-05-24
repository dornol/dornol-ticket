import { ColumnDef } from "@tanstack/react-table";
import { Button } from "@/components/ui/button";
import SortableHeader from "@/components/table/sortable-header";
import { PerformanceScheduleDto } from "@/lib/types/performance-schedule/performance-schedule.dto";

export function getColumns(
  onEditClick: (schedule: PerformanceScheduleDto) => void,
): ColumnDef<PerformanceScheduleDto>[] {
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
      accessorKey: "date",
      header: "Date"
    },
    {
      accessorKey: "time",
      header: "Time",
      cell: ({row}) => row.original.time?.substring(0, 5)
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