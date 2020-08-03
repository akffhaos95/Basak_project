package com.basak.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.basak.app.dao.IReviewRepository;
import com.basak.app.model.ChartMap;
import com.basak.app.model.PagingVO;
import com.basak.app.model.ReviewVO;
import com.basak.app.service.IReviewService;

@Service
public class ReviewService implements IReviewService {
	
	@Autowired
	@Qualifier("IReviewRepository")
	IReviewRepository reviewRepository;

	//페이징 처리를 위한 전체 카운트
	@Override
	public int countReview(int snackId) {
		return reviewRepository.countReview(snackId);
	}
	//페이징 처리 후 게시글 조회
	@Override
	public List<ReviewVO> selectReview(int snackId, PagingVO vo){
		return reviewRepository.selectReview(snackId, vo);
	}

	/* 리뷰 페이지 */
	//리뷰 삭제를 위한 snackId 값 처리
	@Override
	public int getSnackId(int reviewId) {
		return reviewRepository.getSnackId(reviewId);
	}
	//평균 별점
	@Override
	public double getSnackAvg(int snackId) {
		return reviewRepository.getSnackAvg(snackId);
	}
	//리뷰 저장
	@Override
	public void insertReview(ReviewVO review) {
		reviewRepository.insertReview(review);
	}
	//리뷰 삭제
	@Override
	public void deleteReview(ReviewVO review) {
		reviewRepository.deleteReview(review);
	}
	
	/* 미사용 */
	//리뷰 수정
	@Override
	public void updateReview(ReviewVO review) {
		reviewRepository.updateReview(review);
	}
	
	@Override
	public List<ChartMap> getSnackChart(){
		return reviewRepository.getSnackChart();
	}
	
	@Override
	public List<ChartMap> getCompanyChart(){
		return reviewRepository.getCompanyChart();
	}
	
	@Override
	public List<ChartMap> getCategoryChart(){
		return reviewRepository.getCategoryChart();
	}
	
	@Override
	public double getMemberAvg(String memberId) {
		return reviewRepository.getMemberAvg(memberId);
	}
	
	@Override
	public int countMemberReview(String memberId) {
		return reviewRepository.countMemberReview(memberId);
	}
	
	@Override
	public List<ReviewVO> selectMemberReview(String memberId, PagingVO vo){
		return reviewRepository.selectMemberReview(memberId, vo);
	}
}