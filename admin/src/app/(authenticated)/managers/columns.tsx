import { ManagerListDto } from "@/lib/types/manager/manager-list.dto";
import { ColumnDef } from "@tanstack/react-table";
import { Button } from "@/components/ui/button";
import { ArrowUpDown } from "lucide-react";

export function getColumns(
  onApproveClick: (manager: ManagerListDto) => void,
): ColumnDef<ManagerListDto>[] {
  return [
    {
      accessorKey: "username",
      header: ({ column }) => {
        return (
          <Button
            variant="ghost"
            onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
          >
            ID
            <ArrowUpDown className="ml-2 h-4 w-4" />
          </Button>
        )
      },
    },
    {
      accessorKey: "name",
      header: "이름"
    },
    {
      accessorKey: "email",
      header: "이메일"
    },
    {
      accessorKey: "phone",
      header: "전화번호"
    },
    {
      accessorKey: "managerRole",
      header: "권한"
    },
    {
      accessorFn: originalRow => originalRow.company?.name,
      header: "회사명",
      cell: ({ cell }) => cell.getValue()
    },
    {
      accessorFn: originalRow => originalRow.company?.businessNumber,
      header: "사업자번호",
      cell: ({ cell }) => cell.getValue()
    },
    {
      accessorFn: originalRow => originalRow.approval.approved,
      accessorKey: "approved",
      header: "승인",
      cell: ({ cell, row }) => {
        return (
          <>
            {
              cell.getValue() && '승인'
            }
            {
              !cell.getValue() && <Button onClick={() => onApproveClick(row.original)}>
                    승인하기
                </Button>
            }
          </>
        )
      }
    },
  ]
}