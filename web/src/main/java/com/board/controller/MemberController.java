package com.board.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.MemberVO;
import com.board.service.MemberService;
import com.board.util.sha256;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/member/*")
public class MemberController {

	@Inject
	private MemberService service;
	
	// 회원가입 get
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void getRegister() throws Exception {
		log.info("===== MemberController :: getRegister() invoked.");
	} // end getRegister

	// 회원가입 post
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String postRegister(MemberVO vo) throws Exception {
		log.info("===== MemberController :: postRegister(MemberVO vo) invoked.");
		
		String encryPassword = sha256.encrypt(vo.getMember_pwd());  //패스워드 암호화
		vo.setMember_pwd(encryPassword);
		
		service.memberRegister(vo);
		return "redirect:/";

	} // end postRegister

	// 로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
			MemberVO vo, 
			HttpServletRequest req, 
			RedirectAttributes rttr) throws Exception {
		log.info("===== MemberController :: login(MemberVO vo, HttpServletRequest req) invoked.");

		HttpSession session = req.getSession(); //세션

		String member_pwd = vo.getMember_pwd();
		vo.setMember_pwd(sha256.encrypt(member_pwd));
		
		System.out.println("+++++member_pwd encoding : " + vo.getMember_pwd());
		
		MemberVO login = service.login(vo);
		String loginkey = (String)session.getAttribute("member_nick");
		
		if (login == null) {
			log.info("===== 로그인 실패.");
			rttr.addFlashAttribute("msg", false);
			session.setAttribute("member", null); // 로그인 실패시 정보 x
		} else {
			log.info("===== 로그인 성공.");
			session.setAttribute("member", login); // 로그인 성공
			return "redirect:/";
		} // if-else

		return "redirect:/";
	} // end login

	
	//회원 아이디 중복 확인
	@ResponseBody  //메소드에서 리턴되는 값은 View 를 통해서 출력되지 않고 HTTP Response Body 에 직접 쓰여짐
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public int postIdCheck(HttpServletRequest req) throws Exception {
		log.info("===== MemberController :: postIdCheck  invoked.");
		
		String member_id = req.getParameter("member_id");
		MemberVO idCheck = service.idCheck(member_id);
		
		int idResult = 0;
		
		if(idCheck != null) {
			idResult = 1;
		} //if  
		return idResult;
	}// end postIdCheck
	
	//회원 닉네임 중복 확인
	@ResponseBody  
	@RequestMapping(value = "/nickCheck", method = RequestMethod.POST)
	public int postNickCheck(HttpServletRequest req, MemberVO vo) throws Exception {
		log.info("===== MemberController :: postNickCheck  invoked.");
		
		String member_nick = req.getParameter("member_nick");
		MemberVO nickCheck = service.nickCheck(member_nick);
		
		int nickResult = 0;
		
		if(nickCheck != null) {
			nickResult = 1;
		} //if
		return nickResult;
	}// end postNickCheck
	
	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {
		log.info("===== MemberController :: logout invoked.");

		session.invalidate(); //세션정보 무효화

		return "redirect:/";
	} // end logout

	// 회원정보 수정 get (비밀번호)
	@RequestMapping(value = "/memberModify", method = RequestMethod.GET)
	public void getMemberModify() throws Exception {
		log.info("===== MemberController :: getMemberModify invoked.");
	} // end getModify
	
	//회원정보 수정 post
	@RequestMapping(value = "/memberModify", method = RequestMethod.POST)
	public String  postMemberModify(
			HttpSession session,
			MemberVO vo) throws Exception {
		log.info("===== MemberController :: postMemberModify invoked.");
		log.info("===== 회원 정보 수정 완료");
		
		String member_pwd = vo.getMember_pwd();
		vo.setMember_pwd(sha256.encrypt(member_pwd));
		
		System.out.println("+++++member_pwd encoding : " + vo.getMember_pwd());
		 service.memberModify(vo);
		 
		 session.invalidate();  //세션정보 무효화
		 
		 return "redirect:/";
	} //end postMemberModify
	
	//회원 탈퇴
	@RequestMapping(value = "/withdrawal", method =RequestMethod.GET )
	public void getWithdrawal() throws Exception {
		log.info("===== MemberController :: getWithdrawal invoked.");
	}
	
	//회원 탈퇴
	@RequestMapping(value = "/withdrawal", method =RequestMethod.POST )
	public String postWithdrawal(HttpSession session, MemberVO vo, RedirectAttributes rttr) throws Exception {
		log.info("===== MemberController :: postWithdrawal invoked.");
		
		MemberVO member = (MemberVO) session.getAttribute("member");  //세션 값 비교
		
		String oldPWD = member.getMember_pwd();  //기존 member에 있던 값
		String newPWD = vo.getMember_pwd();   //탈퇴 페이지에서 입력한 값
		
		if(!oldPWD.equals(newPWD)) {     
			//다른 비밀번호 입력시 에러
			rttr.addFlashAttribute("msg", false);
		}  //end if
		
		service.withdrawal(vo);
		
		session.invalidate(); //세션정보 무효화
		
		 return "redirect:/";
	}  //end postWithdrawal

}// end class
