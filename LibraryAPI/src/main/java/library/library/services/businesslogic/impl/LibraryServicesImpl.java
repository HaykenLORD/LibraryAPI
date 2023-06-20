package library.library.services.businesslogic.impl;

import library.library.controllers.LibraryController;
import library.library.persistences.entities.Books;
import library.library.persistences.repositories.BooksRepository;
import library.library.services.businesslogic.ILibraryService;
import library.library.services.models.Book;
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

    private void bookValidation(Book book){
        try{
            if(book.getName() == null || book.getName().isEmpty()){
                logger.error("The books name is null or empty.");
                throw new NullPointerException("The books name is null or empty.");
            }

            if(book.getAuthor() == null || book.getAuthor().isEmpty()){
                logger.error("The books author is null or empty.");
                throw new NullPointerException("The books author is null or empty.");
            }

            if(book.getIsbn() == null || book.getIsbn().isEmpty()){
                logger.error("The books isbn is null or empty.");
                throw new NullPointerException("The books isbn is null or empty.");
            }else {
                if(book.getIsbn().length()<13 || book.getIsbn().length()>13){
                    logger.error("The isbn of the book has more than 13 or less than 13 digits allowed.");
                    throw new IndexOutOfBoundsException("The isbn of the book has more than 13 or less than 13 digits allowed.");
                }
            }

            if(book.getPublisher() == null || book.getPublisher().isEmpty()){
                logger.error("The books publisher is null or empty.");
                throw new NullPointerException("The books publisher is null or empty.");
            }

            if(book.getDate() == null || book.getDate().isEmpty()){
                logger.error("The books date is null or empty.");
                throw new NullPointerException("The books date is null or empty.");
            }
        }catch (Exception e){
            throw e;
        }


    }

}
