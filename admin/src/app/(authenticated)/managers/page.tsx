"use client";

import { useMutation, useQueryClient } from "@tanstack/react-query";
import apiClient from "@/lib/axios/api";
import DataTable from "@/components/table/data-table";
import { getColumns } from "@/app/(authenticated)/managers/columns";
import { ManagerListDto } from "@/lib/types/manager/manager-list.dto";
import { Dialog } from "@/lib/dialog/dialog";
import { PageImpl } from "@/lib/types/common/page";

const queryFn = async (params: URLSearchParams): Promise<PageImpl<ManagerListDto>> => {
  const res = await apiClient.get('/managers', {
    params: params
  });
  return res.data;
}

const queryKey: string = 'get-managers';

export default function AdminListPage() {
  const queryClient = useQueryClient();
  const mutation = useMutation<void, Error, ManagerListDto, unknown>({
    mutationFn: (manager: ManagerListDto) => {
      console.log('manager', manager)
      return apiClient.post(`/managers/${manager.id}/approve`)
    },
    onSuccess: () => {
      queryClient.refetchQueries({
        queryKey: [queryKey],
        type: 'active'
      })
    },
    onError: async (error) => {
      await Dialog.alert(error.message)
    }
  });

  return (
    <>
      <div className="container mx-auto py-10">
        {
          <DataTable columns={getColumns(mutation.mutate)} queryKey={queryKey} queryFn={queryFn} />
        }
      </div>
    </>
  )
}