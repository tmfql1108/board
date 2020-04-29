package com.board.util;

import java.security.MessageDigest;

public class sha256 {

	public static String encrypt ( String planText) {
		try {
			//sha-256형식으로 암호화 MessageDigest 클래스 사용
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(planText.getBytes());
			byte byteData[] = md.digest();
			
			StringBuffer sb = new StringBuffer();
			
			for ( int i = 0; i< byteData.length; i ++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16 ).substring(1));
			}  //end for
			
			StringBuffer hexString  = new StringBuffer();
			
			for( int i = 0; i < byteData.length; i++) {
				String hex = Integer.toHexString(0xff  & byteData[i]);
				
				if(hex.length() == 1) {
					hexString.append('0');
				}  //end if
				hexString.append(hex);
			}  //end for
			return hexString.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}  //end try-catch
	} //end encrypt
}  //end class
