package com.demos.demo.comments.model;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private List<String> data;

	public Data(List<String> data) {
		super();
		this.data = data;
	}

	public Data() {
		super();
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}
	
	
}
