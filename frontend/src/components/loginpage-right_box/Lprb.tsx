import './Lprb.css';
import { Link } from 'react-router-dom';
import Dodum from '../../assets/images/dodamdodam.svg';
import Google from '../../assets/images/Google.svg';

function Right_box() {
    return (
        <div className="Right_box">
            <div className="login-container">
                <div className="login-container-textbox">
                    <p id='Welcome-to-Rebloom'>Welcome to Rebloom</p>
                    <p id='Please-take-a-moment-to-login'>Please take a moment to login</p>
                </div>
                <div className="login-container-inputbox">
                    <div className="login-container-inputbox-mail">
                        <p className='Email'>Email</p>
                        <input className="login-input" type="text" placeholder='Enter your Emaail'/>
                    </div>
                    <div className="login-container-inputbox-pw">
                        <p className='Password'>Password</p>
                        <input className="login-input" type="text" placeholder='Enter your password' />
                    </div>
                </div>
                <div className="login-container-buttonbox">
                    <Link to="/" className='forgot-password'>Forgot password?</Link>
                    <button className="login-button-oksign">Log In</button>
                </div>
                <div className="OAuth-family">
                    <div className="dodum">
                        <button id='Dodum'><img className='OAuth-button' src={Dodum} alt="" /></button>
                    </div>
                    <div className="google">
                        <button id='google'><img className="OAuth-button" src={Google} alt="" /></button>
                    </div>
                </div>
            </div>
        </div>
    );
}
export default Right_box;