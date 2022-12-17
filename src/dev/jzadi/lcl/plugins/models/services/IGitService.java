package dev.jzadi.lcl.plugins.models.services;

import java.util.List;

import dev.jzadi.lcl.plugins.models.Project;

public interface IGitService {

	List<Project> allProjects();

	List<String> allProjectsNames();
}
