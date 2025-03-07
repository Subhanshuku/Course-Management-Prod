import { Link, Route, Routes } from 'react-router-dom'
import UsersRegistration from './Registration/UsersRegistration';
import AdminHome from './Home/AdminHome';
import StudentHome from './Home/StudentHome';
import TeacherHome from './Home/TeacherHome';
import Login from './login/Login';
import Landing from './Home/Landing';
import Logout from './login/Logout';
import { ToastContainer } from 'react-toastify';

function App() {
  return (
    <div align="center">
      <ToastContainer position='top-center' />
      <Routes>
        <Route path='/' Component={Landing} className='align-center'></Route>
        <Route path='/register' Component={UsersRegistration}></Route>
        <Route path='/login' Component={Login}></Route>
        <Route path='/adminHome' Component={AdminHome}></Route>
        <Route path='/studentHome' Component={StudentHome}></Route>
        <Route path='/teacherHome' Component={TeacherHome}></Route>
        <Route path='/logout' Component={Logout}></Route>
      </Routes>
    </div>
  );
}

export default App;
