package com.basak.app.dao;

import java.util.List;

import com.basak.app.model.MemberVO;

public interface IMemberDAO {
	//ȸ�� ���
	public List<MemberVO> memberList();
	//ȸ������
	public void insertMember(MemberVO vo);
	//ȸ������ ���̵� �ߺ� üũ
	public boolean writeCheck(MemberVO vo); 
	//���� ���� üũ
	public boolean authCheck(int memberAuth);
	//�α���
	public MemberVO login(MemberVO vo);	
	//���� ����
	public void updateAuth(String memberId);
	//ȸ�� ���� ��ȸ
	public MemberVO getMemberInfo(String memberId);
	//��й�ȣ üũ
	public boolean checkPw(MemberVO vo);
	//ȸ�� ���� ����
	public void updateMember(MemberVO vo);
}
