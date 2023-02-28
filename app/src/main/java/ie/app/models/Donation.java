package ie.app.models;

public class Donation {
    private int id;
    private int amount;
    private String method;
    public Donation (int amount, String method)
    {
        this.amount = amount;
        this.method = method;
    }

    public Donation() {
        this.amount = 0;
        this.method = "";
    }

    public Donation(int id, int amount, String method) {
        this.id = id;
        this.amount = amount;
        this.method = method;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id + ", " + amount + ", " + method;
    }
}
