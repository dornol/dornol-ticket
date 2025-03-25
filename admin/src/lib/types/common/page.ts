export interface Pagination {
  pageIndex: number;
  pageSize: number;
}

export interface Page {
  number: number;
  size: number;
  totalElements: number;
  totalPages: number;
}

export interface PageImpl<TData> {
  content: TData[];
  page: Page;
}