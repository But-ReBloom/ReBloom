import { createBrowserRouter } from "react-router-dom";
import App from "./App";
import LoginPage from "./Pages/LoginPage";

const router = createBrowserRouter([
    {
        path: "/",
        element: <App/>
    },
    {
        path: "/Login",
        element: <LoginPage/>
    }
])

export default router;