package com.cylentsystems.dropwizard.sisu.guice.guice.test;

import java.lang.annotation.Annotation;
import javax.inject.Inject;
import io.dropwizard.servlets.tasks.Task;

import org.eclipse.sisu.BeanEntry;
import org.eclipse.sisu.inject.BeanLocator;
import org.eclipse.sisu.launch.InjectedTest;
import org.junit.Test;
import com.google.inject.Key;


public class BeanLocatorTest extends InjectedTest {

  @Inject
  private BeanLocator locator;

  @Test
  public void beanLocator() {
    for (BeanEntry<Annotation, Task> t : locator.locate(Key.get(Task.class))) {
      Task task = t.getValue();
    }
  }
}
