package control;

import javax.persistence.NoResultException;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import model.Player;

public class ValidationData {

	public static boolean userNameExist(String username) {

		try {
			Requests.showPlayerByLoginName(username);

		} catch (NoResultException e) {
			// TODO: handle exception
			return true;
		}
		return false;
	}

	public static boolean userNamePasswordValid(String username, String password) {

		try {
			Player player = Requests.showPlayerByLoginName(username);
			HashFunction hash = Hashing.sha256();
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

	public static boolean validBalanceForWithDraw(String token ,double balance){
		
		double temp = Requests.showBalance(token);
		if (temp<balance) {
			return false;
		}else {
			return true;
		}
		
	}
}
