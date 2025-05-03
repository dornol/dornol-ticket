"use client";

import SearchBox from "@/components/table/searchbox/search-box";
import {Button} from "@/components/ui/button";
import DataTable from "@/components/table/data-table";
import {useRouter} from "next/navigation";
import {useState} from "react";
import {PerformanceListDto} from "@/lib/types/performance/performance.dto";
import {getColumns} from "@/app/(authenticated)/performances/columns";
import performanceService from "@/lib/service/performance/performance-service";
import {getSearchOptions} from "@/app/(authenticated)/performances/search-options";

const queryKey = 'performances';

export default function PerformancesPage() {
  const router = useRouter();

  const [search, setSearch] = useState<Record<string, string>>({
    searchText: "",
    searchFields: ""
  })

  const onEditClick = (data: PerformanceListDto) => {
    router.push(`/performances/${data.id}/edit`);
  }

  return (
    <>
      <SearchBox searchOptions={getSearchOptions()} onSearch={setSearch} />
      <div>
        <Button onClick={() => router.push('/performances/new')}>Add</Button>
      </div>
      <div className="container mx-auto py-10">
        <DataTable columns={getColumns(onEditClick)} queryKey={queryKey} queryFn={performanceService.list} search={search} />
      </div>
    </>
  )
}