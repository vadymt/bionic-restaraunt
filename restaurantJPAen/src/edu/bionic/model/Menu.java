package edu.bionic.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Request
 * 
 */
@Entity
@Table(name = "menuposition")
@NamedQueries({
        @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m"),
        @NamedQuery(name = "Menu.findAllActive", query = "SELECT m FROM Menu m WHERE m.active = :active"),
        @NamedQuery(name = "Menu.getCount", query = "SELECT COUNT(m) FROM Menu m WHERE m.active = :active") })
public class Menu implements Serializable, DirtyHackForPaging<Integer> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMenuPosition")
    private int id;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "cost", nullable = false)
    private BigDecimal cost;
    @Column(name = "active", nullable = false)
    private boolean active = true;


    public Menu() {
        super();
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

   

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public Integer getId() {
        // TODO Auto-generated method stub
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (active ? 1231 : 1237);
        long temp;
        temp = Double.doubleToLongBits(cost.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Menu other = (Menu) obj;
        if (active != other.active)
            return false;
        if (Double.doubleToLongBits(cost.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()) != Double
                .doubleToLongBits(other.cost.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}
