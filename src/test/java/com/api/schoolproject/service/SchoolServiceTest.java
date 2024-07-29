package com.api.schoolproject.service;

import com.api.schoolproject.dto.SchoolDto;
import com.api.schoolproject.entity.SchoolEntity;
import com.api.schoolproject.repository.SchoolRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SchoolServiceTest {

    @InjectMocks
    private SchoolService schoolService;

    @Mock
    private SchoolRepository schoolRepository;

    @Test
    public void getAllShouldWork() {
        //Preparacion
        SchoolEntity user1 = new SchoolEntity();
        user1.setId("1");
        user1.setName("Luis");
        user1.setEmail("luis@example.com");

        SchoolEntity user2 = new SchoolEntity();
        user2.setId("2");
        user2.setName("Karin");
        user2.setEmail("karin@example.com");

        when(schoolRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        //Ejecucion
        List<SchoolDto> users = schoolService.getAll();

        //Validacion
        assertNotNull(users);
        assertEquals(2, users.size());
        assertEquals("Luis", users.get(0).getName());
        assertEquals("Karin", users.get(1).getName());
    }

    @Test
    public void findByIdShouldWork() {
        //Preparacion
        SchoolEntity user = new SchoolEntity();
        user.setId("1");
        user.setName("Luis");
        user.setEmail("luis@example.com");

        when(schoolRepository.findById("1")).thenReturn(java.util.Optional.of(user));

        //Ejecucion
        SchoolDto foundUser = schoolService.getById("1");

        //Validacion
        assertNotNull(foundUser);
        assertEquals("Luis", foundUser.getName());
        assertEquals("luis@example.com", foundUser.getEmail());
    }

    @Test
    public void saveStudentShouldWork() {
        //Preparacion
        SchoolDto schoolDto = new SchoolDto(null,"Luis","luis@example.com");
        SchoolEntity student = new SchoolEntity();
        student.setName("Luis");
        student.setEmail("luis@example.com");

        SchoolEntity savedStudent = new SchoolEntity();
        savedStudent.setId("1");
        savedStudent.setName("Luis");
        savedStudent.setEmail("luis@example.com");

        when(schoolRepository.save(any(SchoolEntity.class))).thenReturn(savedStudent);

        //Ejecucion
        SchoolDto savedUserDto = schoolService.save(schoolDto);

        //Validacion
        assertNotNull(savedUserDto);
        assertEquals("1", savedUserDto.getId());
        assertEquals("Luis", savedUserDto.getName());
        assertEquals("luis@example.com", savedUserDto.getEmail());
    }

    @Test
    public void updateStudentShouldWork() {
        //Preparacion
        SchoolDto schoolDto = new SchoolDto(null,"Luis","luis@example.com");

        SchoolEntity user = new SchoolEntity();
        user.setId("1");
        user.setName("Luis");
        user.setEmail("luis@example.com");

        when(schoolRepository.findById("1")).thenReturn(Optional.of(user));
        when(schoolRepository.save(any(SchoolEntity.class))).thenReturn(user);

        //Ejecucion
        SchoolDto updatedStudent = schoolService.update(schoolDto, "1");

        //Validacion
        assertNotNull(updatedStudent);
        assertEquals("1", updatedStudent.getId());
        assertEquals("Luis", updatedStudent.getName());
        assertEquals("luis@example.com", updatedStudent.getEmail());
    }

    @Test
    public void deleteStudentShouldWork() {
        //Preparacion
        SchoolEntity user = new SchoolEntity();
        user.setId("1");
        user.setName("Luis");
        user.setEmail("luis@example.com");

        when(schoolRepository.findById("1")).thenReturn(Optional.of(user));

        //Ejecucion
        schoolService.delete("1");

        //Validacion
        verify(schoolRepository).delete(user);
    }

}