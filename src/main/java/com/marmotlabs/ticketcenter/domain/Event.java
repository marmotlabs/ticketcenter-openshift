package com.marmotlabs.ticketcenter.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * @author Zuui
 */
@Entity
@Table(name = "T_EVENT")
@SequenceGenerator(name = "EVENT_ID_GEN", sequenceName = "SQ_EVENT", allocationSize = 1)
public class Event implements Serializable {

    @Id
    @Column(name = "EVENT_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENT_ID_GEN")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCATION_FK")
    private Location location;

    @Column(name = "NAME")
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "EVENT_DATE")
    private Date date;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "PICTURE")
    private String picture;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("id", id).
                append("location", location).
                append("name", name).
                append("date", date).
                append("price", price).
                append("picture", picture).
                toString();
    }
}
