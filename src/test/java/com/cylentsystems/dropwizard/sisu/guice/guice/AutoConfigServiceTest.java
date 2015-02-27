package com.cylentsystems.dropwizard.sisu.guice.guice;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import com.codahale.metrics.health.HealthCheckRegistry;
import com.cylentsystems.dropwizard.sisu.SisuHealthCheck;
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

import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.servlets.tasks.Task;
import io.dropwizard.setup.AdminEnvironment;
import io.dropwizard.setup.Environment;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class AutoConfigServiceTest {

	@Mock private SampleServiceConfiguration configuration;
	@Mock private Environment environment;
    @Mock private JerseyEnvironment jersey;
    @Mock private HealthCheckRegistry healthCheckRegistry;
    @Mock private AdminEnvironment adminEnvironment;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
        when(environment.jersey()).thenReturn(jersey);
        when(environment.admin()).thenReturn(adminEnvironment);
        when(environment.healthChecks()).thenReturn(healthCheckRegistry);
	}
	
	@Test
	public void itInstallsResources() throws Exception {
		SampleApplication s = new SampleApplication();
		s.run(configuration, environment);
		
		
		ArgumentCaptor<MyResource> resource = ArgumentCaptor.forClass(MyResource.class);
		verify(environment.jersey(), times(2)).register(resource.capture());
		assertThat(resource.getValue(), is(MyResource.class));
	}
	
	@Test
	public void itInstallsMultiPackageResources() throws Exception {
		MultiPackageApplication s = new MultiPackageApplication();
		s.run(configuration, environment);
		
		ArgumentCaptor<?> captor = ArgumentCaptor.forClass(Object.class);
		verify(environment.jersey(), times(2)).register(captor.capture());
		
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
	
	@Test
	public void itWiresUpDependencies() throws Exception {
		SampleApplication s = new SampleApplication();
		s.run(configuration, environment);
		
		ArgumentCaptor<MyResource> resource = ArgumentCaptor.forClass(MyResource.class);
		verify(environment.jersey(),times(2)).register(resource.capture());
		
		MyResource r = resource.getValue();
		assertThat(r.getMyService(), not(nullValue()));
		assertThat(r.getMyService().getMyOtherService(), not(nullValue()));
	}

    @Test
    public void isInstallsHealthCheck() throws Exception {

        SampleApplication s = new SampleApplication();
        s.run(configuration,environment);

        ArgumentCaptor<? extends SisuHealthCheck> healthCheck = ArgumentCaptor.forClass(SisuHealthCheck.class);
        verify(environment.healthChecks()).register(eq("my-health"),healthCheck.capture());
        assertThat(healthCheck.getValue(),is(SisuHealthCheck.class));
    }
	
	@Test
	public void itInstallsTasks() throws Exception {
		SampleApplication s = new SampleApplication();
		s.run(configuration, environment);
		
		ArgumentCaptor<? extends Task> task = ArgumentCaptor.forClass(Task.class);
		verify(environment.admin()).addTask(task.capture());
		assertThat(task.getValue(), is(MyTask.class));
	}
}
