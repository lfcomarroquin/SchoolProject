package com.api.schoolproject.repository;

import com.api.schoolproject.entity.SchoolEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolRepository extends MongoRepository<SchoolEntity, String> {

    Optional<SchoolEntity> findByEmail(String email);

}