package com.basak.app.service;

import java.util.List;

import com.basak.app.model.PagingVO;
import com.basak.app.model.RecommendVO;

public interface IRecommendService {
	//페이징을 위한 전체 카운트
	int countRecommend();
	//페이징 처리 후 게시글 조회
	List<RecommendVO> selectRecommend(PagingVO vo);
	//추천 저장
	void insertRecommend(RecommendVO recommend);
	//추천 삭제
	void updateRecommend(RecommendVO recommend);
	//미사용
	void deleteRecommend(RecommendVO recommend);
}