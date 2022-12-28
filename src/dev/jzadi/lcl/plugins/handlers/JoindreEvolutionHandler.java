package dev.jzadi.lcl.plugins.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import dev.jzadi.lcl.plugins.views.TestCombo;

public class JoindreEvolutionHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		TestCombo combo = new TestCombo();
		combo.open();
		return null;
	}

}
