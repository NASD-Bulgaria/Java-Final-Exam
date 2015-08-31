package servlets;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Hash {
	private String loginName;
	private String password;
	
	public Hash(String loginName, String password) {
		setLoginName(loginName);
		setPassword(password);
	}
	
	public String hash(){
		SimpleDateFormat date2string = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String curTime = date2string.format(new Date());
		
		String input = getLoginName() + getPassword() + curTime;
		System.out.println(input);
		
		MessageDigest myMD = null;
		try {
			myMD = MessageDigest.getInstance("SHA-1");
			myMD.update(input.getBytes());
		} catch (NoSuchAlgorithmException e) {
			System.err.println("Problem in MessageDigest utility");
		}
		
		byte[] byteInput = myMD.digest();
		
		StringBuffer fin_psw = new StringBuffer();
		for (int i = 0; i < byteInput.length; i++) {
			fin_psw.append(Integer.toString((byteInput[i] & 0xff) + 0x100, 16).substring(1));
        }

        return fin_psw.toString();
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	
}
