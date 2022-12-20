package dev.jzadi.lcl.plugins.services.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.CheckoutCommand;
import org.eclipse.jgit.api.CreateBranchCommand;
import org.eclipse.jgit.api.CreateBranchCommand.SetupUpstreamMode;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.ProgressMonitor;
import org.eclipse.jgit.lib.TextProgressMonitor;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.jzadi.lcl.plugins.models.Project;
import dev.jzadi.lcl.plugins.services.IGitService;

public class GitService implements IGitService {

	private final String URI_API;
	private final String URI_PROJECT;
	private final String USERNAME;
	private final String PASSWORD;
	private final CredentialsProvider PROVIDER;
	private final ProgressMonitor MONITOR;

	public GitService() {
		ResourceBundle props = ResourceBundle.getBundle("dev.jzadi.lcl.plugins.resources.params");
		URI_API = props.getString("uri_api");
		URI_PROJECT = props.getString("uri_project");
		USERNAME = props.getString("username");
		PASSWORD = props.getString("passowrd");
		PROVIDER = new UsernamePasswordCredentialsProvider(USERNAME, PASSWORD);
		MONITOR = new TextProgressMonitor(new PrintWriter(System.out));
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
		List<String> names = allProjects().stream().map(p -> p.getName()).map(p -> p.toLowerCase())
				.collect(Collectors.toList());
		Collections.sort(names);
		return names;
	}

	@Override
	public List<String> allBranchsNames(String project) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWorkSpace() {
		return System.getProperty("osgi.instance.area.default").substring(6);
	}

	@Override
	public void cloneDepot(String depot) throws IOException, GitAPIException {
		File appPath = new File(String.format("%s%s", getWorkSpace(), depot));

		if (appPath.exists()) {
			System.out.println(String.format("SUPPRESSION DU DEPOT %s", depot));
			FileUtils.deleteDirectory(appPath);
			System.out.println(String.format("SUPPRESSION DU DEPOT %s OK", depot));
		}

		final String URI_DEPOT = String.format("%s%s.git", URI_PROJECT, depot);

		try {
			System.out.println("\nDEBUT DE CLONAGE %s");
			Git.cloneRepository().setURI(URI_DEPOT).setDirectory(appPath).setCredentialsProvider(PROVIDER)
					.setProgressMonitor(MONITOR).call();
			
			System.out.println("CLONAGE OK");
		} catch (Exception e) {
			System.out.println("\nCLONAGE NOK : " + e.getMessage());
		}
	}

	@Override
	public void createNewBranch(String branch, String depot) throws IOException, GitAPIException {
		File appPath = new File(String.format("%s%s", getWorkSpace(), depot));
		try {
			System.out.println("\nDEBUT CREATION DE LA BRANCHE " + branch);
			Git git = Git.open(appPath);

			// Creation de la branche locale
			CreateBranchCommand branchCommand = git.branchCreate();
			branchCommand.setName(branch).setUpstreamMode(SetupUpstreamMode.TRACK).setForce(true).call();

			// Creation de la branche remote si elle n'existe pas
			PushCommand pushCommand = git.push();
			pushCommand.setCredentialsProvider(PROVIDER).setProgressMonitor(MONITOR).setRemote("origin")
					.setRefSpecs(new RefSpec(branch)).call();

			CheckoutCommand checkoutCommand = git.checkout();
			checkoutCommand.setName(branch).call();

			System.out.println("CREATION DE LA BRANCHE OK");

			git.close();
		} catch (Exception e) {
			System.err.println("CREATION DE LA BRANCHE NOK\n");
			e.printStackTrace();
		}
	}
}
