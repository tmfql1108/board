package com.board.controller;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class CTest {
 

  final String DRIVER = "com.mysql.jdbc.Driver";
  final String URL = "jdbc:mysql://127.0.0.1:3306/board?serverTimezone=UTC";
  final String USER = "mysql";
  final String PASSWORD = "mysql";
  
  @Test
  public void conn() throws Exception{
   Class.forName(DRIVER);
   
   try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
    System.out.println(con);
   } catch(Exception e) {
    e.printStackTrace();
   }
  }
}