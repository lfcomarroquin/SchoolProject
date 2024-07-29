package com.api.schoolproject.controller;

import com.api.schoolproject.dto.SchoolDto;
import com.api.schoolproject.service.SchoolService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SchoolControllerTest {

    @InjectMocks
    private SchoolController schoolController;

    @Mock
    private SchoolService schoolService;

    @Test
    public void findAllStudentsShouldWork() {
        //Preparacion
        SchoolDto user1 = new SchoolDto();
        user1.setId("1");
        user1.setName("Luis");
        user1.setEmail("luis@example.com");

        SchoolDto user2 = new SchoolDto();
        user2.setId("2");
        user2.setName("Karin");
        user2.setEmail("karin@example.com");

        when(schoolService.getAll()).thenReturn(Arrays.asList(user1, user2));

        //Ejecucion
        List<SchoolDto> students = schoolController.findAllStudents().getBody();

        //Validacion
        assertNotNull(students);
        assertEquals(2, students.size());
        assertEquals("Luis", students.get(0).getName());
        assertEquals("Karin", students.get(1).getName());
    }

    @Test
    public void findByIdShouldWork() {
        //Preparacion
        SchoolDto user = new SchoolDto();
        user.setId("1");
        user.setName("Luis");
        user.setEmail("luis@example.com");

        when(schoolService.getById("1")).thenReturn(user);

        //Ejecucion
        SchoolDto foundUser = schoolController.findById("1").getBody();

        //Validacion
        assertNotNull(foundUser);
        assertEquals("Luis", foundUser.getName());
        assertEquals("luis@example.com", foundUser.getEmail());
    }

    @Test
    public void createStudentShouldWork() {
        //Preparacion
        SchoolDto studentDto = new SchoolDto();
        studentDto.setName("Luis");
        studentDto.setEmail("luis@example.com");

        SchoolDto savedStudentDto = new SchoolDto();
        savedStudentDto.setId("1");
        savedStudentDto.setName("Luis");
        savedStudentDto.setEmail("luis@example.com");

        when(schoolService.save(studentDto)).thenReturn(savedStudentDto);

        //Ejecucion
        SchoolDto savedStudent = schoolController.saveStudent(studentDto).getBody();

        //Validacion
        assertNotNull(savedStudent);
        assertEquals("1", savedStudent.getId());
        assertEquals("Luis", savedStudent.getName());
        assertEquals("luis@example.com", savedStudent.getEmail());
    }

    @Test
    public void updateStudentShouldWork () {
        //Preparacion
        String id = "1";
        SchoolDto studentDto = new SchoolDto();
        studentDto.setId(id);
        studentDto.setName("Luis");
        studentDto.setEmail("luis@example.com");

        SchoolDto updatedStudentDto = new SchoolDto();
        updatedStudentDto.setId(id);
        updatedStudentDto.setName("Luis Updated");
        updatedStudentDto.setEmail("luis@example.com");

        when(schoolService.update(studentDto, id)).thenReturn(updatedStudentDto);

        //Ejecucion
        SchoolDto responseStudent = schoolController.updateStudent(studentDto, id).getBody();

        //Validacion
        assertNotNull(responseStudent);
        assertEquals("1", responseStudent.getId());
        assertEquals("Luis Updated", responseStudent.getName());
        assertEquals("luis@example.com", responseStudent.getEmail());
    }

    @Test
    public void deleteStudentShouldWork() {
        //Preparacion
        String id = "1";

        //Ejecucion
        schoolController.deleteStudent(id);

        //Validacion
        verify(schoolService, times(1)).delete(id);
    }

}