package com.colordung.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
/**
 * 사용자검색 모델
 * @author colordung
 *
 */
public class SearchMaterials {
	private String searchKind;					/* 검색구분 */
	private String searchText;					/* 검색어 */
	private int page;							/* 현재페이지 */
	private int pageSize;						/* 페이지사이즈 */
}

