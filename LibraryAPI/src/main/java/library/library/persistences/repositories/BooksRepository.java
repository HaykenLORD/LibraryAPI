package library.library.persistences.repositories;

import library.library.persistences.entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Books,Long> {

    @Query(value = "SELECT * FROM books WHERE books.name LIKE ?1%",nativeQuery = true)
    List<Books> findAllBooksByName(String name);

}
