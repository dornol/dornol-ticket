import { SearchOptions } from "@/lib/types/search/search.dto";

export const searchOptions: SearchOptions = {
  searchFields: [
    {
      title: 'Name',
      value: 'NAME',
    },
    {
      title: 'ID',
      value: 'USERNAME',
    },
    {
      title: 'Company name',
      value: 'COMPANY_NAME',
    },
    {
      title: 'Phone',
      value: 'PHONE',
    },
    {
      title: 'Email',
      value: 'EMAIL',
    },
  ],
  searchOptions: [
    {
      title: "Approve state",
      name: "approved",
      type: "select",
      options: [
        {
          title: "Approved",
          value: "true",
        },
        {
          title: "Not Approved",
          value: "false"
        }
      ]
    },
    {
      title: "Role",
      name: "managerRole",
      type: "select",
      options: [
        {
          title: "System Admin",
          value: "SYSTEM_ADMIN",
        },
        {
          title: "Business Admin",
          value: "BUSINESS_ADMIN",
        },
      ]
    },
  ]
}