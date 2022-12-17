package dev.jzadi.lcl.plugins.models.services;

import java.io.IOException;
import java.util.List;

import dev.jzadi.lcl.plugins.models.Project;

/**
 * Interface exposant les services liés à la gestion d'un projet SCM
 * 
 * @author Joachim
 * @version 1.0
 *
 */
public interface IGitService {

	/**
	 * Permet de recuper la liste de tous ,les projets du referentiel Distant
	 * 
	 * @return La liste de tous les projets
	 * @throws IOException 
	 */
	List<Project> allProjects() throws IOException;

	/**
	 * Permet de recuperer la liste de tous les noms de projets
	 * 
	 * @return Le liste de nom de tous les projets
	 * @throws IOException 
	 */
	List<String> allProjectsNames() throws IOException;
}
