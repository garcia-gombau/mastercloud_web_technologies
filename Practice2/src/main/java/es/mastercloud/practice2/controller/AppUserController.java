package es.mastercloud.practice2.controller;

import es.mastercloud.practice2.dto.AppUser;
import es.mastercloud.practice2.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class AppUserController {

    @Autowired
    private AppUserRepository appUsers;

    @GetMapping("/")
    public Page<AppUser> getAppUsers(@RequestParam(required = false) String username, Pageable page){
        if (username != null){
            return appUsers.findByUsername(username, page);
        } else {
            return appUsers.findAll(page);
        }
    }

    @GetMapping("/{id}")
    public Optional<AppUser> getAppUsersById(@PathVariable long id, Pageable page){
        return appUsers.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<AppUser> createAppUsers(@RequestBody AppUser appUser){
        appUsers.save(appUser);

        URI location = fromCurrentRequest().path("/id").buildAndExpand(appUser.getId()).toUri();

        return ResponseEntity.created(location).body(appUser);
    }
}
