const BASE_URL = import.meta.env.VITE_API_URL || "http://localhost:8080";

// 토큰이 필요 없는 공개 엔드포인트
const publicEndpoints = [
  "/channel/find/all",
  "/channel/find/keyword",
  "/hobby/find/all",
];

export const client = async (endpoint: string, options: RequestInit = {}) => {
  const url = `${BASE_URL}${endpoint}`;
  
  // /auth 경로 중 current-user는 토큰 필요, 공개 엔드포인트는 토큰 불필요
  const isPublicEndpoint = publicEndpoints.some(pe => endpoint.startsWith(pe));
  const requiresAuth = (!endpoint.startsWith("/auth") || endpoint === "/auth/find/current-user") && !isPublicEndpoint;
  const token = requiresAuth ? localStorage.getItem("token") : null;
  
  console.log(`API Request: ${endpoint}, requiresAuth: ${requiresAuth}, hasToken: ${token ? 'Yes' : 'No'}, tokenValue: ${token?.substring(0, 30)}...`);
  
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
