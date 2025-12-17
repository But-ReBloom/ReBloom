import { createBrowserRouter } from "react-router-dom";
import App from "./App";
import LoginPage from "./Pages/LoginPage/LP.tsx";
import SignUpPage from "./Pages/signuppage/SUP.tsx";
import FindEmail from "./Pages/findEmailpage/FEP.tsx";
import FindId from "./Pages/findPasswordpage/FPWP.tsx";
import FT_HobbyTest from "./Pages/findtastepage/ftp.tsx";
import FT_HobbyTest_Description from "./Pages/findtaste-description-page/ft-dp.tsx";
import FT_TestPage from "./Pages/findtaste-testpage/fttp.tsx";
import NotFound from "./Pages/404page/NF.tsx";
import ExpReviewPage from "./Pages/expReviewpage/Exr.tsx";
import MyExps from "./Pages/myExpspage/MEP.tsx";
import EPT from "./Pages/exploreTaste/EPT.tsx";
import Community from "./Pages/communityPage/CP";
import MainPage from "./Pages/MainPage/MP";
import MyPost from "./Pages/myPostPage/myPost";
import Post from "./Pages/postPage/post";
import PostDetail from "./Pages/postPage/PostDetail";
import TestResult from "./Pages/hobbytestresultpage/hobbytestresultpage.tsx";
import Thankyou from "./components/thanktou/thankyou.tsx";
import Mypage from "./Pages/mypage/Mypage.tsx";
import GoogleCallback from "./Pages/GoogleCallback/GoogleCallback.tsx";

//경로명세 라우터
const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
  },
  {
    path: "/auth/google/callback",
    element: <GoogleCallback />,
  },
    {
    path: "/community",
    element: <Community />,
  },
  {
    path: "/post", 
    element: <Post />,
  },
    {
    path: "/myPostPage",
    element: <MyPost />,
  },
  {
    path: "/main",
    element: <MainPage />,
  },
    {
    path: "/post/:id", 
    element: <PostDetail />,
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
    path: "/forgot/email",
    element: <FindEmail />,
  },
  {
    path: "/forgot/id",
    element: <FindId />,
  },
  {
    path: "/taste",
    element: <FT_HobbyTest />,
  },
  {
    path: "/taste/hobby",
    element: <FT_HobbyTest_Description />,
  },
  {
    path: "taste/hobby/test",
    element: <FT_TestPage />,
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
    element: <NotFound />,
  },
  {
    path: "/expreview",
    element: <ExpReviewPage />,
  },
  {
    path: "/myexps",
    element: <MyExps />,
  },
  {
    path: "/explore/taste",
    element: <EPT />,
  },
  {
    path: "/thankyou",
    element: <Thankyou />,
  },
  {
    path: "/test/result",
    element: <TestResult/>,
  },
  {
    path: "/mypage",
    element: <Mypage/>,
  }
]);

export default router;
