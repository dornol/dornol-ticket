export interface Token {
  value: string;
  expiresIn: number;
}

export interface TokenBundle {
  accessToken: Token;
  refreshToken?: Token | null;
}

export interface UserInfo {
  user: {
    name: string;
    email: string;
  },
  authorities: ManagerRole[]
}

export interface AuthState {
  userInfo: UserInfo | null;
  setUser: (userInfo: UserInfo) => void;
  clearUser: () => void;
}

export type ManagerRole = "SCOPE_SYSTEM_ADMIN" | "SCOPE_BUSINESS_ADMIN" | "SCOPE_BUSINESS_STAFF";