import NextAuth from "next-auth";
import CredentialsProvider from "next-auth/providers/credentials";
import { randomUUID } from "node:crypto";

export const { handlers, signIn, signOut, auth } = NextAuth({
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
          headers: { "Content-Type": "application/json", "X-Refresh-Token-Accept": "BODY" },
          body: JSON.stringify(credentials),
        });

        const user = await res.json();

        console.log('user', user)

        if (!res.ok) throw new Error(user.message);

        return {
          // id: user.id,
          name: user.name,
          accessToken: user.accessToken.value,
          accessTokenExpires: Date.now() + (user.accessToken.expiresIn * 1000),
          refreshToken: user.refreshToken.value
        }; // JWT or session info
      },
    }),
  ],
  callbacks: {
    async jwt({ token, user, account, profile, trigger }) {
      console.log('trigger: ', trigger);
      console.log('profile: ', profile);


      if (trigger === 'signIn') {
        console.log('new user !!')
        return {
          ...token,
          // id: user.id,
          name: user.name,
          accessToken: user.accessToken,
          accessTokenExpires: user.accessTokenExpires,
          refreshToken: user.refreshToken,
          exp: Math.floor(user.accessTokenExpires / 1000)

        }
      } else if (Date.now() < token.accessTokenExpires) {
        console.log('not expired user !!')
        return token;
      } else {
        console.log('refreshing token !!', token);
        console.log('refreshing user !!', user);
        console.log('refreshing account !!', account);
        if (!token.refreshToken) {
          throw new TypeError("Missing refresh token");
        }

        try {
          const response = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/auth/refresh`, {
            method: "POST",
            headers: {
              'Content-Type': 'application/json',
              'X-Refresh-Token-Accept': 'BODY',
            },
            body: JSON.stringify({
              accessToken: token.accessToken,
              refreshToken: token.refreshToken,
            }),
            credentials: "include",
            cache: "no-cache"
          });

          const tokensOrError = await response.json();

          console.log('new tokens:', tokensOrError);

          if (!response.ok) {
            throw tokensOrError;
          }

          const newToken = tokensOrError;

          const expiresAt = Date.now() + (newToken.accessToken.expiresIn * 1000);
          return {
            // id: token.id,
            accessToken: newToken.accessToken.value,
            accessTokenExpires: expiresAt,
            refreshToken: newToken.refreshToken.value,
            temp: randomUUID().toString(),
            exp: Math.floor(expiresAt / 1000)
          }
        } catch (error) {
          console.error("Error refreshing access token", error);
          token.error = "RefreshTokenError";
          return token;
        }
      }
    },
    async session({ session, token, user, trigger, newSession }) {
      console.log('session... token:', token);
      // console.log('session... user: ', user)
      // console.log('session... trigger: ', trigger)
      // console.log('session... newSession: ', newSession)

      // session.accessToken = token.accessToken as string;
      // session.accessTokenExpires = token.accessTokenExpires as number;
      // session.refreshToken = token.refreshToken as string;
      // session.user.id = token.id;
      // session.user.name = token.name;
      session.error = token.error;

      return session;
      // return {
      //   ...session,
      //   accessToken: token.accessToken,
      //   accessTokenExpires: token.accessTokenExpires,
      //   refreshToken: token.refreshToken,
      //   error: token.error
      // };
    },
    authorized: async ({auth}) => {
      console.log('@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@auth: ', auth);
      return !!auth;
    },
  },
  pages: {
    signIn: '/login'
  },
  session: {
    strategy: "jwt",
    // maxAge: 10,
    updateAge: 0,

  },
  secret: process.env.AUTH_SECRET,
});