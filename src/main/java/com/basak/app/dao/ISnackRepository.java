package com.basak.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.basak.app.model.CategoryVO;
import com.basak.app.model.CompanyVO;
import com.basak.app.model.SnackVO;

public interface ISnackRepository {
	/* �˻�â */
	//ȸ�纰
	List<CompanyVO> getCompanyInfo();
	//������
	List<CategoryVO> getCategoryInfo();
	
	/* ����Ʈ */
	//��ü ����Ʈ
	List<SnackVO> getSnackId(@Param("company") int company, @Param("category") int category, @Param("search") String search);
	
	/* ȸ�纰, ������ �˻����� �����Ͽ����� return���� null�� ���� ���ذ� */
	/* ����� ȸ��, ����Ʈ�� ������ ���⶧���� ���� ó�� */
	// �̻�� : ȸ�纰 ����Ʈ
	List<SnackVO> getSnackCompany(@Param("company") int company);
	
	// �̻�� : ������ ����Ʈ
	List<SnackVO> getSnackCategory(@Param("category") int category);
	
	/* ����ȸ */
	//ID�� ���� ���� ����
	SnackVO getSnackInfo(@Param("snackId") int snackId);
	
	/* �̻�� */
	//����
	void insertSnack(SnackVO snack);
	//����
	void updateSnack(SnackVO snack);
	//����
	void deleteSnack(@Param("snackID") int snackId);
}
