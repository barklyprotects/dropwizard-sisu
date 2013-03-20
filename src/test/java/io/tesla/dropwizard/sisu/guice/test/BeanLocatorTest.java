package io.tesla.dropwizard.sisu.guice.test;

import java.lang.annotation.Annotation;

import javax.inject.Inject;

import org.eclipse.sisu.BeanEntry;
import org.eclipse.sisu.containers.InjectedTest;
import org.eclipse.sisu.locators.BeanLocator;
import org.junit.Test;

import com.google.inject.Key;
import com.yammer.dropwizard.tasks.Task;

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
