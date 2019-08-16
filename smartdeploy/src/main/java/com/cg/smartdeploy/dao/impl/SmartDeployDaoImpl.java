package com.cg.smartdeploy.dao.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.cg.smartdeploy.dao.SmartDeployDao;
import com.cg.smartdeploy.entity.BenchEmployee;
import com.cg.smartdeploy.entity.DemandTable;
import com.cg.smartdeploy.entity.ManagerMaster;
import com.cg.smartdeploy.exception.Messages;
import com.cg.smartdeploy.exception.ProgramException;

@Repository
@Transactional
public class SmartDeployDaoImpl implements SmartDeployDao {

	@PersistenceContext
	private EntityManager entityManager;

	static String currentManager = "";
	
	/**
	 * 
	 * @param employees
	 * @return ManagerMaster object which confirm that manager exist in database or not
	 * @throws ProgramException
	 */
	public ManagerMaster loginDb(ManagerMaster employees) throws ProgramException {

		ManagerMaster newManagerMaster = entityManager.find(ManagerMaster.class, employees.getManagerId());
		if (newManagerMaster == null)
			throw new ProgramException(Messages.SIGN_IN_ERROR);

		if (newManagerMaster.getManagerPassword().equals(employees.getManagerPassword())) {
			currentManager = employees.getManagerId();

			return newManagerMaster;
		} else
			throw new ProgramException(Messages.SIGN_IN_ERROR);

	}
	
	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @return List of integer having values totalRequestRaised and totalRequestFullfill
	 * @throws ProgramException
	 */
	public List<Integer> demandReportingDbDao(LocalDate startDate, LocalDate endDate) throws ProgramException {

		int totalRequestRaised = 0;
		int totalRequestFullfill = 0;
		TypedQuery<DemandTable> query1 = entityManager.createQuery(
				"SELECT demandTable  FROM DemandTable demandTable WHERE managerId=:mid AND demandTable.requestDate>=:startd AND demandTable.requestDate<=:endd",
				DemandTable.class);
		query1.setParameter("mid", currentManager);
		query1.setParameter("startd", startDate);
		query1.setParameter("endd", endDate);

		List<DemandTable> demandList = query1.getResultList();

		for (int index = 0; index < demandList.size(); index++) {

			totalRequestRaised = totalRequestRaised + demandList.get(index).getRequestRaised();
			totalRequestFullfill = totalRequestFullfill + demandList.get(index).getRequestFullfill();
		}

		List<Integer> totalRequest = new ArrayList<Integer>();
		totalRequest.add(totalRequestRaised);
		totalRequest.add(totalRequestFullfill);

		return totalRequest;
	}
	
	/**
	 * this method return all the employee details who are currently on bench
	 * @return List<BenchEmployee> 
	 */
	@Override
	public List<BenchEmployee> getBenchEmployeeDetails() {

		Query query = entityManager
				.createQuery("select benchEmployee from BenchEmployee benchEmployee where managerId IS NULL");
		List<BenchEmployee> tempResult = query.getResultList();
		return tempResult;
	}
	
	/**
	 * This method is used make the selected list of employees to deploy stage
	 * @param list
	 * @throws ProgramException
	 */
	@Override
	public void deployServiceDao(List<String> list) throws ProgramException {

		for (int index = 0; index < list.size(); index++) {
			BenchEmployee benchEmployee = entityManager.find(BenchEmployee.class, list.get(index));
			if (benchEmployee == null) {
				throw new ProgramException(Messages.SIGN_IN_ERROR);
			}
			benchEmployee.setManagerId(currentManager);

		}

	}
	/**
	 * this method used to perform Super admin operation to observe all the demand report in form of pie chart
	 * @param id
	 * @return
	 */

	@Override
	public List<Integer> adminReport(String id) {
		int totalRequestRaised = 0;
		int totalRequestFullfill = 0;
		TypedQuery<DemandTable> query1 = entityManager.createQuery(
				"SELECT demandTable  FROM DemandTable demandTable WHERE managerId=:mid ", DemandTable.class);
		query1.setParameter("mid", id);
		List<DemandTable> demandList = query1.getResultList();
		for (int index = 0; index < demandList.size(); index++) {

			totalRequestRaised = totalRequestRaised +demandList.get(index).getRequestRaised();
			totalRequestFullfill = totalRequestFullfill +demandList.get(index).getRequestFullfill();
		}
		List<Integer> totalRequest = new ArrayList<Integer>();
		totalRequest.add(totalRequestRaised);
		totalRequest.add(totalRequestFullfill);
		return totalRequest;
	}

}
