package com.trupt.itrainz.model;

import com.trupt.itrainz.ui.fragment.FragmentEnum;

public class Menu {

	private String name;
	private FragmentEnum fragmentEnum;
	
	public Menu(String name, FragmentEnum fragmentEnum) {
		this.name = name;
		this.fragmentEnum = fragmentEnum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FragmentEnum getFragmentEnum() {
		return fragmentEnum;
	}

	public void setFragmentEnum(FragmentEnum fragmentEnum) {
		this.fragmentEnum = fragmentEnum;
	}

	@Override
	public String toString() {
		return "Menu [name=" + name + ", fragmentEnum=" + fragmentEnum + "]";
	}

}
