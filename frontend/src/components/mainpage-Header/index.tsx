import { useEffect, useState } from "react";
import './index.css';
import logo from '../../assets/images/Rebloom-logo.svg';

function Header() {
    const [scrolled, setScrolled] = useState(false);
    
    useEffect(() => {
        const handleScroll = () => {
            setScrolled(window.scrollY > 50);
        };
        window.addEventListener("scroll", handleScroll);
        
        return () =>{
            window.removeEventListener("scroll", handleScroll)
        };
    }, []);

    return (
        <>
            <div className={`header-container ${scrolled ? "scrolled" : ""}`} id='header-container'>
                <a href=""><img className='logo-svg' src={logo} alt="rebloom-logo" /></a>
                <div className='header-menu'>
                    <ul>
                        <li><a href="">취향 발견</a></li>
                        <li><a href="">취향 탐색</a></li>
                        <li><a href="">커뮤니티</a></li>
                        <li><a href="">마이페이지</a></li>
                    </ul>
                </div>
                <div className="login-box">
                    <button className='login-button' id='login-box_button' >Login</button>
                    <button className='about-button' id='login-box_button' >About</button>
                </div>
            </div>
        </>
    );
}

export default Header