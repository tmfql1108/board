package com.board.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MemberVO {
	/*
	member_no int auto_increment primary key,
    member_id varchar(20) not null,
    member_pwd varchar(150) not null,
    member_nick varchar(20) not null unique ,
    member_email varchar(50) not null,
    member_reg timestamp not null  default current_timestamp
    */
	private int member_no;
	private String member_id;
	private String member_pwd;
	private String member_nick;
	private String member_email;
	private Date member_reg;
	private String withdrawal;
	
}
