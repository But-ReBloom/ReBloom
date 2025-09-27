import Header from "../../components/mainpage-Header";
import Body from "../../components/mainpage-Body";
import './index.css';
// import { BrowserRouter as Router,Route,Link} from "react-router-dom";

function MainPage() {
    return (
        <>
            <header><Header /></header>
            <body>
                <Body />
            </body>
        </>
    );
}

export default MainPage;