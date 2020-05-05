package com.board.domain;

import lombok.Getter;

@Getter
public class Criteria {
// 시작과 끝값을 다룬다 
	private int page;
	private int perPageNum;
	private int rowStart;
	private int rowEnd;
	
	public Criteria() {
	//기본생성자를 통한 기본 값 정의
	  this.page = 1;
	  this.perPageNum = 10;
	 } //end Criteria
	
	 public void setPage(int page){
	  if (page <= 0)  {
		   this.page = 1;
		   return;
	  }  //end if
		  this.page = page;
	 }
	
	 public void setPerPageNum(int perPageNum)
	{
		if (perPageNum <= 0 || perPageNum > 100)
		{
			this.perPageNum = 10;
			return;
		}
		this.perPageNum = perPageNum;
	}
	
	public int getPage() {
		 return page;
	 }
	
	 public int getPageStart(){
		 return (this.page - 1) * perPageNum;
	 }
	
	 public int getPerPageNum() {
		 return this.perPageNum;
	 }
	
	 public int getRowStart() {
		rowStart = ((page - 1) * perPageNum);
		System.out.println("+++++++rowStart"+ rowStart);
		return rowStart;
	}
	
	public int getRowEnd() {
		rowEnd = (rowStart+1) + perPageNum - 1;
		System.out.println("+++++++rowEnd"+ rowEnd);
		return rowEnd;
	}
	
	 @Override
		public String toString() {
			return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", rowStart=" +  getRowStart() + ", rowEnd=" + getRowEnd() + "]";
	}
}//end Criteria

