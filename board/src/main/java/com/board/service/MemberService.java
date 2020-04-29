package com.board.service;

import com.board.domain.MemberVO;

public interface MemberService {

	public void memberRegister(MemberVO vo) throws Exception;  //회원 가입
	
	public MemberVO  login(MemberVO vo) throws Exception;  //로그인
	
	public void memberModify(MemberVO vo) throws Exception;  //회원정보 수정 (비밀번호)

	public void withdrawal(MemberVO vo) throws Exception;   //회원 삭제
	
	public MemberVO idCheck(String member_id) throws Exception;  //회원 아이디 중복체크
	
	public MemberVO nickCheck(String member_nick) throws Exception;  //회원 아이디 중복체크
} //end interface
