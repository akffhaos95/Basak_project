package com.basak.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.basak.app.model.ChartMap;
import com.basak.app.model.PagingVO;
import com.basak.app.model.ReviewVO;

public interface IReviewRepository {
	//페이징을 위한 전체 카운트
	int countReview(@Param("snackId") int snackId);
	//페이징 처리 후 게시글 조회
	List<ReviewVO> selectReview(@Param("snackId") int snackId, @Param("vo") PagingVO vo);
	
	/* 리뷰 페이지 */
	//리뷰 삭제를 위한 snackId 값 처리
	int getSnackId(int reviewId);
	//평균 별점
	double getSnackAvg(int snackId);
	//리뷰 저장
	void insertReview(ReviewVO rev);
	//리뷰 삭제
	void deleteReview(ReviewVO rev);
	
	List<ChartMap> getSnackChart();
	List<ChartMap> getCompanyChart();
	List<ChartMap> getCategoryChart();
	
	double getMemberAvg(String memberId);
	int countMemberReview(String memberId);
	List<ReviewVO> selectMemberReview(@Param("memberId") String memberId, @Param("vo") PagingVO vo);

	/* 미사용 */
	//리뷰 수정
	void updateReview(ReviewVO rev);
}