package com.board.persistence;

import java.util.List;

import com.board.domain.BoardVO;
import com.board.domain.Criteria;
import com.board.domain.SearchCriteria;

public interface BoardDAO {

	//게시글 작성
	public void write(BoardVO vo) throws Exception;

	//게시글 상세 정보
	public BoardVO viewDetail(int board_no) throws Exception;
	
	//게시글 상제 정보 조회 시  증가
	public void updateViewCnt(int viewCnt) throws Exception;
	
	//게시글 수정
	public void modify(BoardVO vo) throws Exception;
	
	//게시글 삭제
	public void delete(int board_no) throws Exception;

	//게시글 리스트 + 페이징 처리 + 검색
	public List<BoardVO> listSearch(SearchCriteria scri) throws Exception;
	
		//게시물 총 갯수
	public int searchCnt(SearchCriteria scri ) throws Exception;

		
}
