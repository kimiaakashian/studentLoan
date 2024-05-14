package org.example.service.university;

import org.example.base.service.BaseService;
import org.example.model.University;

import java.util.List;

public interface UniversityService extends BaseService<University,Long> {
    List<University> findAllUniversity();
}

