package com.cg.smartdeploy.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.smartdeploy.entity.BenchEmployee;
import com.cg.smartdeploy.entity.DemandDateDto;
import com.cg.smartdeploy.entity.ManagerMaster;
import com.cg.smartdeploy.exception.Messages;
import com.cg.smartdeploy.exception.ProgramException;
import com.cg.smartdeploy.service.SmartDeployService;


/**
 * 
 * @author Team 3
 * @projectName Smart Deploy
 */




@RestController
@CrossOrigin
public class SmartDeployController {
	/**
	 * This handler method performs login functionality and returns an object if the
	 * particular managerMaster exists.
	 */

	@Autowired
	private SmartDeployService service;
	public static org.slf4j.Logger log=LoggerFactory.getLogger(SmartDeployController.class);


	@PostMapping(path = "/login", produces = "application/json", consumes = "application/json")

	public ManagerMaster Login(@RequestBody ManagerMaster managerMaster) throws ProgramException {
		log.info("Checking that manager with Id "+managerMaster.getManagerId()+ " exist in database or not for login");
		ManagerMaster managerMasterObj = service.login(managerMaster);
		return managerMasterObj;

	}

	/**
	 * This handler method takes the date given by the managerMaster and returns a
	 * list of integers consists of demand raised and request fulfilled values.
	 * which will leads to the structure of pie chart.
	 */
	@PostMapping(path = "/demand", produces = "application/json", consumes = "application/json")
	public List<Integer> pieChartGeneration(@RequestBody DemandDateDto demandDateDto) throws ProgramException {
     
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		LocalDate startDate = LocalDate.parse(demandDateDto.getBeginDate(), dateTimeFormatter);
		LocalDate endDate = LocalDate.parse(demandDateDto.getEndDate(), dateTimeFormatter);
		if(startDate.isAfter(endDate))
			throw new ProgramException(Messages.WRONG_DATE_FORMAT);
		log.info("It will post the list of details which is under the"+startDate+"and"+endDate);
		List<Integer> demandRatioList = service.demandReportingDb(startDate, endDate);
		return demandRatioList;
	}

	/**
	 * this handler method handle the pie chart click event and display all the
	 * employees whose managerid is null
	 * 
	 */
	@GetMapping(value = "/benchEmployeeDetails")
	@ResponseBody
	public List<BenchEmployee> getDetails() {
		log.info("This will get all the list of bench employees");
		return service.getBenchEmployee();
	}

	/**
	 * this handler method is used to fullfill the demand manager request by
	 * providing managerid to benchemployee
	 */
	@PostMapping(path = "/deployEmployee", produces = "application/json", consumes = "application/json")
	public void deployEmployees(@RequestBody List<String> employees) throws ProgramException {

		log.info("this will show the list of employees who is fulfilling the demand request");
		service.deployService(employees);
	}
	/**
	 * This handler method is used to get the details of the managerMaster on
	 * considering managerMaster id as a parameter
	 */

	@GetMapping(value = "/superAdminDashboard/{id}")
	@ResponseBody

	public List<Integer> superAdminReport(@PathVariable String id) {
		log.info("this will show the list of manager master according to the given id");
		return service.superAdminService(id);
	}

}
