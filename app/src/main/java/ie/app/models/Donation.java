package ie.app.models;

public class Donation {
    private int id;
    private int amount;
    private String method;

    private String date;
    public Donation (int amount, String method, String date)
    {
        this.amount = amount;
        this.method = method;
        this.date = date;
    }

    public Donation(int amount, String method) {
        this.amount = amount;
        this.method = method;
    }

    public Donation() {
        this.amount = 0;
        this.method = "";
        this.date = "DD/MM/YYYY";
    }

    public Donation(int id, int amount, String method, String date) {
        this.id = id;
        this.amount = amount;
        this.method = method;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return id + ", " + amount + ", " + method + " " + date;
    }
}
