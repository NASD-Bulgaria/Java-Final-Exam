package controller;

import entityClasses.Player;
import entityClasses.PlayerProfile;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author zahra
 */
public class PlayerQueries {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("PlayersProject");

    public static void signupPlayer(String firstName, String lastName, String loginName, String loginPassword) {

        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();

        Player player = new Player(loginName, loginPassword, 0);
        manager.persist(player);

        PlayerProfile plProfile = new PlayerProfile(firstName, lastName, player);
        manager.persist(plProfile);

        manager.getTransaction().commit();
        manager.close();
    }

    public static Player getPlayerByName(String loginName) {

        EntityManager manager = emf.createEntityManager();
        Query query = manager.createQuery("SELECT p FROM Player p WHERE p.loginName = '" + loginName + "'");

        Player player = (Player) query.getSingleResult();
        manager.close();

        return player;
    }

    public static void deposit(String loginName, double balance) {

        EntityManager manager = emf.createEntityManager();

      
        Player player = (Player) manager.createNamedQuery("Player.findByName").setParameter("name", loginName);

        manager.getTransaction().begin();
        double newBalance = player.getBalance() + balance;
        player.setBalance(newBalance);
        manager.persist(player);
        manager.getTransaction().commit();

        manager.close();
    }

    public static void withdraw(String loginName, double balance) {

        EntityManager manager = emf.createEntityManager();

        Player player = (Player) manager.createNamedQuery("Player.findByName").setParameter("name", loginName);
        
        manager.getTransaction().begin();
        double newBalance = player.getBalance() - balance;
        player.setBalance(newBalance);
        manager.persist(player);
        manager.getTransaction().commit();

        manager.close();
    }

}
