package com.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.domain.BoardVO;
import com.board.domain.SearchCriteria;
import com.board.persistence.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl  implements BoardService{
	
	@Inject
	private BoardDAO dao;

	//게시글 작성
	@Override
	public void write(BoardVO vo) throws Exception {
		log.info("===== BoardServiceImpl::write invoked.");
		
		dao.write(vo);		
	} //end write

	//게시글 상세 조회
	@Override
	public BoardVO viewDetail(int board_no) throws Exception {
		log.info("===== BoardServiceImpl::view invoked.");
		
		return dao.viewDetail(board_no);
	} //end read

	//게시글 조회수 증가
	@Override
	public void updateViewCnt(int viewCnt) throws Exception {
		dao.updateViewCnt(viewCnt);
	}  //updateViewCnt
	
	@Override
	public void modify(BoardVO vo) throws Exception {
		log.info("===== BoardServiceImpl::modify invoked.");
		
		dao.modify(vo);		
	}  //end modify

	@Override
	public void delete(int board_no) throws Exception {
		log.info("BoardServiceImpl::delete invoked.");
		
		dao.delete(board_no);		
	}  //end remove
	
	/* 페이징 처리 요소 */
	// 게시글 리스트 + 검색
	@Override
	public List<BoardVO> listSearch(SearchCriteria scri) throws Exception {
		log.info("BoardServiceImpl::listSearch invoked.");
		return dao.listSearch(scri);
	}  //end listSearch

	//검색된 데이터 개수
	@Override
	public int searchCnt(SearchCriteria scri) throws Exception {
		log.info("BoardServiceImpl::searchCnt invoked.");
		return dao.searchCnt(scri);
	}  //end searchCnt
	

}//endclass
