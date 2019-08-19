package com.colordung.dao;

import java.util.List;

import com.colordung.domain.Materials;
import com.colordung.domain.SearchMaterials;

public interface MaterialsDao {
	
	int getCountByName(String name);
	
	int addMaterials(Materials materials);
	
	int updateMaterials(Materials materials);
	
	int deleteMaterials(String seq);

	List<Materials> getMaterialsList(SearchMaterials searchMaterials);

}
