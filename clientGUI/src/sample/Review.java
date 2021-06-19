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
        return "Review{" +
                "revID=" + revID +
                ", comment='" + comment + '\'' +
                ", rate=" + rate +
                '}';
    }
}