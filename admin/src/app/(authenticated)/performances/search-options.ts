import {SearchOptions} from "@/lib/types/search/search.dto";

export const getSearchOptions: () => SearchOptions = () => ({
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
  searchOptions: []
})