package com.api.schoolproject.service;

import com.api.schoolproject.dto.SchoolDto;
import com.api.schoolproject.entity.SchoolEntity;
import com.api.schoolproject.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    public List<SchoolDto> getAll() {
        return this.schoolRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public SchoolDto getById(String id) {
        return this.schoolRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    public SchoolDto save(SchoolDto school) {
        SchoolEntity entity = new SchoolEntity();
        entity.setName(school.getName());
        entity.setEmail(school.getEmail());
        SchoolEntity entitySaved = this.schoolRepository.save(entity);
        SchoolDto saved = this.toDto(entitySaved);
        return saved;
    }

    public SchoolDto update(SchoolDto school, String id) {
        SchoolEntity entity = this.schoolRepository.findById(id)
                .orElse(null);
        entity.setName(school.getName());
        entity.setEmail(school.getEmail());
        SchoolEntity entitySaved = this.schoolRepository.save(entity);
        SchoolDto saved = this.toDto(entitySaved);
        return saved;
    }

    public void delete(String id) {
        SchoolEntity entity = this.schoolRepository.findById(id)
                .orElse(null);
        this.schoolRepository.delete(entity);
    }

    private SchoolDto toDto(SchoolEntity entity) {
        return new SchoolDto(entity.getId(), entity.getName(), entity.getEmail());
    }

}