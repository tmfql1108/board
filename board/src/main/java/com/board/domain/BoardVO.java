package com.board.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {

/*CREATE TABLE  tbl_board (
   board_no number(4) primary key,
    board_title varchar2(100)  not null,
    board_content varchar2(200) not null,	
    board_viewcnt number not null,
    board_reg timestamp not null
);*/
	
	private int board_no; 
	private String board_title;
	private String board_content;
	private int board_viewcnt;
	private Date board_reg;
	private String member_nick;
	private int replycnt;

	
	
}
