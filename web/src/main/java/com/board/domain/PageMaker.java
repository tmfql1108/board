package com.board.domain;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


public class PageMaker {

	private int totalCnt;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;

	private int displayPageNum = 10;  //게시판에 보여질 페이지 수

	private Criteria cri;
	
	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;  //전체 게시물의 갯수를 받아서 처리한다. 
		calcData();
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public Criteria getCri() {
		return cri;
	}
	
	private void calcData() {
		endPage = (int) (Math.ceil(cri.getPage() / (double)displayPageNum) * displayPageNum);
		startPage = (endPage - displayPageNum) + 1;
		
		int tempEndPage = (int) (Math.ceil(totalCnt / (double)cri.getPerPageNum()));
		if (endPage > tempEndPage)
		{
			endPage = tempEndPage;
		}  //end if
		prev = startPage == 1 ? false : true;
		next = endPage * cri.getPerPageNum() >= totalCnt ? false : true;
	}
	
	public String makeQuery(int page){
		// 선택된 페이지에 따라 uri를 만들어준다.
		UriComponents uriComponents = 
				UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.build();
				
		return uriComponents.toUriString();
	}
	
	public String makeSearch(int page){
		//검색된 결과에 따라 uri를 만들어준다.
		UriComponents uriComponents =
	            UriComponentsBuilder.newInstance()
	            .queryParam("page", page)
	            .queryParam("perPageNum", cri.getPerPageNum())
	            .queryParam("searchType", ((SearchCriteria)cri).getSearchType())
	            .queryParam("keyword", encoding(((SearchCriteria)cri).getKeyword()))
	            .build();	
		return uriComponents.toUriString();		
	}
	
	private String encoding(String keyword) {
		if(keyword == null || keyword.trim().length() == 0){ 
			return ""; 
		}  //end if
		
		try	{
			return URLEncoder.encode(keyword, "UTF-8");
		} catch(UnsupportedEncodingException e){ 
			return ""; 
		}  //end try-catch
	}
	
}