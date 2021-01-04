package com.naz.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.naz.pma.dto.ChartData;
import com.naz.pma.entities.Project;

public interface ProjectRepository extends CrudRepository<Project, Long>{
	
	@Override
	public List<Project> findAll();
	
	@Query(nativeQuery=true, value="SELECT stage as label, COUNT(stage) as value "
			+ "FROM project "
			+ "GROUP BY stage "
			+ "ORDER BY COUNT(stage) DESC")
	public List<ChartData> projectStatusCount();
}
