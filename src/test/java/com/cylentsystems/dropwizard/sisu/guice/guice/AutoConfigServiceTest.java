package com.cylentsystems.dropwizard.sisu.guice.guice;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import com.cylentsystems.dropwizard.sisu.common.resources.CommonResource;
import com.cylentsystems.dropwizard.sisu.guice.guice.test.MultiPackageApplication;
import com.cylentsystems.dropwizard.sisu.guice.guice.test.SampleApplication;
import com.cylentsystems.dropwizard.sisu.guice.guice.test.SampleServiceConfiguration;
import com.cylentsystems.dropwizard.sisu.guice.guice.test.health.MyHealthCheck;
import com.cylentsystems.dropwizard.sisu.guice.guice.test.resources.MyResource;
import com.cylentsystems.dropwizard.sisu.guice.guice.test.tasks.MyTask;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.tasks.Task;
import com.yammer.metrics.core.HealthCheck;


public class AutoConfigServiceTest {

	@Mock private SampleServiceConfiguration configuration;
	@Mock private Environment environment;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	//@Test
	public void itInstallsResources() throws Exception {
		SampleApplication s = new SampleApplication();
		s.run(configuration, environment);
		
		
		ArgumentCaptor<MyResource> resource = ArgumentCaptor.forClass(MyResource.class);
		verify(environment).addResource(resource.capture());
		assertThat(resource.getValue(), is(MyResource.class));
	}
	
	//@Test
	public void itInstallsMultiPackageResources() throws Exception {
		MultiPackageApplication s = new MultiPackageApplication();
		s.run(configuration, environment);
		
		ArgumentCaptor<?> captor = ArgumentCaptor.forClass(Object.class);
		verify(environment, times(2)).addResource(captor.capture());
		
		List<?> values = captor.getAllValues();
		assertEquals(2, values.size());
		
		Set<Class<?>> expectedResults = new HashSet<Class<?>>();
	  expectedResults.add(MyResource.class);
	  expectedResults.add(CommonResource.class);
	  for(Object obj : values){
	    Class<?> cls = obj.getClass();
	    expectedResults.remove(cls);
	  }
	  
	  assertTrue(expectedResults.isEmpty());
	}
	
	//@Test
	public void itWiresUpDependencies() throws Exception {
		SampleApplication s = new SampleApplication();
		s.run(configuration, environment);
		
		ArgumentCaptor<MyResource> resource = ArgumentCaptor.forClass(MyResource.class);
		verify(environment).addResource(resource.capture());
		
		MyResource r = resource.getValue();
		assertThat(r.getMyService(), not(nullValue()));
		assertThat(r.getMyService().getMyOtherService(), not(nullValue()));
	}
	
	@Test
	public void itInstallsHealthChecks() throws Exception {
		SampleApplication s = new SampleApplication();
		s.run(configuration, environment);

		ArgumentCaptor<? extends HealthCheck> healthCheck = ArgumentCaptor.forClass(HealthCheck.class);
		verify(environment).addHealthCheck(healthCheck.capture());
		assertThat(healthCheck.getValue(), is(MyHealthCheck.class));
	}
	
	@Test
	public void itInstallsTasks() throws Exception {
		SampleApplication s = new SampleApplication();
		s.run(configuration, environment);
		
		ArgumentCaptor<? extends Task> task = ArgumentCaptor.forClass(Task.class);
		verify(environment).addTask(task.capture());
		assertThat(task.getValue(), is(MyTask.class));
	}
}