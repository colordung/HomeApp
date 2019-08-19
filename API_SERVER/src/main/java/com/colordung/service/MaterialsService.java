package com.colordung.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colordung.dao.CommonDao;
import com.colordung.dao.MaterialsDao;
import com.colordung.domain.Materials;
import com.colordung.domain.PagingList;
import com.colordung.domain.SearchMaterials;

@Service
public class MaterialsService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MaterialsDao materialsDao;

    @Autowired
	private CommonDao commonDao;

    public int getCountByName(String name) {
		return materialsDao.getCountByName(name);
	}

	@Transactional
	public int saveMaterials(Materials materials) {
		int returlVal = 0;
		if( materials.getSeq() == 0 ){
			returlVal = materialsDao.addMaterials(materials);
		}else{
			returlVal = materialsDao.updateMaterials(materials);
		}
		return returlVal;
	}

	public int deleteMaterials(String seq) {
		return materialsDao.deleteMaterials(seq);
	}

	public PagingList getMaterialsList(SearchMaterials searchMaterials){
		List<Materials> dataList = materialsDao.getMaterialsList(searchMaterials);
		int totalRows = commonDao.pagingTotal();
		
		PagingList pagingList = new PagingList();
		pagingList.setTotalRows(totalRows);
		pagingList.setDataList(dataList);
		
		return pagingList;
	}

	
}
