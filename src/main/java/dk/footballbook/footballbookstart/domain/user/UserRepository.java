package dk.footballbook.footballbookstart.domain.user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    // JPA query method
    User findByUsername(String username);

//    User findById(int id);

}
