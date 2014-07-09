package com.trupt.itrainz.util;

import java.util.ArrayList;

import com.trupt.itrainz.model.Menu;
import com.trupt.itrainz.ui.fragment.FragmentEnum;

public class MenuUtil {

	public static ArrayList<Menu> getMenus() {
		ArrayList<Menu> menus = new ArrayList<>();
		menus.add(new Menu("PNR Status", FragmentEnum.PNR_STATUS_REQUEST));
		menus.add(new Menu("Train Between Station", FragmentEnum.HOME));
		menus.add(new Menu("Seat Availability", FragmentEnum.HOME));
		menus.add(new Menu("Help", FragmentEnum.HOME));
		return menus;
	}
	
}
