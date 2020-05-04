package com.board.domain;

import lombok.Data;

@Data
public class ReplyVO {
/*
	comment_no int auto_increment primary key,
    board_no int not null ,
	comment varchar(100)  not null , 
    member_nick varchar(20) not null,
	comment_reg timestamp not null default current_times
	*/
	
	private int comment_no;
	private int board_no;
	private String comment;
	private String member_nick;
	private String  comment_reg;
	private int replycnt;
}  //end class
