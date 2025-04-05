import auth from "@/lib/axios/auth";
import api from "@/lib/axios/api";
import useAuthStore from "@/lib/store/auth-store";
import { Token, TokenBundle, UserInfo } from "@/lib/types/auth/auth";
import { LoginRequestDto } from "@/lib/types/login/login.dto";

const ACCESS_TOKEN_NAME: string = 'accessToken';
const ACCESS_TOKEN_EXPIRES_NAME: string = 'accessTokenExpiresAt';
const LOGIN_URL: string = '/auth/authenticate';
const AUTHENTICATION_REFRESH_URL: string = '/auth/refresh';


class TokenProvider {

  set accessToken(accessToken: Token) {
    localStorage.setItem(ACCESS_TOKEN_NAME, accessToken.value);
    localStorage.setItem(ACCESS_TOKEN_EXPIRES_NAME, (Date.now() + (accessToken.expiresIn * 1000)).toString())
  }

  get accessToken(): string | null {
    return localStorage.getItem(ACCESS_TOKEN_NAME);
  }

  clearAccessToken(): void {
    localStorage.removeItem(ACCESS_TOKEN_NAME);
  }

  expired(): boolean {
    const expiresAt = Number(localStorage.getItem(ACCESS_TOKEN_EXPIRES_NAME) ?? "0");
    return Date.now() >= expiresAt;
  }

}

const tokenProvider = new TokenProvider();

const logout = async (): Promise<void> => {
  tokenProvider.clearAccessToken();
  useAuthStore.getState().clearUser();
}

let refreshPromise: Promise<void> | null = null;

const authService = {

  login: async function ({username, password}: LoginRequestDto): Promise<TokenBundle> {
    return auth.post(LOGIN_URL, {
      username, password
    }).then(response => response.data);
  },

  logout: async (): Promise<void> => {
    return logout();
  },

  loadUser: async (): Promise<UserInfo | null> => {
    if (!tokenProvider.accessToken) {
      return Promise.resolve(null);
    }

    return api.get('/user/me')
      .then(response => {
        useAuthStore.getState().setUser(response.data);
        return response.data;
      })
      .catch(async (e: Error) => {
        console.error(e);
        await logout();
        return null;
      });
  },

  refreshAccessToken: async (): Promise<void> => {
    if (refreshPromise) {
      return refreshPromise; // 이미 refresh 중이면 기존 Promise 반환
    }

    refreshPromise = (async () => {
      try {
        const accessToken = tokenProvider.accessToken;
        if (!accessToken) {
          throw new Error("accessToken is missing");
        }

        const response = await auth.post(AUTHENTICATION_REFRESH_URL, {
          accessToken,
        });

        const tokenBundle: TokenBundle = response.data;
        tokenProvider.accessToken = tokenBundle.accessToken;
      } catch (error) {
        console.debug("Failed to refresh token:", error);
        await logout();
      } finally {
        refreshPromise = null; // 실행 완료 후 락 해제
      }
    })();

    return refreshPromise;
  },
};

export {
  authService,
  tokenProvider
};
export type { UserInfo };
