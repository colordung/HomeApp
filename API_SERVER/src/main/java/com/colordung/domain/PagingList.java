package com.colordung.domain;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
/**
 * 페이징목록 모델
 * @author choijaehun
 *
 */
public class PagingList {
	private int totalRows;						/* 총 레코드 수 */
	private List<?> dataList;
}
