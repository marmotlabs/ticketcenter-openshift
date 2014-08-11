package com.marmotlabs.ticketcenter.domain;

import java.io.Serializable;
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
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * @author Zuui
 */
@Entity
@Table(name = "T_LOCATION")
@SequenceGenerator(name = "LOCATION_ID_GEN", sequenceName = "SQ_LOCATION", allocationSize = 1)
public class Location implements Serializable {

    @Id
    @Column(name = "LOCATION_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOCATION_ID_GEN")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CITY_FK")
    private City city;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PICTURE")
    private String picture;

    @Column(name = "CAPACITY")
    private Integer capacity;

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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("id", id).
                append("city", city).
                append("name", name).
                append("picture", picture).
                append("capacity", capacity).
                toString();
    }
}
