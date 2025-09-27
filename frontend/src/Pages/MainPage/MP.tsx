import Header from "../../components/mainpage-Header/mph";
import Body from "../../components/mainpage-Body/mpb";
import './MP.css';

function MainPage() {
    return (
        <div className="mainpage-container">
            <Header />
            <Body />
        </div>
    );
}

export default MainPage;