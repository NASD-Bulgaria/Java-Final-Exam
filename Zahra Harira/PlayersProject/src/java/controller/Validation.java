package controller;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import entityClasses.Player;
import javax.persistence.NoResultException;

/**
 *
 * @author zahra
 */
public class Validation {

    public static boolean isExisting(String loginName) {

        try {

            Player player = PlayerQueries.getPlayerByName(loginName);

        } catch (NoResultException e) {
            return false;
        }
        return true;
    }

    public static boolean isRegistered(String loginName, String loginPassword) {

        try {
            
            Player player = PlayerQueries.getPlayerByName(loginName);
            HashFunction hf = Hashing.sha256();
            HashCode code = hf.newHasher().putString(loginPassword, Charsets.UTF_8).hash();
            String result = code.toString();
            
            if (result.equals(player.getLoginPassword())) {
                return true;
            } else {
                return false;
            }
        } catch (NoResultException e) {
            return false;
        }

    }

    public static boolean enoughBalance(String loginName, double newBalance) {
        try {
            
            Player player = PlayerQueries.getPlayerByName(loginName);
            double balance = player.getBalance();
            
            if (balance < newBalance) {
                
                return false;
                
            } else {
                return true;
            }

        } catch (Exception e) {
            return false;
        }

    }

}
