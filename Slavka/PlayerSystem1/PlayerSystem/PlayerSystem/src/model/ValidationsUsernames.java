package model;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import controller.Queries;
import javax.persistence.*;

public class ValidationsUsernames {
	public static boolean validPlayer_profile(String playerName) {

		try {
			PlayerProfile result = Queries.showPlayerByProfileByName(playerName);
		} catch (NoResultException e) {
			// TODO: handle exception
			return true;
		}

		return false;
	}
	public static boolean validPlayerName(String player) {

		try {
			Player result = Queries.showPlayerByLogin_name(player);
		} catch (NoResultException e) {
			// TODO: handle exception
			return true;
		}

		return false;
	}
	public static boolean validForLogin(String loginName, String password){
		try {
			Player findPlayer = Queries.showPlayerByLogin_name(loginName);
			HashFunction hash = Hashing.sha1();
		
			String pass = hash.newHasher().putString(password, Charsets.UTF_8).hash().toString();
			HashCode hs = hash.newHasher()
					.putString(pass, Charsets.UTF_8)
					.putString(findPlayer.getSalt(), Charsets.UTF_8)
					.hash();
			String result =hs.toString();
			if (findPlayer.getLoginPassword().equals(result)) {
				return true;
			}
		} catch (NoResultException e) {
			// TODO: handle exception
			System.out.println("Error");
			return false;
		}
		
		return false;
	}
	public static boolean validBalance(String name,double temp){
		try {
			Player chek=Queries.showPlayerByLogin_name(name);
			double result = chek.getBalance()-temp;
			if (result<0) {
				return false;
			}else {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		return false;
		}
		
		
	}	
	public static boolean validDeposit(String name,double temp){
		try {
			Player chek=Queries.showPlayerByLogin_name(name);
			
			if (temp<=0) {
				
				return false;
			}else {
				double result = chek.getBalance()+temp;
				return true;
			}
		} catch (NumberFormatException e) {
			// TODO: handle exception
		return false;
		}
		
		
	}	

}
