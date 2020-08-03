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

	//����¡�� ���� ��ü ī��Ʈ
	@Override
	public int countRecommend() {
		return recommendRepository.countRecommend();
	}

	//����¡ ó�� �� �Խñ� ��ȸ
	@Override
	public List<RecommendVO> selectRecommend(PagingVO vo) {
		return recommendRepository.selectRecommend(vo);
	}

	//��õ ����
	@Override
	public void insertRecommend(RecommendVO recommend) {
		recommendRepository.insertRecommend(recommend);
	}

	//��õ ����
	@Override
	public void deleteRecommend(RecommendVO recommend) {
		recommendRepository.deleteRecommend(recommend);
	}

	//�̻��
	@Override
	public void updateRecommend(RecommendVO recommend) {
		recommendRepository.updateRecommend(recommend);		
	}
}