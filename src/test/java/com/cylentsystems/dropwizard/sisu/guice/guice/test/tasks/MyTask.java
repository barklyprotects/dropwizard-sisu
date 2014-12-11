package com.cylentsystems.dropwizard.sisu.guice.guice.test.tasks;

import java.io.PrintWriter;

import javax.inject.Named;

import com.google.common.collect.ImmutableMultimap;
import io.dropwizard.servlets.tasks.Task;

@Named
public class MyTask extends Task {

	public MyTask() {
		super("my-task");
	}
	
	@Override
	public void execute(ImmutableMultimap<String, String> parameters,
			PrintWriter output) throws Exception {

		output.println("my task complete.");
	}

}
