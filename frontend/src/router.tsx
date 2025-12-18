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
import ChannelPage from "./Pages/communityPage/ChannelPage";
import ChannelJoin from "./Pages/channel/channeljoin";
import ChannelApproval from "./Pages/channel/channelApproval";

import MainPage from "./Pages/MainPage/MP";
import MyPost from "./Pages/myPostPage/myPost";
import Post from "./Pages/postPage/post";
import PostDetail from "./Pages/postPage/PostDetail";

import TestResult from "./Pages/hobbytestresultpage/hobbytestresultpage.tsx";
import Thankyou from "./components/thanktou/thankyou.tsx";
import Mypage from "./Pages/mypage/Mypage.tsx";
import GoogleCallback from "./Pages/GoogleCallback/GoogleCallback.tsx";
import LoadingPage from "./Pages/loadingpage/loading.tsx";

import PrivateRoute from "./components/PrivateRoute";
import PostDetailPage from './Pages/channel/PostDetailPage.tsx';


const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
  },
  {
    path: "/post/:postId",
    element: <PostDetailPage />,
  },
  {
    path: "/auth/google/callback",
    element: <GoogleCallback />,
  },
  {
    path: "/main",
    element: <MainPage />,
  },
  {
    path: "/post",
    element: <Post />,
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

  /* Main */
  {
    path: "/main",
    element: <MainPage />,
  },

  /* Post */
  {
    path: "/post",
    element: <Post />,
  },
  {
    path: "/post/:id",
    element: <PostDetail />,
  },

  /* Channel */
  {
    path: "/channeljoin",
    element: <ChannelJoin />,
  },
  {
    path: "/channelApproval",
    element: <ChannelApproval />,
  },
  {
    path: "/channel/:id",
    element: <ChannelPage key={window.location.pathname} />,
  },

  /* Hobby / Taste */
  {
    path: "/taste",
    element: <FT_HobbyTest />,
  },
  {
    path: "/taste/hobby",
    element: <FT_HobbyTest_Description />,
  },
  {
    path: "/taste/hobby/test",
    element: <FT_TestPage />,
  },

  /* Etc */
  {
    path: "/expreview",
    element: <ExpReviewPage />,
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
    element: <TestResult />,
  },
  {
    path: "/loading",
    element: <LoadingPage />,
  },

  /* Community */
  {
    path: "/community",
    element: <Community />,
  },
  {
        path: "/myPostPage",
        element: <MyPost />,
      },

  /* Protected Routes */
  {
    element: <PrivateRoute />,
    children: [
      {
        path: "/mypage",
        element: <Mypage />,

      },
      // {
      //   path: "/myPostPage",
      //   element: <MyPost />,
      // },
      {
        path: "/myexps",
        element: <MyExps />,
      },
    ],
  },

  /* 404 */
  {
    path: "*",
    element: <NotFound />,
  },
]);

export default router;
