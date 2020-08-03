package com.basak.app.dao;

import java.util.List;

import com.basak.app.model.MemberVO;

public interface IMemberDAO {
	//회원 목록
	public List<MemberVO> memberList();
	//회원가입
	public void insertMember(MemberVO vo);
	//회원가입 아이디 중복 체크
	public boolean writeCheck(MemberVO vo); 
	//메일 인증 체크
	public boolean authCheck(int memberAuth);
	//로그인
	public MemberVO login(MemberVO vo);	
	//권한 변경
	public void updateAuth(String memberId);
	//회원 정보 조회
	public MemberVO getMemberInfo(String memberId);
	//비밀번호 체크
	public boolean checkPw(MemberVO vo);
	//회원 정보 수정
	public void updateMember(MemberVO vo);
}
