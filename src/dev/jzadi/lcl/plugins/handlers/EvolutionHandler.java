package dev.jzadi.lcl.plugins.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import dev.jzadi.lcl.plugins.models.services.IGitService;
import dev.jzadi.lcl.plugins.models.services.impl.GitService;

public class EvolutionHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IGitService service = new GitService();
		System.out.println(service.getWorkSpace());
		return null;
	}
}