import { NextAuthOptions } from "next-auth";
import CredentialsProvider from "next-auth/providers/credentials";

const fetchUserData = async (accessToken: string) => {
  console.debug('accessToken:', accessToken);
  return await fetch(`${process.env.NEXT_PUBLIC_API_URL}/user/me`, {
    headers: { "Content-Type": "application/json", Authorization: `Bearer ${accessToken}` },
  });
}

export const authOptions: NextAuthOptions = {
  providers: [
    CredentialsProvider({
      name: "Credentials",
      credentials: {
        username: { label: "Username", type: "text" },
        password: { label: "Password", type: "password" },
      },
      async authorize(credentials) {
        try {
          const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/auth/authenticate`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(credentials),
          });

          const authData = await res.json();

          if (!res.ok) throw new Error(authData.message);

          const userRes = await fetchUserData(authData.accessToken.value)

          const userData = await userRes.json();

          if (!userRes.ok) throw new Error(userData.message);

          return {
            id: userData.userId,
            name: userData.name,
            username: userData.username,
            accessToken: authData.accessToken.value,
            accessTokenExpires: Date.now() + (authData.accessToken.expiresIn * 1000),
            refreshToken: authData.refreshToken.value
          } as any; // JWT or session info
        } catch (error) {
          throw new Error('입력하신 정보와 일치하는 회원이 없습니다.')
        }
      },
    }),
  ],
  callbacks: {
    async jwt({ token, user, trigger, session }) {
      console.debug('trigger: ', trigger);
      console.debug('session?: ', session);

      if (trigger === 'signIn') {
        return {
          ...token,
          name: user.name,
          email: user.email,
          accessToken: user.accessToken,
          accessTokenExpires: user.accessTokenExpires,
          refreshToken: user.refreshToken,
        }
      } else if (Date.now() < token.accessTokenExpires) {
        console.debug('not expired user !!')
        return token;
      } else {
        console.debug('refreshing token !!', token.refreshToken);
        if (!token.refreshToken) {
          throw new TypeError("Missing refresh token");
        }

        try {
          const response = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/auth/refresh`, {
            method: "POST",
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
              accessToken: token.accessToken,
              refreshToken: token.refreshToken,
            }),
            credentials: "include",
            cache: "no-cache"
          });

          const tokensOrError = await response.json();

          console.debug('new tokens:', tokensOrError.refreshToken);

          if (!response.ok) {
            throw tokensOrError;
          }

          const newToken = tokensOrError;

          const userRes = await fetchUserData(newToken.accessToken.value)

          const userData = await userRes.json();

          if (!userRes.ok) throw new Error(userData.message);

          const expiresAt = Date.now() + (newToken.accessToken.expiresIn * 1000);

          return {
            name: userData.name,
            email: userData.email,
            accessToken: newToken.accessToken.value,
            accessTokenExpires: expiresAt,
            refreshToken: newToken.refreshToken.value,
          }
        } catch (error) {
          console.error("Error refreshing access token", error);
          token.error = "RefreshTokenError";
          return token;
        }
      }
    },
    async session({ session, token }) {
      session.accessToken = token.accessToken as string;
      session.accessTokenExpires = token.accessTokenExpires as number;
      session.error = token.error;
      return session;
    },
  },
  pages: {
    signIn: '/login',
    error: '/login'
  },
  session: {
    strategy: "jwt",
  },
  secret: process.env.AUTH_SECRET,
}