package ec.com.nwi.springaws.repository;

import ec.com.nwi.springaws.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
