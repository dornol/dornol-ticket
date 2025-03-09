import { NextAuthOptions } from "next-auth";
import CredentialsProvider from "next-auth/providers/credentials";

export const authOptions: NextAuthOptions = {
  providers: [
    CredentialsProvider({
      name: "Credentials",
      credentials: {
        username: { label: "Username", type: "text" },
        password: { label: "Password", type: "password" },
      },
      async authorize(credentials) {
        const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/auth/authenticate`, {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(credentials),
        });

        const user = await res.json();

        console.log('user', user)

        if (!res.ok) throw new Error(user.message);
        // return user;
        return {
          id: user.userId,
          name: user.name,
          accessToken: user.accessToken.value,
          accessTokenExpires: Date.now() + (user.accessToken.expiresIn * 1000),
          refreshToken: user.refreshToken.value
        } as any; // JWT or session info
      },
    }),
  ],
  callbacks: {
    async jwt({ token, user, trigger, session }) {
      console.log('trigger: ', trigger);
      console.log('session?: ', session);

      if (trigger === 'signIn') {
        console.log('new user !!')
        return {
          ...token,
          userId: user.id,
          accessToken: user.accessToken,
          accessTokenExpires: user.accessTokenExpires,
          refreshToken: user.refreshToken,

        }
      } else if (Date.now() < token.accessTokenExpires) {
        console.log('not expired user !!')
        return token;
      } else {
        console.log('refreshing token !!', token.refreshToken);
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

          console.log('new tokens:', tokensOrError.refreshToken);

          if (!response.ok) {
            throw tokensOrError;
          }

          const newToken = tokensOrError;

          const expiresAt = Date.now() + (newToken.accessToken.expiresIn * 1000);
          return {
            userId: token.userId,
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
      // console.log('session... token:', token.refreshToken, token.userId);
      // console.log('session... user: ', user)
      // console.log('session... trigger: ', trigger)
      // console.log('session... newSession: ', newSession)

      session.accessToken = token.accessToken as string;
      session.accessTokenExpires = token.accessTokenExpires as number;
      // session.refreshToken = token.refreshToken as string;
      session.error = token.error;
      session.user = {
        id: token.userId as string,
        accessToken: token.accessToken as string,
        accessTokenExpires: token.accessTokenExpires as number,
        refreshToken: token.refreshToken,
      }

      return session;
    },
  },
  pages: {
    signIn: '/login'
  },
  session: {
    strategy: "jwt",
    // updateAge: 0,
  },
  secret: process.env.AUTH_SECRET,
}