package com.board.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class MemberDAOImpl implements MemberDAO {

	@Inject
	private SqlSession sqlSession;
	private final  String namespace ="com.board.mapper.MemberMapper";
	
	//회원가입
	@Override
	public void memberRegister(MemberVO vo) throws Exception {
		log.info("===== MemberDAOImpl :: memberRegister() invoked.");	
	
		sqlSession.insert(namespace + ".memberRegister", vo); 
	}  //end memberRegister

	//로그인
	@Override
	public MemberVO login(MemberVO vo) throws Exception {
		log.info("===== MemberDAOImpl :: login() invoked.");	
		
		 return sqlSession.selectOne(namespace +".login", vo );
	
	}  //end login

	//회원정보 수정 (비밀번호)
	@Override
	public void memberModify(MemberVO vo) throws Exception {
		log.info("===== MemberDAOImpl :: memberModify(MemberVO vo)  invoked.");
		
		sqlSession.update(namespace + ".memberModify", vo);
	}  //end memberModify

	
	//회원 삭제
	@Override
	public void withdrawal(MemberVO vo) throws Exception {
		log.info("===== MemberDAOImpl :: withdrawal(MemberVO vo)  invoked.");
		sqlSession.update(namespace + ".withdrawal", vo);
		
	}

	//회원 아이디 중복 체크
	@Override
	public MemberVO idCheck(String member_id) throws Exception {
		log.info("===== MemberDAOImpl :: idCheck(MemberVO vo)  invoked.");
		
		return sqlSession.selectOne(namespace + ".idCheck", member_id );
	}

	//회원 닉네임 중복 체크
	@Override
	public MemberVO nickCheck(String member_nick) throws Exception {
		log.info("===== MemberDAOImpl :: nickCheck(MemberVO vo)  invoked.");
	
		return sqlSession.selectOne(namespace + ".nickCheck", member_nick );
	}

}  //end class
