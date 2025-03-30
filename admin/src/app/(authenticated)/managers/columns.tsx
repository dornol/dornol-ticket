import { ManagerListDto } from "@/lib/types/manager/manager-list.dto";
import { ColumnDef } from "@tanstack/react-table";
import { Button } from "@/components/ui/button";
import SortableHeader from "@/components/table/sortable-header";

export function getColumns(
  onApproveClick: (manager: ManagerListDto) => void,
): ColumnDef<ManagerListDto>[] {
  return [
    {
      accessorKey: "username",
      header: (context) => {
        return (
          <SortableHeader context={context}>
            ID
          </SortableHeader>
        )
      },
    },
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
      accessorKey: "email",
      header: (context) => {
        return (
          <SortableHeader context={context}>
            Email
          </SortableHeader>
        )
      },
    },
    {
      accessorKey: "phone",
      header: "Phone"
    },
    {
      accessorKey: "managerRole",
      header: "Role"
    },
    {
      id: "businessName",
      accessorFn: originalRow => originalRow.company?.name,
      cell: ({ cell }) => cell.getValue(),
      header: (context) => {
        return (
          <SortableHeader context={context}>
            Company name
          </SortableHeader>
        )
      },
    },
    {
      id: "businessNumber",
      accessorFn: originalRow => originalRow.company?.businessNumber,
      cell: ({ cell }) => cell.getValue(),
      header: (context) => {
        return (
          <SortableHeader context={context}>
            Business No
          </SortableHeader>
        )
      },
    },
    {
      accessorFn: originalRow => originalRow.approval.approved,
      accessorKey: "approved",
      header: "Approved",
      cell: ({ cell, row }) => {
        return (
          <>
            {
              cell.getValue() && 'Approved'
            }
            {
              !cell.getValue() && <Button size="sm" onClick={() => onApproveClick(row.original)}>
                    Approve
                </Button>
            }
          </>
        )
      }
    },
  ]
}