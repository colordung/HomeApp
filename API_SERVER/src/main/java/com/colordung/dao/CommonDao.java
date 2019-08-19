package com.colordung.dao;

import org.apache.ibatis.annotations.Select;

public interface CommonDao {
	@Select("SELECT FOUND_ROWS()")
	int pagingTotal();
}

