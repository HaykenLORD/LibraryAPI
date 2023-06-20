package library.library.services.businesslogic;

import library.library.services.models.Book;

import java.util.List;

public interface ILibraryService {

    public List<Book> getAllBooks();

    public List<Book> getSpecificsBookByName(String name);

    public void saveBook(Book book);

}
