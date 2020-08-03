package com.basak.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.basak.app.dao.IRecommendRepository;
import com.basak.app.model.PagingVO;
import com.basak.app.model.RecommendVO;

@Service
public class RecommendService implements IRecommendService {

	@Autowired
	@Qualifier("IRecommendRepository")
	IRecommendRepository recommendRepository;

	//페이징을 위한 전체 카운트
	@Override
	public int countRecommend() {
		return recommendRepository.countRecommend();
	}

	//페이징 처리 후 게시글 조회
	@Override
	public List<RecommendVO> selectRecommend(PagingVO vo) {
		return recommendRepository.selectRecommend(vo);
	}

	//추천 저장
	@Override
	public void insertRecommend(RecommendVO recommend) {
		recommendRepository.insertRecommend(recommend);
	}

	//추천 삭제
	@Override
	public void deleteRecommend(RecommendVO recommend) {
		recommendRepository.deleteRecommend(recommend);
	}

	//미사용
	@Override
	public void updateRecommend(RecommendVO recommend) {
		recommendRepository.updateRecommend(recommend);		
	}
}