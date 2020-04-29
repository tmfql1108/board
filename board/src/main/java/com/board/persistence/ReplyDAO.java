package com.board.persistence;

import java.util.List;

import com.board.domain.ReplyVO;

public interface ReplyDAO {

	public  List<ReplyVO> replyList(int board_no) throws Exception;  //댓글리스트

	public  void replyCreate(ReplyVO vo) throws Exception;  //댓글 삽입
	
	public  void replyUpdate(ReplyVO vo) throws Exception;  //댓글 수정
	
	public  void  replyDelete(int comment_no) throws Exception;  //댓글 삭제

	
}  //end interface
