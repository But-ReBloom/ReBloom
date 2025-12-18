const BASE_URL = import.meta.env.VITE_API_URL || "http://localhost:8080";

export const client = async (endpoint: string, options: RequestInit = {}) => {
  const url = `${BASE_URL}${endpoint}`;
  
  // /auth 경로 중 current-user는 토큰 필요
  const requiresAuth = !endpoint.startsWith("/auth") || endpoint === "/auth/find/current-user";
  const token = requiresAuth ? localStorage.getItem("token") : null;
  
  const headers: HeadersInit = {
    "Content-Type": "application/json",
    ...(token ? { Authorization: `Bearer ${token}` } : {}),
    ...(options.headers || {}),
  };

  const config = {
    ...options,
    headers,
  };

  try {
    const response = await fetch(url, config);
    
    if (!response.ok) {
      const errorBody = await response.text();
      throw new Error(`HTTP error! status: ${response.status}, body: ${errorBody}`);
    }
    
    const data = await response.json();
    return data;
  } catch (error: any) {
    // 404 에러는 콘솔에 출력하지 않음 (데이터 없음으로 처리)
    if (!error.message?.includes("status: 404")) {
      console.error("API request failed:", error);
    }
    throw error;
  }
};
