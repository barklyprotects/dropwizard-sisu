package com.barkly.dropwizard.sisu.guice.guice.test;

import com.barkly.dropwizard.sisu.SisuApplication;

public class SampleApplication extends SisuApplication<SampleServiceConfiguration> {
  public static void main(String[] args) throws Exception {
    new SampleApplication().run(new String[] {"server", "example.yml"});
  }
}
