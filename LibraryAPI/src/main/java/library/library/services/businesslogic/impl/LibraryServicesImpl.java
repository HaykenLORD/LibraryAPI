package library.library.services.businesslogic.impl;

import library.library.controllers.LibraryController;
import library.library.persistences.entities.Books;
import library.library.persistences.repositories.BooksRepository;
import library.library.services.businesslogic.ILibraryService;
import library.library.services.models.Book;
import library.library.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryServicesImpl implements ILibraryService {

    private final Logger logger = LoggerFactory.getLogger(LibraryController.class);

    @Autowired
    private BooksRepository booksRepository;

    /**
     * This function return a list with all books into bbdd.
     * @return List<Book>
     */
    @Override
    public List<Book> getAllBooks() {
        try{
            logger.info("Get all books from books repository.");
            List<Book> bookList = new ArrayList<>();
            List<Books> booksBBDD = booksRepository.findAll();
            logger.info("Iterate the books entity and putting into the list for return.");
            booksBBDD.stream().forEach(books -> {
                Book book = new Book(books.getId(),books.getName(),books.getAuthor(),books.getIsbn(),books.getPublisher(),books.getDate());
                bookList.add(book);
            });
            return bookList;
        }catch (Exception e){
            logger.error("The exception occurred while books are getting.");
            throw e;
        }
    }

    /**
     * This function return a list of books that name starts with parameters that you insert by frontend.
     * @param name of book
     * @return List<Book>
     */
    @Override
    public List<Book> getSpecificsBookByName(String name) {
        try {
            List<Book> bookList = new ArrayList<>();
            logger.info("Get all books from books repository.");
            List<Books> booksBBDD = booksRepository.findAllBooksByName(name);
            logger.info("Iterate the books entity and putting into the list for return.");
            booksBBDD.stream().forEach(books -> {
                Book book = new Book(books.getId(),books.getName(),books.getAuthor(),books.getIsbn(),books.getPublisher(),books.getDate());
                bookList.add(book);
            });
            return bookList;
        }catch (Exception e){
            logger.error("The exception occurred while books are getting.");
            throw e;
        }
    }

    /**
     * This function save a book into de bbdd with a previous validation.
     * @param book
     */
    @Override
    public void saveBook(Book book) {
        try{
            logger.info("Prepare the book for save into books repository.");
            bookValidation(book);
            Books bookToSave = new Books(book.getName(), book.getAuthor(), book.getIsbn(), book.getPublisher(), book.getDate());
            logger.info("The book saved is {}.",bookToSave.getName());
            booksRepository.save(bookToSave);
            logger.info("The book is saved into books repository.");
        }catch (Exception e){
            logger.error("The exception occurred while book is saving or validating in bookValidation() function.");
            throw e;
        }
    }

    /**
     * This function delete a book by id.
     * @param id
     */
    @Override
    public void deleteBook(Long id) {
        try{
            logger.info("Prepare to delete the book from books repository.");
            booksRepository.deleteById(id);
            logger.info("The book with id: {} is deleted from books repository.",id);
        }catch (Exception e){
            logger.error("The exception occurred while book is deleting.");
            throw e;
        }
    }

    /**
     * Funcion to validate a new book before to insert into bbdd.
     * @param book
     */
    private void bookValidation(Book book){
        try{
            if(book.getName() == null || book.getName().isEmpty()){
                logger.error("The book name is null or empty.");
                throw new NullPointerException(Constants.NULL_EXCEPTION_ERROR.concat("name"));
            }

            if(book.getAuthor() == null || book.getAuthor().isEmpty()){
                logger.error("The book author is null or empty.");
                throw new NullPointerException(Constants.NULL_EXCEPTION_ERROR.concat("author"));
            }

            if(book.getIsbn() == null || book.getIsbn().isEmpty()){
                logger.error("The book isbn is null or empty.");
                throw new NullPointerException(Constants.NULL_EXCEPTION_ERROR.concat("isbn"));
            }else {
                if(book.getIsbn().length()<13 || book.getIsbn().length()>13){
                    logger.error("The isbn of the book has more than 13 or less than 13 digits allowed.");
                    throw new IndexOutOfBoundsException(Constants.LENGTS_EXCEPTION_ERROR);
                }
            }

            if(book.getPublisher() == null || book.getPublisher().isEmpty()){
                logger.error("The book publisher is null or empty.");
                throw new NullPointerException(Constants.NULL_EXCEPTION_ERROR.concat("publisher"));
            }

            if(book.getDate() == null || book.getDate().isEmpty()){
                logger.error("The book date is null or empty.");
                throw new NullPointerException(Constants.NULL_EXCEPTION_ERROR.concat("date"));
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }


    }

}
