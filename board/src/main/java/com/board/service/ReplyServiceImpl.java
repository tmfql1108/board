package com.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.domain.ReplyVO;
import com.board.persistence.ReplyDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Inject
	private ReplyDAO dao;

	//댓글 리스트
	@Override
	public List<ReplyVO> replyList(int board_no) throws Exception {
		log.info("===== ReplyServiceImpl :: replyList() invoked.");	
		return dao.replyList(board_no);
	}  //end replyList

	//특정 댓글 조회
	@Override
	public ReplyVO viewReplySelect(int comment_no) throws Exception {
		
		return dao.viewReplySelect(comment_no);
	}  //end viewReplySelect
	
	//댓글 삽입
	@Override
	public void replyCreate(ReplyVO vo) throws Exception {
		log.info("===== ReplyServiceImpl :: replyCreate() invoked.");	
		dao.replyCreate(vo);
	}  //end replyCreate

	//댓글 수정
	@Override
	public void replyUpdate(ReplyVO vo) throws Exception {
		log.info("===== ReplyServiceImpl :: replyUpdate() invoked.");	
		dao.replyUpdate(vo);
	}  //end replyUpdate

	//댓글 삭제
	@Override
	public void replyDelete(ReplyVO vo) throws Exception {
		log.info("===== ReplyServiceImpl :: replyDelete() invoked.");	
		dao.replyDelete(vo);
	}  //end replyDelete

}  //end class
