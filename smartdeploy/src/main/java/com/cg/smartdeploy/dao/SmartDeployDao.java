package com.cg.smartdeploy.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import com.cg.smartdeploy.entity.BenchEmployee;
import com.cg.smartdeploy.entity.ManagerMaster;
import com.cg.smartdeploy.exception.ProgramException;

public interface SmartDeployDao {
	
	/**
	 * 
	 * @param employees
	 * @return ManagerMaster object which confirm that manager exist in database or not
	 * @throws ProgramException
	 */
	public ManagerMaster loginDb(ManagerMaster employees) throws ProgramException;

	
	
	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @return List of integer having values totalRequestRaised and totalRequestFullfill
	 * @throws ProgramException
	 */
	public List<Integer> demandReportingDbDao(LocalDate startDate, LocalDate endDate) throws ProgramException;

	/**
	 * this method return all the employee details who are currently on bench
	 * @return List<BenchEmployee> 
	 */
	public List<BenchEmployee> getBenchEmployeeDetails();
	
	
	/**
	 * This method is used make the selected list of employees to deploy stage
	 * @param list
	 * @throws ProgramException
	 */
	public void deployServiceDao(List<String> list) throws ProgramException;
	
	
	
	/**
	 * this method used to perform Super admin operation to observe all the demand report in form of pie chart
	 * @param id
	 * @return
	 */
	public List<Integer> adminReport(String id);
}
