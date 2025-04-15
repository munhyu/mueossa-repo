package com.smhrd.mueossa.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.mueossa.entity.TbSurvey;

@Repository
public interface SurveyRepository extends JpaRepository<TbSurvey, String> {

	Optional<TbSurvey> findById(String id);

}
