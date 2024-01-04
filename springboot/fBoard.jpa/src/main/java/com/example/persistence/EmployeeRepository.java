package com.example.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.domain.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	// 기본함수
	// 전체검색 	: findAll()
	// 아이디검색 	: findById()
	// 입력		: save()
	// 삭제		: deleteById()
	// 상세 검색	: findBy컬럼명,키워드()
	
	// JPQL
	// @Query("SELECT e, d FROM Employee e INNER JOIN e.dept d")
	//@Query("SELECT e, e.dept FROM Employee e")
//	List<Object[]> getEmployeeList();
	
	// SQL
	@Query(value="SELECT * FROM emp e INNER JOIN dept d on e.deptno = d.deptno ", nativeQuery=true)
	List<Object[]> getEmployeeList();
}
