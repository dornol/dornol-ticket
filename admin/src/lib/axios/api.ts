import axios from "axios";
import { authService, tokenProvider } from "@/lib/service/auth/auth-service";

const apiClient = axios.create({
  baseURL: process.env.NEXT_PUBLIC_API_URL,
  headers: {
    "Content-Type": "application/json",
  },
  withCredentials: true,
});

// 요청 인터셉터: JWT 자동 추가
apiClient.interceptors.request.use(
  async (config) => {
    if (tokenProvider.expired()) {
      await authService.refreshAccessToken()
        .catch(() => {
          tokenProvider.clearAccessToken();
          window.location.href = '/login';
        });
    }

    const accessToken = tokenProvider.accessToken;
    if (accessToken) {
      config.headers.Authorization = `Bearer ${accessToken}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  });

apiClient.interceptors.response.use(
  (response) => {
    return response;
  },
  async (error) => {
    const originalRequest = error.config;

    switch (error.response?.status) {
      case 400:
        // alert('' + error.response.data.message);
        break;
      case 401:
        if (!originalRequest._retry) {
          originalRequest._retry = true;
          return await authService.refreshAccessToken()
            .then(() => apiClient(originalRequest))
            .catch(() => {
              tokenProvider.clearAccessToken();
              return Promise.reject(error);
            })
        }
        break;
      case 403:
        // alert('권한이 없습니다.');
        break;
      case 404:
        // alert('404 ');
        break;
      default:
        // alert('서버 오류');
        break;
    }

    return Promise.reject(error);
  }
)

export default apiClient;