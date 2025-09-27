import Left_box from "../../components/loginpage-left_box/Lplb";
import Right_box from "../../components/loginpage-right_box/Lprb";
import './LP.css';
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