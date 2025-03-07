package com.service;

import com.dto.StudentDTO;
import com.dto.TeacherDTO;
import com.externalServices.StudentClient;
import com.externalServices.TeacherClient;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.entity.UserInfo;
import com.repository.UserInfoRepository;

@Service
public class UserService {
	@Autowired
	private UserInfoRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private StudentClient studentClient;

	@Autowired
	private TeacherClient teacherClient;

	public String addUser(UserInfo userInfo) {
		String name = userInfo.getName();
		UserInfo obj1 = repository.findByName(name).orElse(null);



		if (obj1 == null) {
			userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
			UserInfo obj2 = repository.save(userInfo);

			String role = obj2.getRoles();

			if ("student".equalsIgnoreCase(role)) {
				StudentDTO studentDTO = new StudentDTO(obj2.getId(),obj2.getName(),obj2.getEmail());
				studentClient.addStudent(studentDTO);
			} else if ("teacher".equalsIgnoreCase(role)) {
				TeacherDTO teacherDTO = new TeacherDTO(obj2.getId(),obj2.getName(), obj2.getEmail());
				teacherClient.addTeacher(teacherDTO);
			}
			return "Registration Successfully ";
		} else {
			return "This UserName is Already Registered.";
		}

	}

	public String getRoles(String username) {
		UserInfo obj2 = repository.findByName(username).orElse(null);
		if (obj2 != null) {
			return obj2.getRoles();
		}
		return "Not Found";
	}

    public String deleteRoleById(int userId) {
		UserInfo userInfo=repository.findById(userId).orElse(null);
		repository.deleteById(userId);
		if("student".equalsIgnoreCase(userInfo.getRoles())){
			studentClient.deleteStudentById(userId);
		} else if ("teacher".equalsIgnoreCase(userInfo.getRoles())) {
			teacherClient.deleteTeacherById(userId);
		}
		return "deleted";
    }

	public String updateUser(int userId,UserInfo userInfo) {
		UserInfo userInfo1=repository.findById(userId).orElse(null);

		if("student".equalsIgnoreCase(userInfo1.getRoles())){
			StudentDTO dto=new StudentDTO(userInfo.getId(),userInfo.getName(),userInfo.getEmail());
			studentClient.updateStudent(userId,dto);
		} else if ("teacher".equalsIgnoreCase(userInfo1.getRoles())) {
			TeacherDTO dto=new TeacherDTO(userInfo.getId(),userInfo.getName(),userInfo.getEmail());
			teacherClient.updateTeacher(userId,dto);
		}

		//repository.save(userInfo);
		userInfo1.setName(userInfo.getName());
		userInfo1.setEmail(userInfo.getEmail());
		repository.save(userInfo1);
		return "Updated";
	}
}
