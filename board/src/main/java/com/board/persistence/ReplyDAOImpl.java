package com.board.persistence;

import java.lang.Thread.State;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.ReplyVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ReplyDAOImpl implements ReplyDAO {
	
	
	@Inject
	private SqlSession sqlSession;
	private final String namespace = "com.board.mapper.ReplyMapper";
	
	//댓글 리스트
	@Override
	public List<ReplyVO> replyList(int board_no) throws Exception {
		log.info("===== ReplyDAOImpl :: replyList() invoked.");
		return sqlSession.selectList(namespace + ".replyList", board_no);
	}  //end replyList
	
	//댓글 삽입
	@Override
	public void replyCreate(ReplyVO vo) throws Exception {
		log.info("===== ReplyDAOImpl :: replyCreate() invoked.");
		sqlSession.insert(namespace + ".replyCreate", vo) ;
	}  //end replyCreate

	//댓글 수정
	@Override
	public void replyUpdate(ReplyVO vo) throws Exception {
		log.info("===== ReplyDAOImpl :: replyUpdate() invoked.");
		sqlSession.update(namespace + ".replyUpdate", vo) ;
	}  //end replyUpdate
	
	//댓글 삭제
	@Override
	public void replyDelete(int comment_no) throws Exception {
		log.info("===== ReplyDAOImpl :: replyDelete() invoked.");
		sqlSession.delete(namespace + ".replyDelete", comment_no) ;
	}  //end replyDelete



}  //end class
