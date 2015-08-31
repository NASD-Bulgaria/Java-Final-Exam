package validation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import request.PlayerRequest;
import entity.Player;

public class Validation {

	private static final EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("Exam29.08.2015");

	// validate loginName and password - Login
	public static boolean validate(String loginName, String loginPassword) {

		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			Query query = em
					.createQuery("Select p from Player p Where p.loginName = '"
							+ loginName + "'");

			Player player = (Player) query.getSingleResult();

			String hashedPassword = PlayerRequest.hashing(loginPassword,
					loginName);

			if (player.getLoginPassword().equalsIgnoreCase(hashedPassword)) {

				return true;
			}
		} catch (NoResultException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	
	
	public static boolean validateDeposit(String loginName, double deposit) {

		EntityManager em = entityManagerFactory.createEntityManager();

		if (deposit <= 0) {
			return false;
		} else {

			em.getTransaction().begin();
			Query query = em
					.createQuery("Select p from Player p Where p.loginName = '"
							+ loginName + "'");

			Player player = (Player) query.getSingleResult();

			player.setBalance(player.getBalance() + deposit);

			em.persist(player);
			em.getTransaction().commit();
			em.close();

			return true;
		}
	}

	public static boolean validateWithdraw(String loginName, double withdraw) {
		EntityManager em = entityManagerFactory.createEntityManager();

		if (withdraw <= 0) {
			return false;
		}

		if (withdraw > 0) {

			em.getTransaction().begin();
			Query query = em
					.createQuery("Select p from Player p Where p.loginName = '"
							+ loginName + "'");

			Player player = (Player) query.getSingleResult();

			if (player.getBalance() < withdraw) {
				return false;
			} else {
				player.setBalance(player.getBalance() - withdraw);

				em.persist(player);
				em.getTransaction().commit();
				em.close();

				return true;
			}
		}
		return false;
	}
}
