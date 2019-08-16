package com.cg.smartdeploy.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.smartdeploy.dao.SmartDeployDao;
import com.cg.smartdeploy.entity.BenchEmployee;
import com.cg.smartdeploy.entity.ManagerMaster;
import com.cg.smartdeploy.exception.ProgramException;
import com.cg.smartdeploy.service.SmartDeployService;

@Service
public class SmartDeployServiceImpl implements SmartDeployService {

	@Autowired
	private SmartDeployDao dao;

	public ManagerMaster login(ManagerMaster employees) throws ProgramException {

		return dao.loginDb(employees);
	}

	public List<Integer> demandReportingDb(LocalDate startDate, LocalDate endDate) throws ProgramException {

		return dao.demandReportingDbDao(startDate, endDate);
	}

	public List<BenchEmployee> getBenchEmployee() {

		return dao.getBenchEmployeeDetails();
	}

	@Override
	public void deployService(List<String> employeesList) throws ProgramException {

		dao.deployServiceDao(employeesList);
	}

	@Override
	public List<Integer> superAdminService(String id) {

		return dao.adminReport(id);
	}

}
