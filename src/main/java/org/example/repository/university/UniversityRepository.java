package org.example.repository.university;

import org.example.base.repository.BaseRepository;
import org.example.model.University;

import java.util.List;

public interface UniversityRepository extends BaseRepository<University, Long> {
    List<University> findAllUniversity();

}
