package com.basak.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basak.app.dao.ISnackRepository;
import com.basak.app.model.CategoryVO;
import com.basak.app.model.CompanyVO;
import com.basak.app.model.SnackVO;

@Service
public class SnackService implements ISnackService {
	
	@Autowired
	@Qualifier("ISnackRepository")
	ISnackRepository snackRepository;

	/* �˻�â */
	//ȸ�纰
	@Override
	public List<CompanyVO> getCompanyInfo() {
		return snackRepository.getCompanyInfo();
	}
	//������
	@Override
	public List<CategoryVO> getCategoryInfo() {
		return snackRepository.getCategoryInfo();
	}

	/* ����Ʈ */
	//��ü ����Ʈ
	@Override
	public List<SnackVO> getSnackId(int company, int category, String search) {
		return snackRepository.getSnackId(company, category, search);
	}
	/* ȸ�纰, ������ �˻����� �����Ͽ����� return���� null�� ���� ���ذ� */
	/* ����� ȸ��, ����Ʈ�� ������ ���⶧���� ���� ó�� */
	//ȸ�纰 ����Ʈ
//	@Override
//	public List<SnackVO> getSnackCompany(int company){
//		return snackRepository.getSnackCompany(company);
//	}
	//������ ����Ʈ
//	@Override
//	public List<SnackVO> getSnackCategory(int category){
//		return snackRepository.getSnackCategory(category);
//	}
	
	/* ����ȸ */
	//ID�� ���� ���� ����
	@Override
	public SnackVO getSnackInfo(int snackId) {
		return snackRepository.getSnackInfo(snackId);
	}
	
	/* �̻�� */
	//����
	@Override
	public void insertSnack(SnackVO snack) {
		snackRepository.insertSnack(snack);
	}
	//����
	@Override
	public void updateSnack(SnackVO snack) {
		snackRepository.updateSnack(snack);
	}
	//����
	@Override
	@Transactional
	public void deleteSnack(int snackID) {
		snackRepository.deleteSnack(snackID);
	}
}