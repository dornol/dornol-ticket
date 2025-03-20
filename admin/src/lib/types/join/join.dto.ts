export interface JoinRequest {
  name: string;
  email: string;
  phone: string;
  username: string;
  password: string;
}

export interface UsernameCheckResponse {
  available: boolean;
}