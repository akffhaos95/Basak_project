package com.basak.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.basak.app.model.CategoryVO;
import com.basak.app.model.CompanyVO;
import com.basak.app.model.SnackVO;

public interface ISnackRepository {
	/* 검색창 */
	//회사별
	List<CompanyVO> getCompanyInfo();
	//종류별
	List<CategoryVO> getCategoryInfo();
	
	/* 리스트 */
	//전체 리스트
	List<SnackVO> getSnackId(@Param("company") int company, @Param("category") int category, @Param("search") String search);
	
	/* 회사별, 종류별 검색으로 쿼리하였을때 return값이 null인 문제 미해결 */
	/* 현재는 회사, 리스트의 종류가 적기때문에 각각 처리 */
	// 미사용 : 회사별 리스트
	List<SnackVO> getSnackCompany(@Param("company") int company);
	
	// 미사용 : 종류별 리스트
	List<SnackVO> getSnackCategory(@Param("category") int category);
	
	/* 상세조회 */
	//ID에 따른 과자 정보
	SnackVO getSnackInfo(@Param("snackId") int snackId);
	
	/* 미사용 */
	//저장
	void insertSnack(SnackVO snack);
	//수정
	void updateSnack(SnackVO snack);
	//삭제
	void deleteSnack(@Param("snackID") int snackId);
}
