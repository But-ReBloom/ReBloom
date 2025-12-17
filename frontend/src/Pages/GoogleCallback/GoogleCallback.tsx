import { useEffect, useRef } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import { authApi } from "../../api/auth";
import { toast } from "react-toastify";

export default function GoogleCallback() {
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();
  const processedRef = useRef(false);

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
            navigate("/", { state: { id: response.data.name } });
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

  return <div>Google Login Processing...</div>;
}
