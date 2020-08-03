package com.basak.app.dao;

import java.util.List;

import com.basak.app.model.PagingVO;
import com.basak.app.model.RecommendVO;

public interface IRecommendRepository {
	//����¡�� ���� ��ü ī��Ʈ
	int countRecommend();
	//����¡ ó�� �� �Խñ� ��ȸ
	List<RecommendVO> selectRecommend(PagingVO vo);
	//��õ ����
	void insertRecommend(RecommendVO recommend);
	//��õ ����
	void deleteRecommend(RecommendVO recommend);
	//�̻��
	void updateRecommend(RecommendVO recommend);
}