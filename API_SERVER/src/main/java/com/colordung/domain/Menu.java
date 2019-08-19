package com.colordung.domain;

import java.util.ArrayList;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
/**
 * 메뉴 모델 
 * @author colordung
 *
 */
public class Menu {
	private int menuSeq;								/* 메뉴순번 */
	private int parentMenuSeq;							/* 상위메뉴순번 */
	private String menuName;							/* 메뉴명 */
	private String menuUrl;								/* 메뉴URL */
	private String useYn;								/* 사용여부 */
	private String memo;								/* 메모 */
	private int menuSort;								/* 메뉴순서 */
	private Date creDate;								/* 생성일자 */
	private Date updDate;								/* 수정일자 */
	private ArrayList<Menu> subMenus;					/* sub menu */
}
