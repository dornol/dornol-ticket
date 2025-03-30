import { SearchOptions } from "@/lib/types/search/search.dto";

export const searchOptions: SearchOptions = {
  searchFields: [
    {
      title: 'Name',
      value: 'NAME',
    },
    {
      title: 'Address',
      value: 'ADDRESS',
    },
    {
      title: 'Company name',
      value: 'COMPANY_NAME',
    },
  ],
  searchOptions: []
}