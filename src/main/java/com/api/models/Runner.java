package com.api.models;

import com.api.models.converters.StringToBoolean;
import com.api.models.converters.StringToInteger;
import com.api.models.converters.StringToStringArray;
import com.creditdatamw.zerocell.annotation.Column;

import lombok.Data;

@Data
public class Runner {

	@Column(index = 0, name = "TestCaseName")
	private String testCaseName;
	@Column(index = 1, name = "TestCaseDescription")
	private String testCaseDescription;
	@Column(index = 2, name = "Execute", converterClass  = StringToBoolean.class)
	private boolean execute;
	@Column(index = 3, name = "InvocationCount", converterClass = StringToInteger.class)
	private int invocationCount;
	@Column(index = 4, name = "Priority", converterClass = StringToInteger.class)
	private int priority;
	@Column(index = 5, name = "Group", converterClass = StringToStringArray.class)
	private String[] group;

}
