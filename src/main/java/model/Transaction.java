package model;

import util.TransactionType;

import java.util.Date;
import java.util.Objects;

public class Transaction {
    private String id;

    private Date date;

    private Float cost;

    private String merchant;

    private TransactionType type;

    private String relatedTransaction;

    public Transaction(String id, Date date, Float cost, String merchant, TransactionType type, String relatedTransaction) {
        this.id = id;
        this.date = date;
        this.cost = cost;
        this.merchant = merchant;
        this.type = type;
        this.relatedTransaction = relatedTransaction;
    }

    public Transaction(String id, Date date, Float cost, String merchant, TransactionType type) {
        this.id = id;
        this.date = date;
        this.cost = cost;
        this.merchant = merchant;
        this.type = type;
        this.relatedTransaction = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getRelatedTransaction() {
        return relatedTransaction;
    }

    public void setRelatedTransaction(String relatedTransaction) {
        this.relatedTransaction = relatedTransaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(date, that.date) &&
                Objects.equals(cost, that.cost) &&
                Objects.equals(merchant, that.merchant) &&
                type == that.type &&
                Objects.equals(relatedTransaction, that.relatedTransaction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, cost, merchant, type, relatedTransaction);
    }
}
