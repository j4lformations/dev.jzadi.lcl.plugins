package dev.jzadi.lcl.plugins;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import dev.jzadi.lcl.plugins.services.IGitService;
import dev.jzadi.lcl.plugins.services.impl.GitService;

/**
 * Classe permettant de controller le cycle de vie du plugin
 * 
 * @author Joachim
 * @version 1.0
 *
 */
public class Activator extends AbstractUIPlugin {

	public static final String PLUGIN_ID = "dev.jzadi.lcl.plugins";

	private static Activator plugin;

	private IGitService service;

	public Activator() {		
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		service = new GitService();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		service = null;
		super.stop(context);
	}

	public IGitService getService() {
		return service;
	}

	public static Activator getDefault() {
		return plugin;
	}
}
