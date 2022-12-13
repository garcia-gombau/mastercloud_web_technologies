package es.mastercloud.practice2.service.repository;

import es.mastercloud.practice2.models.dao.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
