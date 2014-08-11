package com.marmotlabs.ticketcenter.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * @author Zuui
 */
@Entity
@Table(name = "T_CITY")
@SequenceGenerator(name = "CITY_ID_GEN", sequenceName = "SQ_CITY", allocationSize = 1)
public class City implements Serializable {

    @Id
    @Column(name = "CITY_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CITY_ID_GEN")
    private Long id;

    @Column(name = "NAME")
    private String name;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("id", id).
                append("name", name).
                toString();
    }
}
