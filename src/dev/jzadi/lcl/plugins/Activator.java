package dev.jzadi.lcl.plugins;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * Classe permettant de controller le cycle de vie du plugin
 * 
 * @author Joachim
 * @version 1.0
 *
 */
public class Activator extends AbstractUIPlugin {

	public static final String PLUGIN_ID = "dev.jzadi.lcl.plugins"; //$NON-NLS-1$

	private static Activator plugin;

	public Activator() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	public static Activator getDefault() {
		return plugin;
	}

}
