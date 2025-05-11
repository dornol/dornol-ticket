import { SearchOptions } from "@/lib/types/search/search.dto";

export const searchOptions: SearchOptions = {
  searchFields: [
    {
      title: 'Performance name',
      value: 'PERFORMANCE_NAME',
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
      title: "Performance type",
      name: "performanceType",
      type: "select",
      options: [
        {
          title: "공연",
          value: "PERFORMANCE",
        },
        {
          title: "영화",
          value: "MOVIE"
        },
        {
          title: "콘서트",
          value: "CONCERT"
        },
      ]
    },
    {
      title: "Performance date",
      name: "performanceDate",
      type: "range",
    },
  ]
}