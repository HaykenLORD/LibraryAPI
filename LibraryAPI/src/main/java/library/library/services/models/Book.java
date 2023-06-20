package library.library.services.models;

public class Book {

    private Long id;
    private String name;
    private String author;
    private String isbn;
    private String publisher;
    private String date;

    public Book(Long id, String name, String author, String isbn, String publisher, String date) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.date = date;
    }

    public Book(String name, String author, String isbn, String publisher, String date) {
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.date = date;
    }

    public Book() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
