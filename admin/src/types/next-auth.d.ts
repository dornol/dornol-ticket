// types/next-auth.d.ts
import NextAuth, { DefaultSession, DefaultUser, JWT } from "next-auth";

// Session 타입 확장
declare module "next-auth" {
  interface Session extends DefaultSession {
    accessToken: string;
    refreshToken: string; // `refreshToken`을 선택적으로 추가
    accessTokenExpires: number;
    error?: string;
    user: User
  }

  interface User extends DefaultUser {
    accessToken: string;
    refreshToken: string; // `refreshToken`을 선택적으로 추가
    accessTokenExpires: number;
  }
}

// JWT 타입 확장
declare module "next-auth/jwt" {
  interface JWT {
    userId: string;
    accessToken: string;
    refreshToken: string;
    accessTokenExpires: number;
    error?: string;
  }
}