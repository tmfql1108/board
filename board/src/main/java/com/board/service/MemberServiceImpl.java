package com.board.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.domain.MemberVO;
import com.board.persistence.MemberDAO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
	
	@Inject 
	private MemberDAO dao;

	//회원 가입
	@Override
	public void memberRegister(MemberVO vo) throws Exception {
		log.info("===== MemberServiceImpl :: register() invoked.");	
		dao.memberRegister(vo);
	}  //end register

	//로그인
	@Override
	public MemberVO login(MemberVO vo) throws Exception {
		log.info("===== MemberServiceImpl :: login() invoked.");	
		
		return dao.login(vo);
	}  //end login

	
	@Override
	public void memberModify(MemberVO vo) throws Exception {
		log.info("===== MemberServiceImpl :memberModify(MemberVO) invoked.");	
		
		dao.memberModify(vo);
	}  //end memberModify

	//회원 탈퇴
	@Override
	public void withdrawal(MemberVO vo) throws Exception {
		log.info("===== MemberServiceImpl :withdrawal(MemberVO) invoked.");	
		
		dao.withdrawal(vo);
	}  //withdrawal

	
	//회원 아이디 중복 체크
	@Override
	public MemberVO idCheck(String member_id) throws Exception {
		log.info("===== MemberServiceImpl :idCheck(MemberVO) invoked.");	
		
		return dao.idCheck(member_id);
	}

	//회원 닉네임 중복 체크
	@Override
	public MemberVO nickCheck(String member_nick) throws Exception {
		log.info("===== MemberServiceImpl :nickCheck(MemberVO) invoked.");	
		
		return dao.nickCheck(member_nick);
	}

	
}//end class
