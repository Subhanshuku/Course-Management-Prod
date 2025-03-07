import React, { useEffect, useState } from 'react';
import Navbar from '../Navbar/Navbar';
import axiosInstance from '../Backend/AxiosInstance';
import { toast } from 'react-toastify';
import { useNavigate } from 'react-router-dom';

export default function TeacherHome() {
  const [teacher, setTeacher] = useState(null);
  const [courses, setCourses] = useState([]);
  const [error, setError] = useState(null);
  const [isRegistered,setIsRegistered] = useState(false);
  // State for displaying enrolled students
  const [enrolledStudents, setEnrolledStudents] = useState([]);
  const [selectedCourseName, setSelectedCourseName] = useState('');
  const [showEnrolledStudents, setShowEnrolledStudents] = useState(false);

  // State for displaying teacher's enrolled course
  const [teacherCourse, setTeacherCourse] = useState(null);
  const [showTeacherCourse, setShowTeacherCourse] = useState(false);

  useEffect(() => {
    const fetchTeacherDetails = async () => {
      try {
        const token = localStorage.getItem('token');
        if (!token) {
          throw new Error('No token found. Please log in again.');
        }

        const teacherResponse = await axiosInstance.get(`/teacher/getById/${localStorage.getItem('userId')}`);
        setTeacher(teacherResponse.data);
      } catch (err) {
        toast.error('Cannot fetch User');
      }
    };

    const fetchCourses = async () => {
      try {
        const coursesResponse = await axiosInstance.get('/course/getAll');
        setCourses(coursesResponse.data);
      } catch (err) {
        setError('Failed to fetch courses');
        toast.error('Cannot fetch Courses');
      }
    };

    fetchTeacherDetails();
    fetchCourses();
  }, [isRegistered]);

  const handleRegister = async (courseId) => {
    try {
      await axiosInstance.post(`/teacher/assignCourse/${localStorage.getItem('userId')}/${courseId}`);
      setIsRegistered(!isRegistered);
      toast.success('Registered to the course successfully');
    } catch (err) {
      console.log(err);
      toast.error('Failed to register to the course');
    }
  };

  const handleDrop = async (courseId) => {
    try {
      await axiosInstance.post(`/teacher/dropCourse/${localStorage.getItem('userId')}/${courseId}`);
      setIsRegistered(!isRegistered);
      toast.success('Dropped from the course successfully');
    } catch (err) {
      toast.error('Failed to drop the course');
    }
  };

  const handleViewEnrolledStudents = async (courseId) => {
    try {
      const response = await axiosInstance.get(`/student/getAllStudentsByCourseId/${courseId}`);
      setEnrolledStudents(response.data);

      const course = courses.find((c) => c.courseId === courseId);
      setSelectedCourseName(course ? course.courseName : '');

      setShowEnrolledStudents(true);
    } catch (err) {
      toast.error('Failed to fetch enrolled students');
    }
  };

  const handleViewTeacherCourse = async () => {
    try {
      const response = await axiosInstance.get(`/teacher/getCourse/${localStorage.getItem('userId')}`);
      if (response.data.ErrorMessage) {
        toast.error(response.data.ErrorMessage);
      } else {
        setTeacherCourse(response.data);
        setShowTeacherCourse(true);
      }
    } catch (err) {
      if (err.response && err.response.data && err.response.data.ErrorMessage) {
        toast.error(err.response.data.ErrorMessage);
      } else {
        toast.error('Failed to fetch enrolled course');
      }
    }
  };

  const navigate = useNavigate();

  if (!teacher) {
    return (
      <div className='mt-10'>
        <div className='mt-10'>
          <p className='h1'>You are not Authorised. Please login or register</p>
          <button className="btn btn-success btn-sm me-2" onClick={() => navigate('/register')}>Register</button>
          <button className="btn btn-success btn-sm me-2" onClick={() => navigate('/login')}>Login</button>
        </div>
      </div>
    );
  }

  return (
    <>
      <Navbar />

      {/* Use container for the rest of the content */}
      <div className="container mt-5">
        <div className="bg-light p-5 rounded shadow-sm">
          {/* Updated welcome message */}
          <h1 className="display-5 fw-bold">Welcome, {teacher.teacherName}!</h1>
          <p className="fs-4">
            Ready to teach different courses to students? Explore the courses below.
          </p>
        </div>

        <h2 className="mt-5 mb-4">Available Courses</h2>
        {error && <p>{error}</p>}
        <table className="table table-hover table-bordered shadow-sm">
          <thead className="table-dark">
            <tr>
              <th scope="col">Course ID</th>
              <th scope="col">Course Name</th>
              <th scope="col">Description</th>
              <th scope="col" style={{ width: '35%' }}>Action</th>
            </tr>
          </thead>
          <tbody>
            {courses.map((course) => (
              <tr key={course.courseId}>
                <th scope="row">{course.courseId}</th>
                <td>{course.courseName}</td>
                <td>{course.courseDesc}</td>
                <td>
                  {
                    course.courseId === teacher.courseId ? (
                      <button
                        className="btn btn-danger btn-sm me-2"
                        onClick={() => handleDrop(course.courseId)}
                      >
                        Drop
                      </button>

                    ) : (

                      <button
                        className="btn btn-success btn-sm me-2"
                        onClick={() => handleRegister(course.courseId)}
                      >
                        Register
                      </button>
                    )
                  }
                  <button
                    className="btn btn-info btn-sm"
                    onClick={() => handleViewEnrolledStudents(course.courseId)}
                  >
                    Enrolled Students
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>

        {/* Button to view teacher's enrolled course */}
        <button
          className="btn btn-primary btn-lg mt-4"
          onClick={handleViewTeacherCourse}
        >
          Enrolled Course
        </button>

        {/* Display Teacher's Enrolled Course */}
        {showTeacherCourse && teacherCourse && (
          <div className="mt-5">
            <h2>Your Enrolled Course</h2>
            <table className="table table-hover table-bordered shadow-sm">
              <thead className="table-dark">
                <tr>
                  <th scope="col">Course ID</th>
                  <th scope="col">Course Name</th>
                  <th scope="col">Description</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <th scope="row">{teacherCourse.courseId}</th>
                  <td>{teacherCourse.courseName}</td>
                  <td>{teacherCourse.courseDesc}</td>
                </tr>
              </tbody>
            </table>
            <button
              className="btn btn-secondary mt-3"
              onClick={() => setShowTeacherCourse(false)}
            >
              Hide Enrolled Course
            </button>
          </div>
        )}

        {/* Display Enrolled Students */}
        {showEnrolledStudents && (
          <div className="mt-5">
            <h2>Enrolled Students in {selectedCourseName}</h2>
            {enrolledStudents.length > 0 ? (
              <table className="table table-hover table-bordered shadow-sm">
                <thead className="table-dark">
                  <tr>
                    <th scope="col">Student ID</th>
                    <th scope="col">Student Name</th>
                    <th scope="col">Email</th>
                  </tr>
                </thead>
                <tbody>
                  {enrolledStudents.map((student) => (
                    <tr key={student.studentId}>
                      <th scope="row">{student.studentId}</th>
                      <td>{student.studentName}</td>
                      <td>{student.studentEmail}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            ) : (
              <p>No students are enrolled in this course.</p>
            )}
            <button
              className="btn btn-secondary mt-3"
              onClick={() => setShowEnrolledStudents(false)}
            >
              Hide Enrolled Students
            </button>
          </div>
        )}
      </div>
    </>
  );
}
