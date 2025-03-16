export async function request<T>(url: string, options?: RequestInit): Promise<T> {
  const baseUrl = process.env.NEXT_PUBLIC_API_URL;
  if (!baseUrl) {
    throw new Error("NEXT_PUBLIC_API_URL is not defined");
  }

  // Trailing slash 처리를 통해 fullUrl 생성
  const fullUrl = `${process.env.NEXT_PUBLIC_API_URL}${url}`

  const response = await fetch(fullUrl, options);

  if (!response.ok) {
    const error = await response.json();
    throw new Error(error.message ?? '오류가 발생했습니다.');
  }

  return (await response.json()) as T;
}
