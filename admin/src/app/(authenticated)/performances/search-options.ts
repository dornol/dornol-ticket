import { SearchOptions } from "@/lib/types/search/search.dto";
import { SiteListDto } from "@/lib/types/site/site.dto";

export const getSearchOptions: (sites: SiteListDto[]) => SearchOptions = (sites) => ({
  searchFields: [
    {
      title: 'Name',
      value: 'NAME',
    },
    {
      title: 'Site name',
      value: 'SITE_NAME',
    },
    {
      title: 'Site address',
      value: 'SITE_ADDRESS',
    },
  ],
  searchOptions: [
    {
      title: "Site",
      name: "siteId",
      type: "select",
      options: sites.map(it => ({
        title: it.name,
        value: it.id
      }))
    },
  ]
})