export interface JoinRequest {
  name: string;
  email: string;
  phone: string;
  username: string;
  password: string;
  company: Company
}

export interface Company {
  businessName: string;
  businessNumber: string;
}

export interface UsernameCheckResponse {
  available: boolean;
}