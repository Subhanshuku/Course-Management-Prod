import React, { useState } from 'react';
import axiosInstance from '../Backend/AxiosInstance';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

export default function UsersRegistration() {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    password: "",
    confirmPassword: "",
    roleSelection: ""
  });

  const navigate = useNavigate();

  const onChangeCtrl = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const onSubmit = async (e) => {
    e.preventDefault();

    // Validate passwords match
    if (formData.password !== formData.confirmPassword) {
      toast.error("Passwords do not match");
      return;
    }

    const payload = {
      name: formData.name,
      email: formData.email,
      password: formData.password, // Ensure this is hashed server-side
      roles: formData.roleSelection.toUpperCase()
    };

    try {
      const response = await axiosInstance.post('/auth/new', payload);
      console.log("User registered:", response.data);
      toast.success("User registered");
      // Optionally clear the form after successful registration
      setFormData({
        name: "",
        email: "",
        password: "",
        confirmPassword: "",
        roleSelection: ""
      });

      // Navigate to login page
      navigate("/login");
    } catch (error) {
      console.error("Error registering user:", error);
      toast.error("Error registering user");
    }
  };

  return (
    <div className="container mt-5 ">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <form className='form-control pt-1 bg-body-secondary shadow-lg border border-secondary-subtle' onSubmit={onSubmit}>
            <h2 className="text-center">Register</h2>
            <div className="w-100 mb-3 mt-3 d-flex flex-column align-items-start ">
              <label className="form-label ps-2">Name</label>
              <input
                type="text"
                className="form-control"
                id="name"
                name="name"
                value={formData.name}
                onChange={onChangeCtrl}
                placeholder="Enter your name"
                required
              />
            </div>
            <div className="w-100 mb-3 mt-3 d-flex flex-column align-items-start ">
              <label className="form-label ps-2">Email</label>
              <input
                type="email"
                className="form-control"
                id="email"
                name="email"
                value={formData.email}
                onChange={onChangeCtrl}
                placeholder="Enter your email"
                required
              />
            </div>
            <div className="w-100 mb-3 mt-3 d-flex flex-column align-items-start ">
              <label className="form-label ps-2">Password</label>
              <input
                type="password"
                className="form-control"
                id="password"
                name="password"
                value={formData.password}
                onChange={onChangeCtrl}
                placeholder="Enter your password"
                required
              />
            </div>
            <div className="w-100 mb-3 mt-3 d-flex flex-column align-items-start ">
              <label className="form-label ps-2">Confirm Password</label>
              <input
                type="password"
                className="form-control"
                id="confirmPassword"
                name="confirmPassword"
                value={formData.confirmPassword}
                onChange={onChangeCtrl}
                placeholder="Confirm your password"
                required
              />
            </div>
            <div className="w-100 mb-3 mt-3 d-flex flex-column align-items-start ">
              <label className="form-label ps-2">Role</label>
              <div className="dropdown w-100">
                <select
                  className="form-select"
                  id="roleSelection"
                  name="roleSelection"
                  value={formData.roleSelection}
                  onChange={onChangeCtrl}
                  required
                >
                  <option value="">Select your role</option>
                  <option value="Student">Student</option>
                  <option value="Teacher">Teacher</option>
                </select>
                <span className="dropdown-arrow"></span>
              </div>
            </div>
            <button type="submit" className="btn btn-dark btn-block mt-3">Register</button>
            <p className="mt-3">Already have an account? <a href="/login" className='text-dark'>Login</a></p>
          </form>
        </div>
      </div>
    </div>
  );
}
