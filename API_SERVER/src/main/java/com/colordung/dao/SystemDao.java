package com.colordung.dao;

import java.util.HashMap;
import java.util.List;

import com.colordung.domain.Menu;


public interface SystemDao {

	List<Menu> getMenuAllList();
	
	List<Menu> getMenuList();
	
	Menu getMenu(String menuSeq);
	
	int addMenu(Menu menu);
	
	int updateMenu(Menu menu);
	
	int deleteMenu(String menuSeq);
	
	int getDuplicationMenuCheck(Menu menu);

	int updateMenuSortMinus(HashMap<String, Object> params);

	int updateMenuSortPlus(HashMap<String, Object> params);

}

