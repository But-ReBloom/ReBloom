import logo from '../../assets/images/Rebloom-logo.svg';
import './index.css';

function Left_box() {
    return (
        <div className="left_box">
            <img className='logo' src={logo} alt="" />
            <div className='text-box'>
                <p className='find-your-taste'>Find your taste</p>
                <p className='Use-Re-Bloom-to-find-your-taste'>Use Re: Bloom to find your taste</p>
                <p className='and-you-will-blooming-again'>and you will blooming again!</p>
            </div>
        </div>
    );
}
export default Left_box;