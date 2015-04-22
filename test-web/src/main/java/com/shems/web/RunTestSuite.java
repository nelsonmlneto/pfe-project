package com.shems.web;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	ObjectFilterTestSuite.class, 
	DashboardTestSuite.class, 
	ObjectVisualizationTestSuite.class,
	RegisterObjectTestSuite.class}
)
public class RunTestSuite {

}
