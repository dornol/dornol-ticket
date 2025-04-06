"use client";

import { useState } from "react";
import SearchBox from "@/components/table/searchbox/search-box";
import { searchOptions } from "@/app/(authenticated)/sites/search-options";
import DataTable from "@/components/table/data-table";
import { getColumns } from "@/app/(authenticated)/sites/columns";
import { Button } from "@/components/ui/button";
import { useRouter } from "next/navigation";
import siteService from "@/lib/service/site/site-service";
import { SiteListDto } from "@/lib/types/site/site.dto";

const queryKey: string = 'get-sites';

export default function SiteListPage() {
  const router = useRouter();
  const [search, setSearch] = useState<Record<string, string>>({
    searchText: "",
    searchFields: ""
  })

  const onEditClick = (data: SiteListDto) => {
    router.push(`/sites/${data.id}/edit`);
  }

  const onSeatsClick = (data: SiteListDto) => {
    router.push(`/sites/${data.id}/seats`);
  }

  return (
    <>
      <SearchBox searchOptions={searchOptions} onSearch={setSearch} />
      <div>
        <Button onClick={() => router.push('/sites/new')}>Add</Button>
      </div>
      <div className="container mx-auto py-10">
        <DataTable columns={getColumns({onSeatsClick, onEditClick})} queryKey={queryKey} queryFn={siteService.list} search={search} />
      </div>
    </>
  )
}