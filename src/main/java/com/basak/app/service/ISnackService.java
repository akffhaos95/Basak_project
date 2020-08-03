package com.basak.app.service;

import java.util.List;

import com.basak.app.model.CategoryVO;
import com.basak.app.model.CompanyVO;
import com.basak.app.model.SnackVO;

public interface ISnackService {
	/* �˻�â */
	//ȸ�纰
	List<CompanyVO> getCompanyInfo();
	//������
	List<CategoryVO> getCategoryInfo();
	
	/* ����Ʈ */
	//��ü ����Ʈ
	List<SnackVO> getSnackId(int company, int category, String search);

	/* ����ȸ */
	//ID�� ���� ���� ����
	SnackVO getSnackInfo(int snackId);
	
	/* �̻�� */
	//����
	void insertSnack(SnackVO snack);
	//����
	void updateSnack(SnackVO snack);
	//����
	void deleteSnack(int snackID);
}