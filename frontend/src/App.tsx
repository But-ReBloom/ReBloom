import MainPage from './Pages/MainPage'
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