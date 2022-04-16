package com.dws.web.Customer;

import com.dws.web.Event.Event;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class EventCustomerId implements java.io.Serializable {

    private Customer customer;
    private Event event;

    @ManyToOne
    public Customer getCustomer() {
        return customer;
    }

    @ManyToOne
    public Event getEvent() {
        return event;
    }


    public void setCustomer(Customer Customer) {
        this.customer = Customer;
    }

    public void setEvent(Event Event) {
        this.event = Event;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventCustomerId that = (EventCustomerId) o;

        if (this.customer != null ? !this.customer.equals(that.customer) : that.customer != null) return false;
        if (this.event != null ? !this.event.equals(that.event) : that.event != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (this.customer != null ? this.customer.hashCode() : 0);
        result = 31 * result + (this.event != null ? this.event.hashCode() : 0);
        return result;
    }

}