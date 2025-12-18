import { useEffect, useRef } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import { authApi } from "../../api/auth";
import { hobbyApi } from "../../api/hobby";
import { toast } from "react-toastify";
import LoadingPage from "../loadingpage/loading";

export default function GoogleCallback() {
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();
  const processedRef = useRef(false);

  // 저장된 취향 테스트 점수 처리
  const processPendingHobbyTest = async () => {
    const pendingTest = localStorage.getItem("pendingHobbyTest");
    if (pendingTest) {
      try {
        const finalAverage = JSON.parse(pendingTest);
        const response = await hobbyApi.recommendHobby(finalAverage);
        localStorage.removeItem("pendingHobbyTest");
        
        if (response.success) {
          navigate("/test/result", {
            state: {
              type: "HobbyTest",
              finalAverage,
              result: response.data,
            },
          });
          return true;
        }
      } catch (error) {
        console.error("Failed to process pending hobby test", error);
        localStorage.removeItem("pendingHobbyTest");
      }
    }
    return false;
  };

  useEffect(() => {
    const code = searchParams.get("code");

    if (code) {
      if (processedRef.current) return;
      processedRef.current = true;

      const login = async () => {
        try {
          const redirectUri = `${window.location.origin}/auth/google/callback`;
          const response = await authApi.googleLogin({
            authorizationCode: code,
            redirectUri,
          });

          if (response.success) {
            // Store token if needed
            localStorage.setItem('token', response.data.accessToken);
            localStorage.setItem('userName', response.data.name);
            localStorage.setItem('userEmail', response.data.email);
            toast.success(`환영합니다, ${response.data.name}님!`);
            
            // 저장된 취향 테스트가 있으면 처리
            const processed = await processPendingHobbyTest();
            if (!processed) {
              navigate("/", { state: { id: response.data.name } });
            }
          } else {
            toast.error("구글 로그인 실패");
            navigate("/login");
          }
        } catch (error) {
          console.error(error);
          toast.error("서버 통신 오류");
          navigate("/login");
        }
      };
      login();
    } else {
      toast.error("인증 코드가 없습니다.");
      navigate("/login");
    }
  }, [searchParams, navigate]);

  return <LoadingPage />;
}
