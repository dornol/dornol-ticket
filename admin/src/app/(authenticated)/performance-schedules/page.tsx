"use client";

import { useRouter } from "next/navigation";
import { useState } from "react";
import SearchBox from "@/components/table/searchbox/search-box";
import { Button } from "@/components/ui/button";
import DataTable from "@/components/table/data-table";
import { searchOptions } from "@/app/(authenticated)/performance-schedules/search-options";
import performanceScheduleService from "@/lib/service/performance-schedule/performance-schedule-service";
import { getColumns } from "@/app/(authenticated)/performance-schedules/columns";
import { PerformanceScheduleDTO } from "@/lib/types/performance-schedule/performance-schedule.dto";

const queryKey = 'performance-schedules';


export default function Page() {
  const router = useRouter();

  const [search, setSearch] = useState<Record<string, string>>({
    searchText: "",
    searchFields: ""
  })

  const onEditClick = (data: PerformanceScheduleDTO) => {
    router.push(`/performance-schedules/${data.id}/edit`);
  }

  return (
    <>
      <SearchBox searchOptions={searchOptions} onSearch={setSearch} />
      <div>
        <Button onClick={() => router.push('/performances-schedules/new')}>Add</Button>
      </div>
      <div className="container mx-auto py-10">
        <DataTable columns={getColumns(onEditClick)} queryKey={queryKey} queryFn={performanceScheduleService.list}
                   search={search} />
      </div>
    </>
  )
}