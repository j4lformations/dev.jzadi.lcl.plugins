package dev.jzadi.lcl.plugins.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe permettant de modeliser un projet du referentiel Git
 * 
 * @author Joachim
 * @version 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {

	private Integer id;
	private String name;
	private String defaultBranch;
	private List<String> tagList;
	private String httpUrlToRepo;
	private String webUrl;

	public Project(
			@JsonProperty("id") Integer id, 
			@JsonProperty("name") String name, 
			@JsonProperty("default_branch") String defaultBranch,
			@JsonProperty("tags") List<String> tagList, 
			@JsonProperty("url_repo") String httpUrlToRepo,
			@JsonProperty("web_url")  String webUrl
	) {
		this.id = id;
		this.name = name;
		this.defaultBranch = defaultBranch;
		this.tagList = tagList;
		this.httpUrlToRepo = httpUrlToRepo;
		this.webUrl = webUrl;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDefaultBranch() {
		return defaultBranch;
	}

	public void setDefaultBranch(String defaultBranch) {
		this.defaultBranch = defaultBranch;
	}

	public List<String> getTagList() {
		return tagList;
	}

	public void setTagList(List<String> tagList) {
		this.tagList = tagList;
	}

	public String getHttpUrlToRepo() {
		return httpUrlToRepo;
	}

	public void setHttpUrlToRepo(String httpUrlToRepo) {
		this.httpUrlToRepo = httpUrlToRepo;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", defaultBranch=" + defaultBranch + ", tagList=" + tagList
				+ ", httpUrlToRepo=" + httpUrlToRepo + ", webUrl=" + webUrl + "]";
	}
}
