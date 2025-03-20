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
  authorities: AccessRole[]
}

export interface AuthState {
  userInfo: UserInfo | null;
  setUser: (userInfo: UserInfo) => void;
  clearUser: () => void;
}

export type AccessRole = "SCOPE_SYSTEM" | "SCOPE_MANAGER";