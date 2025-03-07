package com.project.Course_Service;

import com.project.Course_Service.DTO.StudentDTO;
import com.project.Course_Service.entity.Course;
import com.project.Course_Service.externalServices.StudentServices;
import com.project.Course_Service.repo.CourseRepo;
import com.project.Course_Service.service.CourseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = CourseServiceApplicationTests.class)
class CourseServiceApplicationTests {

	@Mock
	CourseRepo courseRepo;

	@Mock
	StudentServices studentServices;

	@InjectMocks
	CourseServiceImpl courseService;

	List<Course> courses=new ArrayList<>();

	Course course;

	@BeforeEach
	public void setup(){

		course=new Course(1,"Math","Learn Math",null);

		courses.add(new Course(1L,"Java","learn java",null));
		courses.add(new Course(2L,"c++","learn c++",null));
		courses.add(new Course(3L,"Python","learn python",null));
		courses.add(new Course(4L,"Java Script","learn js",null));
		courses.add(new Course(5L,"HTML","learn HTML",null));

	}

	@Test
	void testGetAllCourses(){
		Mockito.when(courseRepo.findAll()).thenReturn(courses);
		Assertions.assertEquals(5,courseService.getAllCourse().size());
	}

	@Test
	void testGetCourseById_Valid(){
		long courseId = 1L;
		Course course = new Course();
		course.setCourseId(courseId);

		List<StudentDTO> students = Arrays.asList(new StudentDTO(), new StudentDTO());

		Mockito.when(courseRepo.findById(courseId)).thenReturn(Optional.of(course));
		Mockito.when(studentServices.getAllStudentsByCourseId(courseId)).thenReturn(students);

		Course result = courseService.getCourseById(courseId);

		Assertions.assertEquals(students, result.getStudents());
	}
}
