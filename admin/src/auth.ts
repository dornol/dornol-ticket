import NextAuth from "next-auth";
import CredentialsProvider from "next-auth/providers/credentials";

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
          id: user.id,
          name: user.name,
          accessToken: user.accessToken.value,
          accessTokenExpires: Date.now() + (user.accessToken.expiresIn * 1000),
          refreshToken: user.refreshToken.value
        }; // JWT or session info
      },
    }),
  ],
  callbacks: {
    async jwt({ token, user }) {
      if (user) {
        token.accessToken = user.accessToken;
        token.accessTokenExpires = user.accessTokenExpires;
        token.refreshToken = user.refreshToken;
      }
      console.log(`??? ${Date.now()} ${token.accessTokenExpires}`)
      if (Date.now() > (token.accessTokenExpires ?? 0)) {
        console.log('refreshing....')
        try {
          const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/auth/refresh`, {
            method: "POST",
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify({
              accessToken: token.accessToken,
              refreshToken: token.refreshToken,
            }),
            credentials: "include",
          });

          if (!res.ok) throw new Error("Failed to refresh token");

          const refreshedTokens = await res.json();

          return {
            ...token,
            accessToken: refreshedTokens.accessToken.value,
            accessTokenExpires: Date.now() + (refreshedTokens.accessToken.expiresIn * 1000),
            refreshToken: refreshedTokens.refreshToken.value
          };
        } catch (error) {
          console.log('error....', error)
          // return { ...token, error: "RefreshTokenError" };
          return null;
        }
      }

      return token;
    },
    async session({ session, token }) {
      session.accessToken = token.accessToken;
      session.refreshToken = token.refreshToken;
      return session;
    },
  },
  session: {
    strategy: "jwt",
  },
  secret: process.env.AUTH_SECRET,
});