package com.trupt.itrainz.model;

public class Menu {

	private String name;
	
	public Menu() {
	}
	
	public Menu(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Menu [name=" + name + "]";
	}

}
