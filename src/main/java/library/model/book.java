package library.model;
public class book {
    private int id;
    private String title;
    private String author;
    private String publisher;
    private double price;
    private boolean status;
    public book(String title, String author, String publisher, double price) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
    }
    public book(double price, int id, String title, String author, String publisher) {
        this.price = price;
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }

    public book(int id, String title, String author, String publisher, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
    }

    public book(String title, String author, String publisher, Double price, boolean status) {
            this.title = title;
            this.author = author;
            this.publisher = publisher;
            this.price = price;
            this.status = status;
    }

    public book() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public boolean bookStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
}
