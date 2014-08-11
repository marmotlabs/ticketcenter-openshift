package com.marmotlabs.ticketcenter.domain;

import com.marmotlabs.ticketcenter.webmvc.controllers.Pages;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * @author Zuui
 */
@Entity
@Table(name = "T_ORDER")
@SequenceGenerator(name = "ORDER_ID_GEN", sequenceName = "SQ_ORDER", allocationSize = 1)
public class Order implements Serializable {

    @Id
    @Column(name = "ORDER_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_ID_GEN")
    private Long id;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OrderEntry> entries;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "NAME")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<OrderEntry> getEntries() {
        return entries;
    }

    public void setEntries(Set<OrderEntry> entries) {
        this.entries = entries;
    }

    /**
     * Adds a new entry in the entries set, or increments the tickets number of
     * the entry with the same eventId
     *
     * @param orderEntry
     */
    public void addOrderEntry(final OrderEntry orderEntry) {
        for (OrderEntry oe : getEntries()) {
            if (oe.getEvent().getId().equals(orderEntry.getEvent().getId())) {
                Integer oldNrTickets = oe.getNrTickets();
                Integer newTickets = orderEntry.getNrTickets();
                Integer totalNumberOfTicketsPerEvent = (oldNrTickets + newTickets);
                if (totalNumberOfTicketsPerEvent > Pages.MAX_TICKETS_PER_EVENT) {
                    oe.setNrTickets(Pages.MAX_TICKETS_PER_EVENT);
                } else {
                    oe.setNrTickets(totalNumberOfTicketsPerEvent);
                }
                return;
            }
        }

        // At this point, no entry with the same eventId was found, so insert a new one
        orderEntry.setOrder(this);
        getEntries().add(orderEntry);
    }

    /**
     * Return the sum of all the orderEntry entries.
     *
     * @return
     */
    public Integer getTotalPrice() {
        Integer totalPrice = 0;
        for (OrderEntry orderEntry : getEntries()) {
            totalPrice += orderEntry.getTotalPrice();
        }
        return totalPrice;
    }
    
    /**
     * Return the total number of tickets in cart.
     *
     * @return
     */
    public Integer getSumOfTickets() {
        Integer totalTickets = 0;
        for (OrderEntry orderEntry : getEntries()) {
            totalTickets += orderEntry.getNrTickets();
        }
        return totalTickets;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("id", id).
                append("email", email).
                append("address", address).
                append("name", name).
                append("entries", entries).
                toString();
    }
}
