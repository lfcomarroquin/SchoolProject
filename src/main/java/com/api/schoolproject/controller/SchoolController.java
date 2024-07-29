package com.api.schoolproject.controller;

import com.api.schoolproject.dto.SchoolDto;
import com.api.schoolproject.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @GetMapping("/query")
    public ResponseEntity<List<SchoolDto>> findAllStudents() {
        List<SchoolDto> listStudents = this.schoolService.getAll();
        return ResponseEntity.ok(listStudents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolDto> findById(@PathVariable("id") String id) {
        SchoolDto student = this.schoolService.getById(id);
        return ResponseEntity.ok(student);
    }

    @PostMapping("/")
    public ResponseEntity<SchoolDto> saveStudent(@RequestBody SchoolDto studentDto) {
        SchoolDto savedStudent = this.schoolService.save(studentDto);
        return ResponseEntity.ok(savedStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchoolDto> updateStudent(@RequestBody SchoolDto studentDto, @PathVariable("id") String id) {
        SchoolDto updatedStudent = this.schoolService.update(studentDto, id);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") String id) {
        this.schoolService.delete(id);
        String mensaje = "El estudiante con ID " + id + " ha sido eliminado correctamente.";
        return ResponseEntity.ok(mensaje);
    }

}