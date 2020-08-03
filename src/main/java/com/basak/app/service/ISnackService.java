package com.basak.app.service;

import java.util.List;

import com.basak.app.model.CategoryVO;
import com.basak.app.model.CompanyVO;
import com.basak.app.model.SnackVO;

public interface ISnackService {
	/* 검색창 */
	//회사별
	List<CompanyVO> getCompanyInfo();
	//종류별
	List<CategoryVO> getCategoryInfo();
	
	/* 리스트 */
	//전체 리스트
	List<SnackVO> getSnackId(int company, int category, String search);

	/* 상세조회 */
	//ID에 따른 과자 정보
	SnackVO getSnackInfo(int snackId);
	
	/* 미사용 */
	//저장
	void insertSnack(SnackVO snack);
	//수정
	void updateSnack(SnackVO snack);
	//삭제
	void deleteSnack(int snackID);
}