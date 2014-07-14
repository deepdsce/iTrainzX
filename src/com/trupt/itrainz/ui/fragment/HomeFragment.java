package com.trupt.itrainz.ui.fragment;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.trupt.itrainz.R;
import com.trupt.itrainz.model.Menu;
import com.trupt.itrainz.util.MenuUtil;


public class HomeFragment extends BaseFragment implements OnItemClickListener {
	
	private ListView listViewMainMenu;
	private ArrayList<Menu> listMenus;
	private MainMenuAdapter adapter;
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
		FragmentEnum fragmentEnum = null;
		switch (position) {
		case 0:
			fragmentEnum = FragmentEnum.PNR_STATUS_REQUEST;
			break;

		default:
			fragmentEnum = FragmentEnum.HOME;
			break;
		}
		if(getActivity() instanceof OnFragmentChangeRequestListener) {
			((OnFragmentChangeRequestListener)getActivity()).onFragmentAddRequest(fragmentEnum, FragmentTransitionType.REPLACE, true, null);
		}
	}
	
	@Override
	protected void init(View view) {
		listViewMainMenu = (ListView) view.findViewById(R.id.listViewMainMenu);
		listViewMainMenu.setOnItemClickListener(this);
		listMenus = new ArrayList<>();
		adapter = new MainMenuAdapter();
		listViewMainMenu.setAdapter(adapter);
	}
	
	@Override
	protected void setup() {
		listMenus.addAll(MenuUtil.getMenus());
		adapter.notifyDataSetChanged();
	}
	
	@Override
	protected int getLayoutResource() {
		return R.layout.fragment_main;
	}
	
	
	private class MainMenuAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return listMenus.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			if(view == null) {
				view = ((LayoutInflater) HomeFragment.this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.listview_main_menu, null);
			}
			TextView textViewMenuTitle = (TextView) view.findViewById(R.id.textViewMenuTitle);
			String title = listMenus.get(position).getName();
			textViewMenuTitle.setText(title);
			return view;
		}
		
	}

}
