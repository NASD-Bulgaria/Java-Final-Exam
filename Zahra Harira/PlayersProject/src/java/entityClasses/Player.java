package entityClasses;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author zahra
 */
@Entity
@Table
 @NamedQuery(name = "Player.findByName", query = "SELECT p FROM Player p WHERE p.name = :name")
public class Player {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "login_name")
    private String loginName;

    @Column(name = "login_password")
    private String loginPassword;

    private double balance;

    public Player() {

    }

    public Player(String loginName, String loginPassword, double balance) {

        this.setLoginName(loginName);
        this.setLoginPassword(loginPassword);
        this.setBalance(balance);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return this.loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {

        this.balance = balance;

    }

}
