import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

export default function Logout() {
  const navigate = useNavigate();

  useEffect(() => {
    const confirmed = window.confirm("Are you sure you want to logout?");
    if (confirmed) {
      // Remove auth data from localStorage
      localStorage.removeItem('token');
      localStorage.removeItem('userId');
      localStorage.removeItem('role');
      // Redirect to login page
      navigate('/login');
    } else {
      // If logout is cancelled, navigate back to the previous page
      navigate(-1);
    }
  }, [navigate]);

  return (
    <div>
      <h2>Logging out...</h2>
    </div>
  );
}
