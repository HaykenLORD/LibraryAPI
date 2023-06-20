package library.library.controllers;

import library.library.services.businesslogic.ILibraryService;
import library.library.services.models.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    private final Logger logger = LoggerFactory.getLogger(LibraryController.class);
    @Autowired
    private ILibraryService iLibraryService;

    //Instances of Interfaces/Services, it's the same that @Autowired, but better for the testing after.
    /*public LibraryController(ILibraryService iLibraryService){
    this.iLibraryService = iLibraryService;
    }*/

    /**
     * This controller getAllBooks() uses to return the all books to the frontend.
     * @return A list of books.
     */
    @GetMapping("/books")
    public List<Book> getAllBooks(){
        logger.info("Accessing to the service iLibraryService to get all books and return to Frontend.");
        return iLibraryService.getAllBooks();
    }

    /**
     * This controller getSpecificsBookByName() uses to return the all books that starts with parameter and return to Frontend.
     * @param name
     * @return A list of books.
     */
    @GetMapping("/books/search")
    public List<Book> getSpecificsBookByName(@RequestParam String name){
        logger.info("Accessing to the service iLibraryService to get all books by name and return to Frontend.");
        return iLibraryService.getSpecificsBookByName(name);
    }

    /**
     * This controller addBook() insert a new book from Frontend to bbdd.
     * @param book
     */
    @PostMapping("/books/add")
    public void addBook(@RequestBody Book book){
        try{
            logger.info("Accessing to the service iLibraryService to save a book.");
            iLibraryService.saveBook(book);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }

    }

    //TODO HABRÁ QUE COMPROBAR CUANDO HAYA FRONT QUE DEVUELVE CON RESPONSEENTITY

        /*   public ResponseEntity<String> addBook(@RequestBody Book book){
        try{
            logger.info("Accessing to the service iLibraryService to save a book added return status to Frontend.");
            iLibraryService.saveBook(book);
            return new ResponseEntity<>("Book correctly saved.",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }*/

    /**
     * This controller delete a book with id
     * @param id
     */
    @DeleteMapping("/books/delete/{id}")
    public void deleteBook(@PathVariable Long id){
        try{
            logger.info("Accessing to the service iLibraryService to delete a book.");
            iLibraryService.deleteBook(id);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }

    }

}
