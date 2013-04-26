package io.tesla.dropwizard.sisu;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.argparse4j.inf.Namespace;

import org.eclipse.sisu.BeanScanning;
import org.eclipse.sisu.binders.SpaceModule;
import org.eclipse.sisu.binders.WireModule;
import org.eclipse.sisu.reflect.ClassSpace;
import org.eclipse.sisu.reflect.URLClassSpace;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.yammer.dropwizard.cli.ConfiguredCommand;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Configuration;

public abstract class SisuCommand<T extends Configuration> extends ConfiguredCommand<T> {

	protected SisuCommand(String name, String description) {
		super(name, description);
	}
	
	private Injector createInjector(T configuration) {
	    ClassSpace space = new URLClassSpace(getClass().getClassLoader());
	    SpaceModule spaceModule = new SpaceModule(space, BeanScanning.CACHE);
	    List<Module> modules = new ArrayList<Module>();
	    modules.add(spaceModule);
	    for(Module m : modules(configuration)) {
	      System.out.println("Adding " + m);
	      modules.add(m);
	    }
	    return Guice.createInjector(new WireModule(modules));
	  }

	  //
	  // Allow the application to customize the modules
	  //
	  protected Module[] modules(T configuration) {
	    return new Module[]{};
	  }

	@Override
	protected void run(Bootstrap<T> bootstrap, Namespace namespace,
			T configuration) throws Exception {
		Injector injector = createInjector(configuration);
	    injector.injectMembers(this);	
	}

}
