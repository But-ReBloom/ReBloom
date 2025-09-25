import MainPage from './Pages/mainpage/MP'
import './App.css'
import { Outlet } from "react-router";

function App() {
  return (   
    <>
    <MainPage/>
    <Outlet/>
    </>
  );
}

export default App