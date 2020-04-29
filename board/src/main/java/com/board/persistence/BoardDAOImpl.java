package com.board.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.BoardVO;
import com.board.domain.SearchCriteria;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession sqlSession;
	private final String namespace = "com.board.mapper.BoardMapper";
	private final String namespace2 = "com.board.mapper.BoardMapper";

	
	//게시글 작성
	@Override
	public void write(BoardVO vo) throws Exception {
		log.info("===== BoardDAOImpl :: write(BoardVO vo) invoked.");
		
		sqlSession.insert(namespace + ".write", vo);
	} //end write
	
	//게시글 상세 조회
	@Override
	public BoardVO viewDetail(int board_no) throws Exception {
		log.info("===== BoardDAOImpl :: view(BoardVO vo) invoked.");		
		
		return sqlSession.selectOne(namespace + ".view", board_no);  //한가지만 데이터만
	}// end view
	
	//게시글 조회수 증가
	@Override
	public void updateViewCnt(int viewCnt) throws Exception {
		sqlSession.update(namespace + ".updateViewCnt", viewCnt);
	}  //end updateViewCnt

	//게시글 수정
	@Override
	public void modify(BoardVO vo) throws Exception {
		log.info("===== BoardDAOImpl :: modify(BoardVO vo) invoked.");
		sqlSession.update(namespace + ".modify", vo);		
	}//end modify

	//게시글 삭제
	@Override
	public void delete(int board_no) throws Exception {
		log.info("===== BoardDAOImpl :: delete(int board_no) invoked.");
		sqlSession.delete(namespace+".delete", board_no);		
	}// end delete

	//목록 리스트
	@Override
	public List<BoardVO> listSearch(SearchCriteria scri) throws Exception {
		log.info("===== BoardDAOImpl :: listSearch() invoked.");	
		return sqlSession.selectList(namespace + ".listSearch", scri) ;
	}

	//검색된 데이터의 수
	@Override
	public int searchCnt(SearchCriteria scri) throws Exception {
		log.info("===== BoardDAOImpl :: searchCnt() invoked.");	
		return sqlSession.selectOne(namespace + ".searchCnt", scri);
	}

	
}  //end class
