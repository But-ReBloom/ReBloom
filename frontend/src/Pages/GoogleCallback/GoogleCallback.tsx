import { useEffect } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import { authApi } from "../../api/auth";
import { toast } from "react-toastify";

export default function GoogleCallback() {
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();

  useEffect(() => {
    const code = searchParams.get("code");

    if (code) {
      const login = async () => {
        try {
          const response = await authApi.googleLogin({
            authorizationCode: code,
          });

          if (response.success) {
            // Store token if needed
            // localStorage.setItem('token', response.data.token);
            toast.success(`환영합니다, ${response.data.userName}님!`);
            navigate("/", { state: { id: response.data.userEmail } });
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
