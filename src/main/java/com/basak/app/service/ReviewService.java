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

	//����¡ ó���� ���� ��ü ī��Ʈ
	@Override
	public int countReview(int snackId) {
		return reviewRepository.countReview(snackId);
	}
	//����¡ ó�� �� �Խñ� ��ȸ
	@Override
	public List<ReviewVO> selectReview(int snackId, PagingVO vo){
		return reviewRepository.selectReview(snackId, vo);
	}

	/* ���� ������ */
	//���� ������ ���� snackId �� ó��
	@Override
	public int getSnackId(int reviewId) {
		return reviewRepository.getSnackId(reviewId);
	}
	//��� ����
	@Override
	public double getSnackAvg(int snackId) {
		return reviewRepository.getSnackAvg(snackId);
	}
	//���� ����
	@Override
	public void insertReview(ReviewVO review) {
		reviewRepository.insertReview(review);
	}
	//���� ����
	@Override
	public void deleteReview(ReviewVO review) {
		reviewRepository.deleteReview(review);
	}
	
	/* �̻�� */
	//���� ����
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