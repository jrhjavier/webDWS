package com.dws.web.Customer;

import com.dws.web.Event.Event;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "Customer_Event", catalog = "mkyongdb")
@AssociationOverrides({
        @AssociationOverride(name = "pk.customer",
                joinColumns = @JoinColumn(name = "CUSTOMER_ID")),
        @AssociationOverride(name = "pk.event",
                joinColumns = @JoinColumn(name = "EVENT_ID")) })
public class EventCustomer implements java.io.Serializable {

    private EventCustomerId pk = new EventCustomerId();
    private Date createdDate;
    private String createdBy;

    public EventCustomer() {
    }

    @EmbeddedId
    public EventCustomerId getPk() {
        return pk;
    }

    public void setPk(EventCustomerId pk) {
        this.pk = pk;
    }

    @Transient
    public Customer getCustomer() {
        return getPk().getCustomer();
    }

    public void setCustomer(Customer Customer) {
        getPk().setCustomer(Customer);
    }

    @Transient
    public Event getEvent() {
        return getPk().getEvent();
    }

    public void setEvent(Event Event) {
        getPk().setEvent(Event);
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATED_DATE", nullable = false, length = 10)
    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Column(name = "CREATED_BY", nullable = false, length = 10)
    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        EventCustomer that = (EventCustomer) o;

        if (getPk() != null ? !getPk().equals(that.getPk())
                : that.getPk() != null)
            return false;

        return true;
    }

    public int hashCode() {
        return (getPk() != null ? getPk().hashCode() : 0);
    }
}