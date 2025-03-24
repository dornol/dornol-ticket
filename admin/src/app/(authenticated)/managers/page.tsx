"use client";

import { keepPreviousData, useMutation, useQuery } from "@tanstack/react-query";
import apiClient from "@/lib/axios/api";
import DataTable from "@/app/(authenticated)/managers/data-table";
import { getColumns } from "@/app/(authenticated)/managers/columns";
import { ManagerListDto } from "@/lib/types/manager/manager-list.dto";
import { Dialog } from "@/lib/alert/alert";
import { useState } from "react";
import { PaginationState, SortingState } from "@tanstack/react-table";

export default function AdminListPage() {
  const [pagination, setPagination] = useState<PaginationState>({
    pageIndex: 0,
    pageSize: 20,
  });
  const [sorting, setSorting] = useState<SortingState>([]);
  const { data, refetch } = useQuery({
    queryKey: ['get-managers', pagination, sorting],
    queryFn: async () => {
      const params = new URLSearchParams();
      Object.keys(pagination).forEach(key => {
        const typedKey = key as keyof typeof pagination;
        params.append(key, String(pagination[typedKey]));
      });
      sorting.forEach((sort) => {
        console.log(`sorting: `, sort);
        params.append('sort', `${sort.id},${sort.desc ? 'desc' : 'asc'}`);
      })
      const res = await apiClient.get('/managers', {
        params: params
      });
      return res.data;
    },
    placeholderData: keepPreviousData,
    throwOnError: true
  });

  const mutation = useMutation<void, Error, ManagerListDto, unknown>({
    mutationFn: (manager: ManagerListDto) => apiClient.post(`/managers/${manager.id}/approve`),
    onSuccess: () => {
      return refetch();
    },
    onError: async (error) => {
      await Dialog.alert(error.message)
    }
  });

  return (
    <>
      <div className="container mx-auto py-10">
        {
          <DataTable columns={getColumns(mutation.mutate)} data={data?.content ?? {}} page={data?.page ?? {}} pagination={pagination} setPagination={setPagination} sorting={sorting} setSorting={setSorting} />
        }
      </div>
    </>
  )
}