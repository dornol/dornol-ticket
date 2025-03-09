// types/next-auth.d.ts
import NextAuth, { DefaultSession, DefaultUser, JWT } from "next-auth";

// Session 타입 확장
declare module "next-auth" {
  interface Session extends DefaultSession {
    accessToken: string;
    accessTokenExpires: number;
    error?: string;
    user: User
  }

  interface User extends DefaultUser {
    id: number;
    name: string;
    username: string;
    email: string;
    phone: string;
    accessToken: string;
    accessTokenExpires: number;
    refreshToken: string;
  }
}

// JWT 타입 확장
declare module "next-auth/jwt" {
  interface JWT {
    name: string;
    email: string;
    accessToken: string;
    refreshToken: string;
    accessTokenExpires: number;
    error?: string;
  }
}