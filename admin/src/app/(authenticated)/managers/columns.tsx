import { ManagerListDto } from "@/lib/types/manager/manager-list.dto";
import { ColumnDef } from "@tanstack/react-table";
import { Button } from "@/components/ui/button";
import SortableHeader from "@/app/(authenticated)/managers/sortable-header";

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
            이름
          </SortableHeader>
        )
      },
    },
    {
      accessorKey: "email",
      header: (context) => {
        return (
          <SortableHeader context={context}>
            이메일
          </SortableHeader>
        )
      },
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
      id: "businessName",
      accessorFn: originalRow => originalRow.company?.name,
      cell: ({ cell }) => cell.getValue(),
      header: (context) => {
        return (
          <SortableHeader context={context}>
            회사명
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
            사업자 번호
          </SortableHeader>
        )
      },
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