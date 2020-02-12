package com.barkly.dropwizard.sisu.guice.guice.test.tasks;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import io.dropwizard.servlets.tasks.Task;

@Named
public class MyTask extends Task {

	public MyTask() {
		super("my-task");
	}
	
	@Override
	public void execute(Map<String, List<String>> map, PrintWriter output) throws Exception {
		output.println("my task complete.");
	}
}
