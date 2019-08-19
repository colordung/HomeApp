package com.colordung.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
/**
 * 재료 모델 
 * @author colordung
 *
 */
public class Materials {
	private int seq;								/* 순번 */
	private String name;							/* 재료명 */
	private int carbon;								/* 탄수화물 */
	private int protein;							/* 단백질 */
	private int fat;								/* 지방 */
	private int na;									/* 나트륨 */
}
