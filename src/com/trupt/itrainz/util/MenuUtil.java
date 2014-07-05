package com.trupt.itrainz.util;

import java.util.ArrayList;

import com.trupt.itrainz.model.Menu;

public class MenuUtil {

	public static ArrayList<Menu> getMenus() {
		ArrayList<Menu> menus = new ArrayList<>();
		Menu menu = new Menu("PNR Status");
		menus.add(menu);
		return menus;
	}
	
}
