import React, { useState } from 'react';
import axiosInstance from '../Backend/AxiosInstance';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

export default function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        const payload = { username, password };

        try {
            const response = await axiosInstance.post('/auth/authenticate', payload);
            const token = response.data.jwtToken;
            const role = response.data.role;
            const useId = response.data.useId;

            // Store token in localStorage
            localStorage.setItem('token', token);
            localStorage.setItem('userId', useId);
            localStorage.setItem('role', role);


            // Navigate based on user role
            if (role.toUpperCase() === "STUDENT") {
                toast.success("Logged in as Student")
                navigate("/studentHome");
            } else if (role.toUpperCase() === "TEACHER") {
                toast.success("Logged in as Teacher")
                navigate("/teacherHome");
            } else if (role.toUpperCase() === "ADMIN") {
                toast.success("Logged in as Admin")
                navigate("/adminHome");
            } else {
                setError("Role not valid");
            }
        } catch (err) {
            console.error('Login error:', err);
            if (err.response && err.response.status === 401) {
                //setError("Incorrect username or password. Please try again.");
                toast.error("Incorrect username or password. Please try again.");
            } else if (err.response && err.response.status === 403) {
                //setError("Access forbidden. Please check your credentials and try again.");
                toast.error("Access forbidden. Please check your credentials and try again.");
            } else {
                //setError("An error occurred. Please try again later.");
                toast.error("An error occurred. Please try again later.");
            }
        }
    };

    return (
        <div className="container border-dark">
            <div className="col-md-6 pt-5">
                {error && <div className="alert alert-danger" role="alert">{error}</div>}
                <form className='form-control pt-3 bg-body-secondary shadow-lg border border-secondary-subtle mt-2' onSubmit={handleSubmit}>
                    <h2 className="mt-2">Login</h2>
                    <div className="w-100 mb-3 mt-3 d-flex flex-column align-items-start ">
                        <label className='form-label ps-2' htmlFor="username">Username</label>
                        <input
                            type="text"
                            className="form-control"
                            id="username"
                            name="username"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            placeholder="Enter your username"
                            required
                        />
                    </div>
                    <div className="w-100 mb-3 mt-3 d-flex flex-column align-items-start ">
                        <label className='form-label ps-2' htmlFor="password">Password</label>
                        <input
                            type="password"
                            className="form-control"
                            id="password"
                            name="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            placeholder="Enter your password"
                            required
                        />
                    </div>
                    <button type="submit" className="btn btn-dark btn-block mt-4">Login</button>
                    <p className="mt-3">Doesn't have an account? <a href="/register" className='text-dark'>Register</a></p>
                </form>
            </div>
        </div>
    );

}
