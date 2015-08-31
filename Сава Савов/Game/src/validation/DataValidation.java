package validation;

import javax.persistence.NoResultException;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import control.PlayerQueries;
import entity.Player;

public class DataValidation {
	
	public static boolean available(String userName) {
		
		try{
			@SuppressWarnings("unused")
			Player player = PlayerQueries.findUserByUserName(userName);
		}catch(NoResultException e){
			return true;
		}
		return false;
	}

	public static boolean isRegistered(String userName, String password) {

		try {
			Player player = PlayerQueries.findUserByUserName(userName);
			HashFunction hf = Hashing.sha256();
			HashCode hc = hf.newHasher().putString(password, Charsets.UTF_8).hash();
			String result = hc.toString();
			if (result.equals(player.getLoginPassword())) {
				return true;
			} else {
				return false;
			}
		} catch (NoResultException e) {
			return false;
		}

	}

	public static boolean isEnough(String userName, double balance) {
		try {
			Player player = PlayerQueries.findUserByUserName(userName);
			double temp = player.getBalance();
			if (temp < balance) {
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			return false;
		}

	}
	public static boolean negativeInput(double balance) {

			if(balance>0){
				return true;
			}
			else{
				return false;
			}

	}

}
