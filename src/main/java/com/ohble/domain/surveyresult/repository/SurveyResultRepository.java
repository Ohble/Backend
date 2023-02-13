package com.ohble.domain.surveyresult.repository;

import com.ohble.domain.surveyresult.SurveyResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyResultRepository extends JpaRepository<SurveyResult, Long> {
}
