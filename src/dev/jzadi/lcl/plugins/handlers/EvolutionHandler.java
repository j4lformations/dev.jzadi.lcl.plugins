package dev.jzadi.lcl.plugins.handlers;

import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jgit.api.errors.GitAPIException;

import dev.jzadi.lcl.plugins.models.services.IGitService;
import dev.jzadi.lcl.plugins.models.services.impl.GitService;

public class EvolutionHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IGitService service = new GitService();		
		try {
			service.cloneDepot("java");
			service.createNewBranch("develop", "java");
//			System.out.println(Platform.getInstanceLocation().getURL().toString());
//			System.out.println(new File(System.getProperty("eclipse.launcher")).getParent());
//			System.out.println(service.getWorkSpace());
		} catch (IOException | GitAPIException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
}