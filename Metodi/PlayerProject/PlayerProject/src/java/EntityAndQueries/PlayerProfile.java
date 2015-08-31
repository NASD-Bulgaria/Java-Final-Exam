/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityAndQueries;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author metodi
 */
@Entity
@Table(name = "player_profile")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlayerProfile.findAll", query = "SELECT p FROM PlayerProfile p"),
    @NamedQuery(name = "PlayerProfile.findById", query = "SELECT p FROM PlayerProfile p WHERE p.id = :id"),
    @NamedQuery(name = "PlayerProfile.findByFirstName", query = "SELECT p FROM PlayerProfile p WHERE p.firstName = :firstName"),
    @NamedQuery(name = "PlayerProfile.findByLastName", query = "SELECT p FROM PlayerProfile p WHERE p.lastName = :lastName")})
public class PlayerProfile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "firstName")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "lastName")
    private String lastName;
    @JoinColumn(name = "fk_player", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Player fkPlayer;

    public PlayerProfile() {
    }

    public PlayerProfile(Integer id) {
        this.id = id;
    }

    public PlayerProfile(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Player getFkPlayer() {
        return fkPlayer;
    }

    public void setFkPlayer(Player fkPlayer) {
        this.fkPlayer = fkPlayer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlayerProfile)) {
            return false;
        }
        PlayerProfile other = (PlayerProfile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityAndQueries.PlayerProfile[ id=" + id + " ]";
    }
    
}
