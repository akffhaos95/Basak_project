package com.basak.app.service;

import java.util.List;

import com.basak.app.model.PagingVO;
import com.basak.app.model.RecommendVO;

public interface IRecommendService {
	//����¡�� ���� ��ü ī��Ʈ
	int countRecommend();
	//����¡ ó�� �� �Խñ� ��ȸ
	List<RecommendVO> selectRecommend(PagingVO vo);
	//��õ ����
	void insertRecommend(RecommendVO recommend);
	//��õ ����
	void updateRecommend(RecommendVO recommend);
	//�̻��
	void deleteRecommend(RecommendVO recommend);
}