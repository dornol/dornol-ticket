export interface ApiErrorItem {
  field: string | null;
  message: string | null;
  scope: "GLOBAL" | "FIELD"
}

export interface ApiError {
  message: string | null;
  errors: ApiErrorItem[];
}