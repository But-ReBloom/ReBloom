import { createBrowserRouter } from "react-router-dom";
import App from "./App";
import LoginPage from "./Pages/LoginPage/LP";

const router = createBrowserRouter([
    {
        path: "/",
        element: <App/>,
    },
    { 
        path: "/login", 
        element: <LoginPage/>,
    },
])

export default router;