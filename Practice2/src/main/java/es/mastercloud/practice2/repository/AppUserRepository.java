package es.mastercloud.practice2.repository;

import es.mastercloud.practice2.dto.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Page<AppUser> findByUsername(String username, Pageable page);
    Page<AppUser> findById(long id, Pageable page);

    AppUser save(AppUser appUser);
}
