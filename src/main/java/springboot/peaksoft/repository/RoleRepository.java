package springboot.peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springboot.peaksoft.model.UserRole;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, Long> {
    @Query("select role from UserRole")
    List<String> getRoleNamesToList();
}