"use client";

import { PageImpl } from "@/lib/types/common/page";
import { SiteListDto } from "@/lib/types/site/site.dto";
import apiClient from "@/lib/axios/api";
import { useState } from "react";
import SearchBox from "@/components/table/searchbox/search-box";
import { searchOptions } from "@/app/(authenticated)/sites/search-options";
import DataTable from "@/components/table/data-table";
import { getColumns } from "@/app/(authenticated)/sites/columns";
import { Button } from "@/components/ui/button";
import { useRouter } from "next/navigation";


const queryFn = async (params: URLSearchParams): Promise<PageImpl<SiteListDto>> => {
  const res = await apiClient.get('/sites', {
    params: params
  });
  return res.data;
}

const queryKey: string = 'get-sites';

export default function SiteListPage() {
  const router = useRouter();
  const [search, setSearch] = useState<Record<string, string>>({
    searchText: "",
    searchFields: ""
  })

  return (
    <>
      <SearchBox searchOptions={searchOptions} onSearch={setSearch} />
      <div>
        <Button onClick={() => router.push('/sites/new')}>Add</Button>
      </div>
      <div className="container mx-auto py-10">
        <DataTable columns={getColumns()} queryKey={queryKey} queryFn={queryFn} search={search} />
      </div>
    </>
  )
}