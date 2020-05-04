package com.board.util;

import java.security.MessageDigest;

public class sha256 {

	public static String encrypt ( String planText) {
		try {
			//sha-256형식으로 암호화방식 지정 메소드 MessageDigest 클래스 사용
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(planText.getBytes());  //해쉬값 없데이트
			byte byteData[] = md.digest();  //해쉬값 얻기
			
			StringBuffer sb = new StringBuffer();
			
			for ( int i = 0; i< byteData.length; i ++) {
				// 16진수 문자열로 변환하여 문자열 추가
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16 ).substring(1));
			}  //end for
			
			StringBuffer hexString  = new StringBuffer();
			
			for( int i = 0; i < byteData.length; i++) {
				String hex = Integer.toHexString(0xff  & byteData[i]);
				
				if(hex.length() == 1) {
					   // 한자리일 경우 앞에 0 추가
					hexString.append('0');
				}  //end if
				// 암호화 된 결과값을 HEX값으로 출력
				hexString.append(hex);
			}  //end for
			return hexString.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}  //end try-catch
	} //end encrypt
}  //end class
