package dev.jzadi.lcl.plugins.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
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
	private String default_branch;
	private List<String> tag_list;
	private String http_url_to_repo;
	private String web_url;

	public Project() {
	}

	@JsonCreator
	public Project(Integer id, String name, String default_branch, @JsonProperty("tags") List<String> tag_list,
			String http_url_to_repo, String web_url) {
		this.id = id;
		this.name = name;
		this.default_branch = default_branch;
		this.tag_list = tag_list;
		this.http_url_to_repo = http_url_to_repo;
		this.web_url = web_url;
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

	public String getDefault_branch() {
		return default_branch;
	}

	public void setDefault_branch(String default_branch) {
		this.default_branch = default_branch;
	}

	public List<String> getTag_list() {
		return tag_list;
	}

	public void setTag_list(List<String> tag_list) {
		this.tag_list = tag_list;
	}

	public String getHttp_url_to_repo() {
		return http_url_to_repo;
	}

	public void setHttp_url_to_repo(String http_url_to_repo) {
		this.http_url_to_repo = http_url_to_repo;
	}

	public String getWeb_url() {
		return web_url;
	}

	public void setWeb_url(String web_url) {
		this.web_url = web_url;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + ", default_branch=" + default_branch + ", tag_list=" + tag_list
				+ ", http_url_to_repo=" + http_url_to_repo + ", web_url=" + web_url + "]";
	}
}
