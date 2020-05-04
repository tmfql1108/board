package com.board.controller;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.board.domain.BoardVO;
import com.board.domain.MemberVO;
import com.board.domain.PageMaker;
import com.board.domain.SearchCriteria;
import com.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	@Inject
	BoardService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(
			Locale locale,
			Model model, 
			MemberVO vo,
			@ModelAttribute("scri") SearchCriteria scri ) throws Exception {
		log.info("HomeController:: home invoke");
		 
		List<BoardVO> list = service.listSearch(scri);
		 model.addAttribute("list", list);   
		
		 PageMaker pageMaker = new PageMaker();  //객체 생성
		 pageMaker.setCri(scri);  
		 pageMaker.setTotalCnt(service.searchCnt(scri));  //개수 계산
		 
		 model.addAttribute("pageMaker", pageMaker);
		
		 return "home";
	}

}
