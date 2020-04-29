package com.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.domain.BoardVO;
import com.board.domain.ReplyVO;
import com.board.service.ReplyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/")
public class ReplyController {
	   
	   @Inject
		 ReplyService service;
	  //@RequestParam("가져올 데이터의 이름") [데이터타입] [가져온데이터를 담을 변수]
	  //댓글 리스트
	@RequestMapping(value = "/comment_list")
	public @ResponseBody List<ReplyVO>  replyList(BoardVO vo  ,Model model ,
			@RequestParam(value = "member_nick", required = false) String member_nick,
			@RequestParam(value = "board_no", required = false) int board_no
			) throws Exception {
		log.info("===== ReplyController :: replyList() invoked.");
	
		List<ReplyVO> replyList = service.replyList(vo.getBoard_no());

		return replyList;
	}  //end replyList
	
	//댓글 삽입
	@RequestMapping(value ="/comment_insert")
	   public @ResponseBody void replyInsert( @ModelAttribute("ReplyVO") ReplyVO vo ) throws Exception{
		log.info("===== ReplyController :: replyInsert() invoked.");
		
		service.replyCreate(vo);
	   }  //replyInsert
	
	//댓글 수정
	@RequestMapping(value = "/comment_update")
	   public @ResponseBody void getReplyUpdate(
			   @ModelAttribute("ReplyVO") ReplyVO vo, Model model,
			   @RequestParam(value = "member_nick", required = false) String  member_nick,
	           @RequestParam(value = "modcomment", required = false) String comment 
	          ) throws Exception {
		log.info("===== ReplyController :: getReplyUpdate() invoked.");
		
			vo.setComment(comment);
			service.replyUpdate(vo);
			
	      
	   }  //replyUpdate



	   @RequestMapping(value = "/comment_delete")
		public @ResponseBody String replyDelete(
				@ModelAttribute("ReplyVO") ReplyVO vo, Model model,
		           @RequestParam(value = "member_nick", required = false) String  member_nick) throws Exception {
		   log.info("===== ReplyController :: replyDelete() invoked.");
		   
			return service.replyDelete(member_nick);
		
	      
	   }  //replyDelete

	
	 


}
