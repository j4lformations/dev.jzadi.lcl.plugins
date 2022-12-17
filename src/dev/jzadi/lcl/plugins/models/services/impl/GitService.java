package dev.jzadi.lcl.plugins.models.services.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.jzadi.lcl.plugins.models.Project;
import dev.jzadi.lcl.plugins.models.services.IGitService;

public class GitService implements IGitService {

	private final String URI_API;
	private final String URI_PROJECT;
	private final String USERNAME;
	private final String PASSWORD;

	public GitService() {
		ResourceBundle props = ResourceBundle.getBundle("dev.jzadi.lcl.plugins.resources.params");
		URI_API = props.getString("uri_api");
		URI_PROJECT = props.getString("uri_project");
		USERNAME = props.getString("username");
		PASSWORD = props.getString("passowrd");
	}

	@Override
	public List<Project> allProjects() throws IOException {
		URL url = new URL(URI_API);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("accept", "application/json");
		connection.setRequestProperty(USERNAME, PASSWORD);

		InputStream response = connection.getInputStream();
		ObjectMapper mapper = new ObjectMapper();

		return mapper.readValue(response, new TypeReference<List<Project>>() {
		});
	}

	@Override
	public List<String> allProjectsNames() throws IOException {
		List<String> names = allProjects().stream().map(p->p.getName()).map(p->p.toLowerCase()).collect(Collectors.toList());
		Collections.sort(names);
		return names;
	}
}
