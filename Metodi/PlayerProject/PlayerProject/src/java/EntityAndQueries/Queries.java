/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityAndQueries;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 *
 * @author metodi
 */
public class Queries {
    private static final EntityManagerFactory emf=Persistence.createEntityManagerFactory("PlayerProjectPU");
    
    public static boolean PlayerRegistration(String firstName,String lastName,String loginName,String password,double balance){
        EntityManager manager=emf.createEntityManager();
        manager.getTransaction().begin();
       
         Query query=manager.createQuery("SELECT c FROM Player c WHERE c.loginName='"+loginName+"'");
        try {
              Player player1=(Player) query.getSingleResult();
         
            if (!player1.getLoginName().isEmpty())
            {
                manager.getTransaction().commit();
                manager.close();
                return false;
            }
        } catch (NoResultException e) {
             String hashPassword=Hashing(password);
        Player player=new Player();
        PlayerProfile profile=new PlayerProfile();
        player.setLoginName(loginName);
        player.setPassWord(hashPassword);
        player.setBalance(balance);
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setFkPlayer(player);
        manager.persist(player);
        manager.persist(profile);
        manager.getTransaction().commit();
        manager.close();
  
        return true;
        }
       return true;
    }
    
    private static String Hashing(String password){
         HashFunction function=Hashing.sha256();
         HashCode code=(HashCode)function.newHasher().putString(password,Charsets.UTF_8).hash();
         String passWord1=code.toString();
         return passWord1;
    }
    
    public static boolean AccountCheck(String loginName,String password){
        EntityManager manager=emf.createEntityManager();
        boolean result=true;
         String hashPassword=Hashing(password);
        Query query=manager.createQuery("SELECT c FROM Player c WHERE c.loginName='"+loginName+"'");
        Query query1=manager.createQuery("SELECT c FROM Player c WHERE c.passWord='"+hashPassword+"'");
        try {
                 Player player=(Player) query.getSingleResult();
                  Player player1=(Player) query1.getSingleResult();
                  
                  if ((player.getLoginName().equalsIgnoreCase(loginName)) && (player1.getPassWord().equalsIgnoreCase(hashPassword))) {
                   manager.close();
                    result=true;
                    return result;
            }
      
        } catch (NoResultException e) {
             manager.close();
             result=false;
            return result;
        }
        
        return false;    
    }
    
    
    public static Player TakeAccountInformation(String userName){
        EntityManager manager=emf.createEntityManager();
        Player player=new Player();
        Query query=manager.createQuery("SELECT c FROM Player c WHERE c.loginName='"+userName+"'");
        player=(Player) query.getSingleResult();
        manager.close();
        return player;
    }
    
    
    public static String InsertSum(String loginName,String passWord,double balance){
       EntityManager manager=emf.createEntityManager();
        if (balance<0) {
            manager.close();
            return "You can not insert this sum";     
        }
       Player player=new Player();
       Query query1=manager.createQuery("SELECT c FROM Player c WHERE c.loginName='"+loginName+"'");
       player=(Player) query1.getSingleResult();
       manager.getTransaction().begin();
       double newBalance=player.getBalance()+balance;
       player.setLoginName(loginName);
       player.setPassWord(passWord);
       player.setBalance(newBalance);
       manager.persist(player);
       manager.getTransaction().commit();
       manager.close();  
       return  NewSumInAccount(loginName);
    }
    
    
    public static String TakeSum(String loginName,String passWord,double balance){
        EntityManager manager=emf.createEntityManager();
           if (balance<0) {
             manager.close();
            return "You can not insert this sum";     
        }
        Player player=new Player();
        Query query1=manager.createQuery("SELECT c FROM Player c WHERE c.loginName='"+loginName+"'");
        player=(Player) query1.getSingleResult();
        if (player.getBalance()<balance) 
        {
            manager.close();
            return "You have no enough money";
        }
        manager.getTransaction().begin();
        double result=player.getBalance()-balance;
        player.setBalance(result);
        player.setLoginName(loginName);
        player.setPassWord(passWord);
        manager.persist(player);
        manager.getTransaction().commit();
        manager.close();
         return  NewSumInAccount(loginName);
    }
    
    private static String NewSumInAccount(String loginName){
         EntityManager manager=emf.createEntityManager();
         Query query=manager.createQuery("SELECT c FROM Player c WHERE c.loginName='"+loginName+"'");
         Player player=new Player();
         player=(Player) query.getSingleResult();
         return String.valueOf(player.getBalance());
             
    }
}
