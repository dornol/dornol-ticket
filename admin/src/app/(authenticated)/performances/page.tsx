"use client";

import SearchBox from "@/components/table/searchbox/search-box";
import { Button } from "@/components/ui/button";
import DataTable from "@/components/table/data-table";
import siteService from "@/lib/service/site/site-service";
import { useRouter } from "next/navigation";
import { useState } from "react";
import { PerformanceListDto } from "@/lib/types/performance/performance.dto";
import { getColumns } from "@/app/(authenticated)/performances/columns";
import performanceService from "@/lib/service/performance/performance-service";
import { getSearchOptions } from "@/app/(authenticated)/performances/search-options";
import { useQuery } from "@tanstack/react-query";

const queryKey = 'performances';

const siteQuery = {
  queryKey: ['site'],
  queryFn: () => {
    const params = new URLSearchParams();
    params.append("pageSize", "1000");
    return siteService.list(params)
  }
}

export default function PerformancesPage() {
  const router = useRouter();

  const {data: sitePage, isSuccess} = useQuery(siteQuery);

  const [search, setSearch] = useState<Record<string, string>>({
    searchText: "",
    searchFields: ""
  })

  const onEditClick = (data: PerformanceListDto) => {
    router.push(`/performances/${data.id}/edit`);
  }

  if (!isSuccess || !sitePage) {
    return null;
  }

  return (
    <>
      <SearchBox searchOptions={getSearchOptions(sitePage.content)} onSearch={setSearch} />
      <div>
        <Button onClick={() => router.push('/performances/new')}>Add</Button>
      </div>
      <div className="container mx-auto py-10">
        <DataTable columns={getColumns(onEditClick)} queryKey={queryKey} queryFn={performanceService.list} search={search} />
      </div>
    </>
  )
}