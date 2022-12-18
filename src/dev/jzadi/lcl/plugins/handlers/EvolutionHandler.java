package dev.jzadi.lcl.plugins.handlers;

import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jgit.api.errors.GitAPIException;

import dev.jzadi.lcl.plugins.Activator;

public class EvolutionHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Activator activator = Activator.getDefault();
		try {
			activator.getService().cloneDepot("java");
			activator.getService().createNewBranch("develop", "java");
		} catch (IOException | GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}