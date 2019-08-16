package com.cg.smartdeploy.servicetest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.cg.smartdeploy.dao.SmartDeployDao;
import com.cg.smartdeploy.service.SmartDeployService;
import com.cg.smartdeploy.service.impl.SmartDeployServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class SmartDeployServiceTests {
	
	@Mock
	private SmartDeployDao smartDeployDao;

	@InjectMocks
	private SmartDeployService deployService=new SmartDeployServiceImpl();
	
	@Before
	public void initializeMockito()
	{
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public  void pieChartGenerationTest()
	{
		List<Integer> expected=new ArrayList<Integer>();
		expected.add(10);
		expected.add(4);
		
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		LocalDate startDate = LocalDate.parse("23-05-2019", dateTimeFormatter);
		LocalDate endDate = LocalDate.parse("18-08-2019", dateTimeFormatter);
		
		
		//stub(smartDeployDao.demandReportingDbDao(startDate,endDate)).toReturn(expected);
		
	}
}
