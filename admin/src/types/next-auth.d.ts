// types/next-auth.d.ts
import NextAuth, { DefaultSession, DefaultUser, JWT } from "next-auth";

// Session 타입 확장
declare module "next-auth" {
  interface Session {
    accessToken: string;
    refreshToken: string; // `refreshToken`을 선택적으로 추가
    error?: string;
  }

  interface User extends DefaultUser {
    id: string;
    name: string;
    accessToken: string;
    refreshToken: string; // `refreshToken`을 선택적으로 추가
    accessTokenExpires: number;
  }
}

// JWT 타입 확장
declare module "next-auth/jwt" {
  interface JWT {
    accessToken: string;
    refreshToken: string;
    accessTokenExpires?: number;
    error?: string;
  }
}