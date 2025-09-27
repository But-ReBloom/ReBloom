import { createBrowserRouter } from "react-router-dom";
import App from "./App";
import LoginPage from "./Pages/loginpage/LP.tsx";
import SignUpPage from "./Pages/signuppage/SUP.tsx";
import FindEmail from "./Pages/findEmailpage/FEP.tsx";
import FindPassWord from "./Pages/findPasswordpage/FPWP.tsx";


const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
  },
  {
    path: "/login",
    element: <LoginPage />,
  },
  {
    path: "/signup",
    element: <SignUpPage />,
  },
  {
    path:"/forgot/email",
    element: <FindEmail />
  },
  {
    path:"/forgot/password",
    element: <FindPassWord/>,
  },
  {
    path: "*",
    element: (
      <>
        <span>찾을 수 없습니다</span>
      </>
    ),
  },
]);

export default router;