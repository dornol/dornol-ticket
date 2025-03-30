"use client";

import { useMutation, useQueryClient } from "@tanstack/react-query";
import apiClient from "@/lib/axios/api";
import DataTable from "@/components/table/data-table";
import { getColumns } from "@/app/(authenticated)/managers/columns";
import { ManagerListDto } from "@/lib/types/manager/manager-list.dto";
import { Dialog } from "@/lib/dialog/dialog";
import { PageImpl } from "@/lib/types/common/page";
import { useState } from "react";
import SearchBox from "@/components/table/searchbox/search-box";
import { searchOptions } from "@/app/(authenticated)/managers/search-options";

const queryFn = async (params: URLSearchParams): Promise<PageImpl<ManagerListDto>> => {
  const res = await apiClient.get('/managers', {
    params: params
  });
  return res.data;
}

const queryKey: string = 'get-managers';

export default function AdminListPage() {
  const queryClient = useQueryClient();
  const [search, setSearch] = useState<Record<string, string>>({
    searchText: "",
    searchFields: ""
  })
  const mutation = useMutation<void, Error, ManagerListDto, unknown>({
    mutationFn: (manager: ManagerListDto) => apiClient.post(`/managers/${manager.id}/approve`),
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
      <SearchBox searchOptions={searchOptions} onSearch={setSearch} />
      <div className="container mx-auto py-10">
        <DataTable columns={getColumns(mutation.mutate)} queryKey={queryKey} queryFn={queryFn} search={search} />
      </div>
    </>
  )
}