package com.colordung.service;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.codec.language.bm.Lang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colordung.dao.SystemDao;
import com.colordung.domain.Menu;

@Service
public class SystemService {
	@Autowired
	private SystemDao systemDao;
	
	/**
	 * 전체 메뉴 목록 조회 
	 * @return
	 */
	public List<Menu> getMenuAllList() {
		return systemDao.getMenuAllList();
	}
	
	/**
	 * 사용중인 메뉴 목록 조회 
	 * @return
	 */
	public List<Menu> getMenuList() {
		return systemDao.getMenuList();
	}

	/**
	 * 메뉴 상세 조회 
	 * @param menuSeq
	 * @return
	 */
	public Menu getMenu(String menuSeq) {
		return systemDao.getMenu(menuSeq);
	}
	
	/**
	 * 메뉴 저장 
	 * @param menu
	 * @return
	 */
	@Transactional
	public int saveMenu(Menu menu) {
		int returlVal = 0;
		
		if( menu.getMenuSeq() == 0 ){
			returlVal = systemDao.addMenu(menu);
		}else{
			
			Menu oldMenu = systemDao.getMenu(Integer.toString(menu.getMenuSeq()));
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("parentMenuSeq", menu.getParentMenuSeq());
			params.put("oldMenuSort", oldMenu.getMenuSort());
			params.put("newMenuSort", menu.getMenuSort());
			// 메뉴 sort 업데이트
			systemDao.updateMenuSortMinus(params);
			systemDao.updateMenuSortPlus(params);
			returlVal = systemDao.updateMenu(menu);
		}
		
		return menu.getMenuSeq();
	}

	/**
	 * 메뉴 삭제 
	 * @param menuSeq
	 * @return
	 */
	public int deleteMenu(String menuSeq) {
		return systemDao.deleteMenu(menuSeq);
	}	
	
	/**
	 * 메뉴명 중복 확인 
	 * @param menuName
	 * @return
	 */
	public int getDuplicationMenuCheck(Menu menu) {
		return systemDao.getDuplicationMenuCheck(menu);
	}


}
