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

	@Override
	public List<ReplyVO> replyList(int board_no) throws Exception {
		log.info("===== ReplyServiceImpl :: replyList() invoked.");	
		return dao.replyList(board_no);
	}  //end replyList

	@Override
	public void replyCreate(ReplyVO vo) throws Exception {
		log.info("===== ReplyServiceImpl :: replyCreate() invoked.");	
		dao.replyCreate(vo);
	}  //end replyCreate

	@Override
	public void replyUpdate(ReplyVO vo) throws Exception {
		log.info("===== ReplyServiceImpl :: replyUpdate() invoked.");	
		dao.replyUpdate(vo);
	}  //end replyUpdate

	@Override
	public void replyDelete(int comment_no) throws Exception {
		log.info("===== ReplyServiceImpl :: replyDelete() invoked.");	
		dao.replyDelete(comment_no);
	}  //end replyDelete
	

}  //end class
