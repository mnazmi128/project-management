package com.naz.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.naz.pma.dto.EmployeeProjectCount;
import com.naz.pma.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	
	@Override
	public List<Employee> findAll();
	
	@Query(nativeQuery=true, value="SELECT e.first_name as firstName, e.last_name as lastName, COUNT(pe.employee_id) as projectCount "
			+ "FROM employee e left join project_employee pe ON e.id = pe.employee_id "
			+ "GROUP BY e.first_name, e.last_name "
			+ "ORDER BY COUNT(pe.employee_id) DESC")
	public List<EmployeeProjectCount> getEmployeeProjectCount(); 
	
}
