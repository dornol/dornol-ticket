import axios from "axios";

const authApi = axios.create({
  baseURL: process.env.NEXT_PUBLIC_API_URL,
  headers: {
    "Content-Type": "application/json",
    "X-Refresh-Token-Accept": "COOKIE",
  },
  withCredentials: true,
});

authApi.interceptors.response.use(
  (response) => {
    return response;
  },
  async (error) => {
    if (error.config.ignoreResponseReject) {
      return;
    }
    switch (error.response.status) {
      case 400:
        // await Dialog.alert('' + error.response.data.message);
        break;
      case 401:
        break;
      case 403:
        // await Dialog.alert('권한이 없습니다. - auth');
        break;
      case 404:
        // await Dialog.alert('404 ');
        break;
      default:
        // await Dialog.alert('서버 오류 - api');
        break;
    }
    return Promise.reject(error.response.data);
  }
)

export default authApi;