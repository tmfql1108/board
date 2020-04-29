package com.board.persistence;

import java.util.List;

import com.board.domain.ReplyVO;

public interface ReplyDAO {

	public  List<ReplyVO> replyList(int board_no) throws Exception;  //댓글리스트

	public ReplyVO viewReplySelect(int comment_no ) throws Exception;  //특정 댓글 조회
	
	public  void replyCreate(ReplyVO vo) throws Exception;  //댓글 삽입
	
	public  void replyUpdate(ReplyVO vo) throws Exception;  //댓글 수정
	
	public  String  replyDelete(String member_nick) throws Exception;  //댓글 삭제

	public void updateReplyCnt(int board_no) throws Exception;  // 게시글에 따른 댓글 개수
}  //end interface
