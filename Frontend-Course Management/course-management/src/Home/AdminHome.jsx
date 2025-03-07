// AdminHome.jsx

import React, { useEffect, useState } from 'react';
import Navbar from '../Navbar/Navbar';
import axiosInstance from '../Backend/AxiosInstance';
import { toast } from 'react-toastify';

export default function AdminHome() {
  const [selectedOption, setSelectedOption] = useState('Course');
  const [data, setData] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [formData, setFormData] = useState({});
  const [editingId, setEditingId] = useState(null);
  

  // Handle dropdown change
  const handleSelectChange = (e) => {
    setSelectedOption(e.target.value);
    setShowForm(false);
    setFormData({});
    setEditingId(null);
  };

  // Fetch data whenever selectedOption changes
  useEffect(() => {
    fetchData();
  }, [selectedOption]);

  const fetchData = async () => {
    const endpoints = {
      Student: '/student/getAll',
      Course: '/course/getAll',
      Teacher: '/teacher/getAll',
    };
    try {
      const response = await axiosInstance.get(endpoints[selectedOption]);
      setData(response.data);
    } catch (err) {
      toast.error(`Failed to fetch ${selectedOption.toLowerCase()}s`);
    }
  };

  // Delete entry with confirmation
  const handleDelete = async (id) => {
    if (!window.confirm('Are you sure you want to delete this entry?')) {
      return;
    }
    const endpoints = {
      Student: `/auth/deleteUser/${id}`,
      Course: `/course/delete/${id}`,
      Teacher: `/auth/deleteUser/${id}`,
    };
    try {
      await axiosInstance.delete(endpoints[selectedOption]);
      toast.success(`${selectedOption} deleted successfully`);
      fetchData();
    } catch (err) {
      toast.error(`Failed to delete ${selectedOption.toLowerCase()}`);
    }
  };

  // Handle add/update toggle
  const handleToggleForm = () => {
    setShowForm(!showForm);
    setFormData({});
    setEditingId(null);
  };

  // Handle input change for forms
  const handleInputChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  // Save entry (add or update)
  const handleSave = async (e) => {
    e.preventDefault();
    let endpoint = '';
    let method = '';

    if (selectedOption === 'Course') {
      // Adding or updating courses
      endpoint = editingId
        ? `/course/update/${editingId}`
        : '/course/add';
      method = editingId ? 'put' : 'post';

      // Prepare payload for adding/updating course
      const payload = {
        courseId: formData.courseId,
        courseName: formData.courseName,
        courseDesc: formData.courseDesc,
      };

      try {
        await axiosInstance[method](endpoint, payload);
        toast.success(`Course ${editingId ? 'updated' : 'added'} successfully`);
        fetchData();
        setShowForm(false);
      } catch (err) {
        toast.error(`Failed to ${editingId ? 'update' : 'add'} course`);
      }
    } else if (selectedOption === 'Student' || selectedOption === 'Teacher') {
      if (editingId) {
        // Updating student or teacher
        endpoint = `/auth/update/${editingId}`;
        method = 'put';

        // Prepare payload for updating
        const payload = {
          name: formData.name,
          email: formData.email,
        };

        try {
          await axiosInstance[method](endpoint, payload);
          toast.success(`${selectedOption} updated successfully`);
          fetchData();
          setShowForm(false);
        } catch (err) {
          toast.error(`Failed to update ${selectedOption.toLowerCase()}`);
        }
      } else {
        // Adding new student or teacher
        endpoint = '/auth/new';
        method = 'post';

        // Prepare the payload
        const payload = {
          name: formData.name,
          email: formData.email,
          password: formData.password,
          roles: selectedOption.toLowerCase(), // "student" or "teacher"
        };

        try {
          await axiosInstance[method](endpoint, payload);
          toast.success(`${selectedOption} added successfully`);
          fetchData();
          setShowForm(false);
        } catch (err) {
          toast.error(`Failed to add ${selectedOption.toLowerCase()}`);
        }
      }
    }
  };

  // Edit entry
  const handleEdit = (item) => {
    let newFormData = {};
    if (selectedOption === 'Course') {
      newFormData = {
        courseId: item.courseId,
        courseName: item.courseName,
        courseDesc: item.courseDesc,
      };
    } else if (selectedOption === 'Student') {
      newFormData = {
        name: item.studentName,
        email: item.studentEmail,
      };
    } else if (selectedOption === 'Teacher') {
      newFormData = {
        name: item.teacherName,
        email: item.teacherEmail,
      };
    }
    setFormData(newFormData);
    setEditingId(item.id || item.courseId || item.studentId || item.teacherId);
    setShowForm(true);
  };

  // Handle cancel button
  const handleCancel = () => {
    setShowForm(false);
    setFormData({});
    setEditingId(null);
  };

  // Render form fields based on selectedOption
  const renderFormFields = () => {
    if (selectedOption === 'Course') {
      return (
        <div className="container">
          <div className="row justify-content-center">
            <div className="col-12 col-sm-8 col-md-6">
              <div className="mb-3">
                <label className="form-label">Course ID</label>
                <input
                  type="text"
                  className="form-control"
                  name="courseId"
                  value={formData.courseId || ''}
                  onChange={handleInputChange}
                  required
                />
              </div>
              <div className="mb-3">
                <label className="form-label">Course Name</label>
                <input
                  type="text"
                  className="form-control"
                  name="courseName"
                  value={formData.courseName || ''}
                  onChange={handleInputChange}
                  required
                />
              </div>
              <div className="mb-3">
                <label className="form-label">Course Description</label>
                <textarea
                  className="form-control"
                  name="courseDesc"
                  value={formData.courseDesc || ''}
                  onChange={handleInputChange}
                  required
                ></textarea>
              </div>
            </div>
          </div>
        </div>
      );
    } else if (selectedOption === 'Student' || selectedOption === 'Teacher') {
      return (
        <div className="container">
          <div className="row justify-content-center">
            <div className="col-12 col-sm-8 col-md-6">
              <div className="mb-3">
                <label className="form-label">Name</label>
                <input
                  type="text"
                  className="form-control"
                  name="name"
                  value={formData.name || ''}
                  onChange={handleInputChange}
                  required
                />
              </div>
              <div className="mb-3">
                <label className="form-label">Email</label>
                <input
                  type="email"
                  className="form-control"
                  name="email"
                  value={formData.email || ''}
                  onChange={handleInputChange}
                  required
                />
              </div>
              {!editingId && (
                <div className="mb-3">
                  <label className="form-label">Password</label>
                  <input
                    type="password"
                    className="form-control"
                    name="password"
                    value={formData.password || ''}
                    onChange={handleInputChange}
                    required
                  />
                </div>
              )}
            </div>
          </div>
        </div>
      );
    }
  };

  // Render table headers
  const renderTableHeaders = () => {
    return (
      <>
        <th>ID</th>
        <th>Name</th>
        {(selectedOption === 'Student' || selectedOption === 'Teacher') && <th>Email</th>}
        {selectedOption === 'Course' && <th>Description</th>}
        <th>Action</th>
      </>
    );
  };

  // Render table rows
  const renderTableRows = () => {
    return data.map((item) => {
      const id = item.id || item.courseId || item.studentId || item.teacherId;
      const name = item.courseName || item.studentName || item.teacherName;
      const email = item.studentEmail || item.teacherEmail;
      return (
        <tr key={id}>
          <td>{id}</td>
          <td>{name}</td>
          {(selectedOption === 'Student' || selectedOption === 'Teacher') && <td>{email}</td>}
          {selectedOption === 'Course' && <td>{item.courseDesc}</td>}
          <td>
            <button
              className="btn btn-warning btn-sm me-2"
              onClick={() => handleEdit(item)}
            >
              Update
            </button>
            <button
              className="btn btn-danger btn-sm"
              onClick={() => handleDelete(id)}
            >
              Delete
            </button>
          </td>
        </tr>
      );
    });
  };

  return (
    <>
      <Navbar />
      <div className="container mt-5">
        <h1 className="mb-4">Admin Dashboard</h1>

        {/* Dropdown Menu */}
        <div className="mb-4">
          <label className="form-label me-2">Manage:</label>
          <select
            className="form-select w-auto d-inline"
            value={selectedOption}
            onChange={handleSelectChange}
          >
            <option value="Student">Student</option>
            <option value="Course">Course</option>
            <option value="Teacher">Teacher</option>
          </select>
        </div>

        {/* Add Button for All Sections */}
        {!showForm && (
          <div className="mb-3">
            <button className="btn btn-primary" onClick={handleToggleForm}>
              {`Add New ${selectedOption}`}
            </button>
          </div>
        )}

        {/* Forms for Adding/Updating Entries */}
        {showForm && (
          <form onSubmit={handleSave} className="mb-4">
            {renderFormFields()}
            <button type="submit" className="btn btn-success me-2">
              {editingId ? 'Update' : 'Add'} {selectedOption}
            </button>
            <button type="button" className="btn btn-secondary" onClick={handleCancel}>
              Cancel
            </button>
          </form>
        )}

        {/* Data Table */}
        <table className="table table-hover table-bordered shadow-sm">
          <thead className="table-dark">
            <tr>{renderTableHeaders()}</tr>
          </thead>
          <tbody>{data.length === 0 ? <tr><td colSpan="5" className="text-center">No data available</td></tr> : renderTableRows()}</tbody>
        </table>
      </div>
    </>
  );
}
