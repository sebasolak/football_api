package footballapi.demo.dao;

import footballapi.demo.model.userrole.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);

    @Query(value = "Select email from user WHERE login like ?1", nativeQuery = true)
    String selectEmailByLogin(String login);
}
