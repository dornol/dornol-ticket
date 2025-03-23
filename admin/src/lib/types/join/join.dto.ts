export interface JoinRequest {
  name: string;
  email: string;
  phone: string;
  username: string;
  password: string;
  company: CompanyRequest
}

export interface CompanyRequest {
  name: string;
  businessNumber: string;
}

export interface UsernameCheckResponse {
  available: boolean;
}