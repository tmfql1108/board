package com.board.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.BoardVO;
import com.board.domain.PageMaker;
import com.board.domain.SearchCriteria;
import com.board.service.BoardService;
import com.board.service.ReplyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Inject
	BoardService service;
	@Inject
	ReplyService replyrervice;
	
	//글목록 + 페이징 + 검색
	@RequestMapping(value ="/listSearch", method = RequestMethod.GET)
	public void getLlstSearch(@ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception {
		 log.info("BoardController:: getLlstSearch invoke");
		
		 List<BoardVO> list = service.listSearch(scri);
		 model.addAttribute("list", list);   
		 System.out.println(list.toString());

		 PageMaker pageMaker = new PageMaker();  //객체 생성
		 pageMaker.setCri(scri);  
		 pageMaker.setTotalCnt(service.searchCnt(scri));  //개수 계산
		 
		 model.addAttribute("pageMaker", pageMaker);
	
	}//listPage
	
	//게시물 작성
	@RequestMapping ( value ="/write", method = RequestMethod.GET)
	public void getWirte(HttpSession session, Model model) throws Exception {
		 log.info("BoardController:: getWirte invoke");
		 
		 Object loginInfo = session.getAttribute("member");
		 
		 if( loginInfo  == null) {  //member 컨트롤로에서 세션값을 가져와서 로그인 여부 확인
			 model.addAttribute("msg", false);  
		 }  //end if
	}  //end getWirte
	
	//게시물 작성
	@RequestMapping ( value = "/write" , method = RequestMethod.POST)
	public String postWirte( BoardVO vo ) 
			throws Exception  {
		service.write(vo);
		
		return "redirect:/board/listSearch"; 
	}  //end postWirte
	
	//게시물 조회용 get 메서드 작성
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public void getDetail(
			@RequestParam("board_no") int board_no, Model model , @ModelAttribute("scri") SearchCriteria scri) 
					throws Exception{
		
		service.updateViewCnt(board_no);
		BoardVO vo = service.viewDetail(board_no);

		model.addAttribute("view", vo);
		model.addAttribute("scri", scri);
		//게시물 번호를 입력받아 데이터 상세 정보 가져오기
	}  //end getDetail
	
	//게시물 수정
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void getModify (
			@RequestParam("board_no") int board_no, Model model)
					throws Exception {
		
		BoardVO vo = service.viewDetail(board_no);
		model.addAttribute("view", vo);
		
	}  //end getModify
	
	//게시물 수정
	@RequestMapping(value="/modify", method = RequestMethod.POST)
	public String postModify(BoardVO vo,  @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) 
			throws Exception {
		
		service.modify(vo);  //뷰에서 컨트롤러로 넘어온 데이터(BoardVO)를 이용해 수정을 끝내고
		
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		return "redirect:/board/listSearch";  //해당 게시글 조회 페이지로 이동
	}  //end postModify
	
	//게시글 삭제
	@RequestMapping(value ="/delete", method = RequestMethod.GET)
	public String getDelete(
			@RequestParam("board_no") int board_no,  @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr)
					throws Exception{
		
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		service.delete(board_no);
		return "redirect:/board/listSearch";
		
	}
	
}
