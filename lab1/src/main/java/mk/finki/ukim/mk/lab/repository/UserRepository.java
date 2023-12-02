package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
