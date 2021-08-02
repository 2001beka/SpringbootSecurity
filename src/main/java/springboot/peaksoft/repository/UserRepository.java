package springboot.peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.peaksoft.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}