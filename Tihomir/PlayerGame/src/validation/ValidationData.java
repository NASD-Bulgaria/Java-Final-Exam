package validation;

import javax.persistence.NoResultException;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;


import controller.PlayerQueries;
import model.Player;

public class ValidationData {
	
	public static boolean validBalanceForWithDraw(String token ,double balance){
		
		double temp = PlayerQueries.showBalance(token);
		if (temp<balance) {
			return false;
		}else {
			return true;
		}
		
	}
	
	public static boolean isNameAvailable(String playerName) {
		try {			
			PlayerQueries.showPlayerName(playerName);
		} catch (NoResultException e) {
			return true;
		}

		return false;
	}
	


	public static boolean isValidUserNameAndPass(String username, String password) {

		try {
			Player player = PlayerQueries.showPlayerName(username);
			HashFunction hash = Hashing.sha1();
			String salt = player.getSalt();
			String pass = hash.newHasher().putString(password, Charsets.UTF_8).hash().toString();
			HashCode hs = hash.newHasher().putString(pass, Charsets.UTF_8).putString(salt, Charsets.UTF_8).hash();
			String result = hs.toString();
			if (result.equals(player.getLogin_password())) {
				return true;
			} else {
				return false;
			}
		} catch (NoResultException e) {
			// TODO: handle exception
			return false;
		}

	}	
}
