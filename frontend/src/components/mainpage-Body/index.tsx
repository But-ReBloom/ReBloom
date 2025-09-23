import './index.css';
import arrow from '../../assets/images/arrow.svg';
import { Link } from 'react-router-dom';

function Body() {
    return (
        <>
            <div className='body-container'>
                <div id='intro-textbox'>
                    <p id='intro-big'>Taste Management Platform</p>
                    <p id='intro-small'><b>Find your taste, and do related activities</b></p>
                </div>
                <div className="body-buttons">
                    <Link to="/login"><button id='go-findTaste-button'>Taste Activity</button></Link>
                    <Link to="/login" id='go-findTaste_Hobbytest'>
                        <div id="intro-buttons">
                            <p>Discover your taste</p>
                            <img src={arrow} alt="" />
                        </div>
                    </Link>
                </div>
            </div>
        </>
    );
}

export default Body;