"use client";

import {
  ColumnDef,
  flexRender,
  getCoreRowModel,
  getSortedRowModel,
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
import { useState } from "react";
import { keepPreviousData, useQuery } from "@tanstack/react-query";
import { PageImpl } from "@/lib/types/common/page";

interface DataTableProps<TData, TValue> {
  columns: ColumnDef<TData, TValue>[];
  queryKey: string;
  queryFn: (params: URLSearchParams) => Promise<PageImpl<TData>>;
  search: object;
}

export default function DataTable<TData, TValue>({
  columns,
  queryKey,
  queryFn,
  search,
}: DataTableProps<TData, TValue>) {
  const [pagination, setPagination] = useState<PaginationState>({
    pageIndex: 0,
    pageSize: 15,
  });
  const [sorting, setSorting] = useState<SortingState>([]);

  const { data } = useQuery({
    queryKey: [queryKey, pagination, sorting, search],
    queryFn: async () => {
      const params = new URLSearchParams();
      Object.keys(pagination).forEach(key => {
        const typedKey = key as keyof typeof pagination;
        params.append(key, String(pagination[typedKey]));
      });
      Object.keys(search).forEach(key => {
        const typedKey = key as keyof typeof search;
        params.append(key, String(search[typedKey]));
      });
      sorting.forEach((sort) => {
        params.append('sort', `${sort.id},${sort.desc ? 'desc' : 'asc'}`);
      })
      return queryFn(params);
    },
    placeholderData: keepPreviousData,
    throwOnError: true
  });

  const table = useReactTable({
    data: data?.content ?? [],
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
    pageCount: data?.page.totalPages,
  })

  const start = (data?.page.number ?? 0) - (data?.page.number ?? 0) % 10;
  const end = Math.min(start + 10, data?.page.totalPages ?? 1);

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
                    isActive={start + i === data?.page.number}
                  >
                    {start + i + 1}
                  </PaginationLink>
                </PaginationItem>
              ))
            }
            <PaginationItem>
              <PaginationNext href="#" onClick={() => {
                setPagination((old) => ({ ...old, pageIndex: Math.min(end, (data?.page.totalPages ?? 1) - 1) }))
              }} />
            </PaginationItem>
          </PaginationContent>
        </Pagination>
      </div>
    </div>
  )
}