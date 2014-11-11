package spring.chap1.entity;

import java.util.Date;

public class Account {
    private int id;
    private int credit;
    private Date lastPaid;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + credit;
        result = prime * result + id;
        result = prime * result + ((lastPaid == null) ? 0 : lastPaid.hashCode());
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
        Account other = (Account) obj;
        if (credit != other.credit)
            return false;
        if (id != other.id)
            return false;
        if (lastPaid == null) {
            if (other.lastPaid != null)
                return false;
        } else if (!lastPaid.equals(other.lastPaid))
            return false;
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Date getLastPaid() {
        return lastPaid;
    }

    public void setLastPaid(Date lastPaid) {
        this.lastPaid = lastPaid;
    }

    @Override
    public String toString() {
        return "Account [id=" + id + ", credit=" + credit + ", lastPaid=" + lastPaid + "]";
    }
}
