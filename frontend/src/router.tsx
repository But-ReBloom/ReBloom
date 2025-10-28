import { createBrowserRouter } from "react-router-dom";
import App from "./App";
import LoginPage from "./Pages/loginpage/LP.tsx";
import SignUpPage from "./Pages/signuppage/SUP.tsx";
import FindEmail from "./Pages/findEmailpage/FEP.tsx";
import FindPassWord from "./Pages/findPasswordpage/FPWP.tsx";
import FT_HobbyTest from "./Pages/findtastepage/ftp.tsx";
import FT_HobbyTest_Description from "./Pages/findtaste-description-page/ft-dp.tsx";
import FT_TestPage from "./Pages/findtaste-testpage/fttp.tsx";


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
    path:"/taste",
    element:<FT_HobbyTest/>
  },
  {
    path:"/taste/hobby",
    element:<FT_HobbyTest_Description/>
  },
  {
    path:"taste/hobby/test",
    element:<FT_TestPage/>
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