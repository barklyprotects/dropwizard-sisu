package io.tesla.dropwizard.sisu.guice.test;

import io.tesla.dropwizard.sisu.SisuService;

public class SampleService extends SisuService<SampleServiceConfiguration> {
  public static void main(String[] args) throws Exception {
    new SampleService().run(new String[] {"server", "example.yml"});
  }
}
