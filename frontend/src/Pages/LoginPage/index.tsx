import Left_box from "../../components/login_page-left_box";
import Right_box from "../../components/login_page-right_box";
import './index.css';
function LoginPage() {
return (
    <>
    <div className="login_box">
        <div className="left_box box"><Left_box /></div>
        <div className="right_box box"><Right_box /></div>
    </div>
    </>
);
}

export default LoginPage;