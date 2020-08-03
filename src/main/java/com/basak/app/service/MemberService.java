package com.basak.app.service;

import java.util.List;

import javax.inject.Inject;
import org.springframework.stereotype.Service;

import com.basak.app.dao.MemberDAO;
import com.basak.app.model.MemberVO;

@Service
public class MemberService implements IMemberService{

	@Inject
	MemberDAO memberDao;
	
	//ȸ�� ���
	@Override
	public List<MemberVO> memberList() {
		return memberDao.memberList();
	}
	//ȸ�� ��� ó��
	@Override
	public void insertMember(MemberVO vo) {
		memberDao.insertMember(vo);	
	}
	//ȸ�� ��� ���̵� �ߺ� üũ
	@Override
	public boolean writeCheck(MemberVO vo) {
		return memberDao.writeCheck(vo);
	}
	//���� ���� üũ
	@Override
	public boolean authCheck(int memberAuth) {
		return memberDao.authCheck(memberAuth);
	}
	//�α���
	@Override
	public MemberVO login(MemberVO vo) {
		return memberDao.login(vo);
	}
	//���� ����
	@Override
	public void updateAuth(String memberId) {
		memberDao.updateAuth(memberId);
	}
	//ȸ�� ���� ��ȸ
	@Override
	public MemberVO getMemberInfo(String memberId) {
		return memberDao.getMemberInfo(memberId);
	}
	//��й�ȣ üũ
	@Override
	public boolean checkPw(MemberVO vo) {
		return memberDao.checkPw(vo);
	}
	//ȸ�� ���� ����
	@Override
	public void updateMember(MemberVO vo) {
		memberDao.updateMember(vo);
	}
}
