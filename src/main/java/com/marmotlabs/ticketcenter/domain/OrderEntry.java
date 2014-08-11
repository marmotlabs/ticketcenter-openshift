package com.marmotlabs.ticketcenter.domain;

import java.io.Serializable;
import javax.persistence.CascadeType;
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
@Table(name = "T_ORDER_ENTRY")
@SequenceGenerator(name = "ORDER_ENTRY_ID_GEN", sequenceName = "SQ_ORDER_ENTRY", allocationSize = 1)
public class OrderEntry implements Serializable {

    @Id
    @Column(name = "ORDER_ENTRY_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_ENTRY_ID_GEN")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_FK")
    private Event event;

    @Column(name = "NR_TICKETS")
    private Integer nrTickets;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_FK")
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Integer getNrTickets() {
        return nrTickets;
    }

    public void setNrTickets(Integer nrTickets) {
        this.nrTickets = nrTickets;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Returns the nrTickets * event.price
     *
     * @return
     */
    public Integer getTotalPrice() {
        return getNrTickets() * getEvent().getPrice();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("id", id).
                append("event", event).
                append("nrTickets", nrTickets).
                toString();
    }
}
