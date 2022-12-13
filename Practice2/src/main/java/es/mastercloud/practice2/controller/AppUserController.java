package es.mastercloud.practice2.controller;

import es.mastercloud.practice2.models.dao.AppUser;
import es.mastercloud.practice2.models.dao.Review;
import es.mastercloud.practice2.models.dto.AppUserDTO;
import es.mastercloud.practice2.models.dto.InAppUserDTO;
import es.mastercloud.practice2.models.dto.ReviewDTO;
import es.mastercloud.practice2.service.AppUserService;
import es.mastercloud.practice2.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/users")
public class AppUserController {


    @Autowired
    AppUserService appUserService;
    @Autowired
    ReviewService reviewService;

    @Operation(summary = "Creates a app user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created app user",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=AppUser.class)
                    )}
            )
    })
    @PostMapping("/")
    public ResponseEntity<AppUserDTO> createAppUser(@Parameter(description = "app user body to create") @RequestBody InAppUserDTO appUser){
        AppUserDTO appUserDTO = appUserService.save(appUser);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(appUserDTO.id()).toUri();
        return ResponseEntity.created(location).body(appUserDTO);
    }

    @Operation(summary = "Returns all app users")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Returns all app users",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=AppUser.class)
                    )}
            )
    })
    @GetMapping("/")
    public Page<AppUserDTO> readAppUsers(Pageable page){
        return appUserService.findAll(page);
    }


    @Operation(summary = "Returns a app user found by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Returns the app user found by the given id",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=AppUser.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "There's no user with the given id",
                    content = @Content
            )
    })
    @GetMapping("/{id}")
    public AppUserDTO readAppUserById(@Parameter(description = "app user's id to be found")@PathVariable long id){
        return appUserService.findById(id);
    }

    @Operation(summary = "Returns all the reviews from an app user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Returns all the reviews from an app user",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=Review.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "There's no user with the given id",
                    content = @Content
            )
    })
    @GetMapping("/{id}/reviews")
    public Page<ReviewDTO> readReviewsById(@Parameter(description = "app user's id for desired reviews")@PathVariable long id, Pageable page){
        return reviewService.findByUserId(id, page);
    }

    @Operation(summary = "Updates an user with the new given info")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Returns the updated user",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=AppUser.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "There's no user with the given id",
                    content = @Content
            )
    })
    @PutMapping("/{id}")
    public AppUserDTO updateAppUser(@Parameter(description = "app user body to update")@RequestBody AppUser newAppUser, @PathVariable long id){
        newAppUser.setId(id);
        return appUserService.replace(newAppUser);
    }

    @Operation(summary = "Deletes an app user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Returns the deleted app user",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=AppUser.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "There's no user with the given id",
                    content = @Content
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<AppUserDTO> deleteAppUser(@Parameter(description = "app user's id to delete")@PathVariable long id){
        AppUserDTO appUser = appUserService.findById(id);
        if (reviewService.findByUserId(id, null).getContent().size()>0){
            return ResponseEntity.status(412).build();
        }
        appUserService.deleteById(id);
        return ResponseEntity.ok(appUser);
    }
}
