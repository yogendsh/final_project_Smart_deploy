package com.cg.smartdeploy.servicetest;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cg.smartdeploy.entity.BenchEmployee;
import com.cg.smartdeploy.entity.ManagerMaster;
import com.cg.smartdeploy.exception.ProgramException;
import com.cg.smartdeploy.service.SmartDeployService;

@Configuration
@ComponentScan(basePackages = "com.cg.smartdeploy.service.impl")
class SpringTestContext{
	
}
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringTestContext.class)

public class SmartDeployTests{
	@Autowired
	private SmartDeployService deployService;
	private ManagerMaster master;
	private LocalDate startDate;
	private LocalDate endDate;
	private List<String> employeesList;
	@Test
	public void testGetBenchEmployee() {
		List<BenchEmployee> result=deployService.getBenchEmployee(); 
	
		assertEquals(null, result);
			
	}
	@Test
	public void testLogin() throws ProgramException {
		ManagerMaster result=deployService.login(master);
		assertEquals(null, result);
	
	
	}
	@Test
	public void testDemand() throws ProgramException {
		List<Integer> result=deployService.demandReportingDb(startDate, endDate);
		assertEquals(null, result);
	
	}
	@Test
	public void testDeploy() throws ProgramException {
		deployService.deployService(employeesList);
		assertEquals(null, employeesList);
	
	}
	
}
