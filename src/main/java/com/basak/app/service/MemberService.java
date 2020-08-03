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
	
	//회원 목록
	@Override
	public List<MemberVO> memberList() {
		return memberDao.memberList();
	}
	//회원 등록 처리
	@Override
	public void insertMember(MemberVO vo) {
		memberDao.insertMember(vo);	
	}
	//회원 등록 아이디 중복 체크
	@Override
	public boolean writeCheck(MemberVO vo) {
		return memberDao.writeCheck(vo);
	}
	//메일 인증 체크
	@Override
	public boolean authCheck(int memberAuth) {
		return memberDao.authCheck(memberAuth);
	}
	//로그인
	@Override
	public MemberVO login(MemberVO vo) {
		return memberDao.login(vo);
	}
	//권한 변경
	@Override
	public void updateAuth(String memberId) {
		memberDao.updateAuth(memberId);
	}
	//회원 정보 조회
	@Override
	public MemberVO getMemberInfo(String memberId) {
		return memberDao.getMemberInfo(memberId);
	}
	//비밀번호 체크
	@Override
	public boolean checkPw(MemberVO vo) {
		return memberDao.checkPw(vo);
	}
	//회원 정보 수정
	@Override
	public void updateMember(MemberVO vo) {
		memberDao.updateMember(vo);
	}
}
