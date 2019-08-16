package com.cg.smartdeploy.servicetest;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cg.smartdeploy.dao.SmartDeployDao;
import com.cg.smartdeploy.entity.BenchEmployee;
import com.cg.smartdeploy.entity.ManagerMaster;
import com.cg.smartdeploy.exception.ProgramException;

@Component
public class SmartDeployImplStub implements SmartDeployDao{

	@Override
	public ManagerMaster loginDb(ManagerMaster employees) throws ProgramException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> demandReportingDbDao(LocalDate startDate, LocalDate endDate) throws ProgramException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BenchEmployee> getBenchEmployeeDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deployServiceDao(List<String> list) throws ProgramException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Integer> adminReport(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}

