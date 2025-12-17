import { Navigate, Outlet } from 'react-router-dom';
import { toast } from 'react-toastify';

const PrivateRoute = () => {
  const token = localStorage.getItem('token');

  if (!token) {
    toast.error('로그인이 필요한 서비스입니다.');
    return <Navigate to="/login" replace />;
  }

  return <Outlet />;
};

export default PrivateRoute;
