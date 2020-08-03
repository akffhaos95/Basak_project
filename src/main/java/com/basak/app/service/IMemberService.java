package com.basak.app.service;

import java.util.List;

import com.basak.app.model.MemberVO;

public interface IMemberService {
	//ȸ�� ���
	public List<MemberVO> memberList();
	//ȸ�� ��� ó��
	public void insertMember(MemberVO vo);
	//ȸ�� ��� ���̵� �ߺ� üũ
	public boolean writeCheck(MemberVO vo);
	//���� ���� üũ
	public boolean authCheck(int memberAuth);
	//�α���
	public MemberVO login(MemberVO vo);
	//���� ����
	void updateAuth(String memberId);
	//ȸ�� ���� ��ȸ
	MemberVO getMemberInfo(String memberId);
	//��й�ȣ üũ
	boolean checkPw(MemberVO vo);
	//ȸ�� ���� ����
	void updateMember(MemberVO vo);
}