package sample;

public class Review {

    private int revID;
    private String comment;
    private int rate;

    public Review(int revID, String comment, int rate) {
        this.revID = revID;
        this.comment = comment;
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "(" + rate + ") -> " + comment;
    }

    public int getRevID() {
        return revID;
    }

    public void setRevID(int revID) {
        this.revID = revID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}