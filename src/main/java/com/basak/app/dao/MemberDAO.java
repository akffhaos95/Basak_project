package com.basak.app.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.basak.app.Hash;
import com.basak.app.model.MemberVO;

@Repository
public class MemberDAO implements IMemberDAO{

	@Inject
	SqlSession sqlSession;
	Hash hash = new Hash();
	
	@Override
	public List<MemberVO> memberList() {
		return sqlSession.selectList("member.memberList");
	}
	
	@Override
	public void insertMember(MemberVO vo) {
		vo.setMemberPw(hash.hash(vo.getMemberPw()));
		sqlSession.insert("member.insertMember", vo);
	}
	
	@Override
	public boolean writeCheck(MemberVO vo) {
		MemberVO vo2 = sqlSession.selectOne("member.writeCheck", vo);
		return (vo2 == null) ? true : false;
	}
	
	@Override
	public boolean authCheck(int memberAuth) {
		MemberVO vo = sqlSession.selectOne("member.authCheck", memberAuth);
		return (vo == null) ? false : true;
	}
	
	@Override
	public MemberVO login(MemberVO vo) {
		vo.setMemberPw(hash.hash(vo.getMemberPw()));
		return sqlSession.selectOne("member.login", vo);
	}

	@Override
	public void updateAuth(String memberId) {
		sqlSession.update("member.updateAuth", memberId);
	}
	
	@Override
	public MemberVO getMemberInfo(String memberId) {
		return sqlSession.selectOne("member.getMemberInfo", memberId);
	}
		
	@Override
	public boolean checkPw(MemberVO vo) {
		vo.setMemberPw(hash.hash(vo.getMemberPw()));
		int count = sqlSession.selectOne("member.checkPw",vo);
		if(count == 1) {
			return true;			
		} else {
			return false;
		}
	}
		
	@Override
	public void updateMember(MemberVO vo) {
		vo.setMemberPw(hash.hash(vo.getMemberPw()));
		sqlSession.update("member.updateMember", vo);
	}
}
