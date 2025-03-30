export interface DefaultSearch {
  searchText: string;
  searchFields: string[];
}

export interface SelectOption {
  title: string;
  value: string;
}

export interface SearchOption {
  title: string;
  name: string;
  type: "select" | "checkbox";
  options?: SelectOption[];
}

export interface SearchResult {
  name: string;
  value: string | boolean;
}

export interface SearchOptions {
  searchFields: SelectOption[];
  searchOptions: SearchOption[];
}