"use client";

import {
  ColumnDef,
  flexRender,
  getCoreRowModel,
  getSortedRowModel,
  OnChangeFn,
  PaginationState,
  SortingState,
  useReactTable,
} from "@tanstack/react-table"
import {
  Pagination,
  PaginationContent,
  PaginationItem,
  PaginationLink,
  PaginationNext,
  PaginationPrevious,
} from "@/components/ui/pagination"

import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow, } from "@/components/ui/table"

interface Page {
  number: number;
  size: number;
  totalElements: number;
  totalPages: number;
}

interface DataTableProps<TData, TValue> {
  columns: ColumnDef<TData, TValue>[];
  data: TData[],
  page: Page,
  pagination: PaginationState,
  setPagination: OnChangeFn<PaginationState>,
  sorting: SortingState,
  setSorting: OnChangeFn<SortingState>,
}

export default function DataTable<TData, TValue>({
  columns,
  data,
  page,
  pagination,
  setPagination,
  sorting,
  setSorting
}: DataTableProps<TData, TValue>) {
  const table = useReactTable({
    data,
    columns,
    getCoreRowModel: getCoreRowModel(),
    getSortedRowModel: getSortedRowModel(),
    state: {
      sorting,
      pagination
    },
    manualSorting: true,
    onSortingChange: setSorting,
    manualPagination: true,
    onPaginationChange: setPagination,
    pageCount: page.totalPages,
  })

  const start = (page.number) - (page.number) % 10;
  const end = Math.min(start + 10, page.totalPages);

  return (
    <div>
      <div className="rounded-md border">
        <Table>
          <TableHeader>
            {table.getHeaderGroups().map((headerGroup) => (
              <TableRow key={headerGroup.id}>
                {headerGroup.headers.map((header) => {
                  return (
                    <TableHead key={header.id}>
                      {header.isPlaceholder
                        ? null
                        : flexRender(
                          header.column.columnDef.header,
                          header.getContext()
                        )}
                    </TableHead>
                  )
                })}
              </TableRow>
            ))}
          </TableHeader>
          <TableBody>
            {table.getRowModel().rows?.length ? (
              table.getRowModel().rows.map((row) => (
                <TableRow
                  key={row.id}
                  data-state={row.getIsSelected() && "selected"}
                >
                  {row.getVisibleCells().map((cell) => (
                    <TableCell key={cell.id}>
                      {flexRender(cell.column.columnDef.cell, cell.getContext())}
                    </TableCell>
                  ))}
                </TableRow>
              ))
            ) : (
              <TableRow>
                <TableCell colSpan={columns.length} className="h-24 text-center">
                  No results.
                </TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>
      </div>
      <div className="flex items-center justify-end space-x-2 py-4">
        <Pagination>
          <PaginationContent>
            <PaginationItem>
              <PaginationPrevious href="#" onClick={() => {
                setPagination((old) => ({ ...old, pageIndex: Math.max(start - 1, 0) }))
              }}
              />
            </PaginationItem>
            {
              Array.from({ length: end - start }, (_, i) => (
                <PaginationItem key={start + i}>
                  <PaginationLink
                    href="#"
                    onClick={() => setPagination((old) => ({ ...old, pageIndex: start + i }))}
                    isActive={start + i === page.number}
                  >
                    {start + i + 1}
                  </PaginationLink>
                </PaginationItem>
              ))
            }
            <PaginationItem>
              <PaginationNext href="#" onClick={() => {
                setPagination((old) => ({ ...old, pageIndex: Math.min(end, page.totalPages - 1) }))
              }} />
            </PaginationItem>
          </PaginationContent>
        </Pagination>
      </div>
    </div>
  )
}