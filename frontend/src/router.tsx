import { createBrowserRouter } from "react-router-dom";
import App from "./App";
import LoginPage from "./Pages/loginpage/LP.tsx";
import SignUpPage from "./Pages/signuppage/SUP.tsx";

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
    path: "*",
    element: (
      <>
        <span>찾을 수 없습니다</span>
      </>
    ),
  },
  {
    path: "/signup",
    element: <SignUpPage />,
  },
]);

export default router;
