package com.karrot.domain.user;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Api(tags = "User", description = "사용자 정보")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary ="Create User", description = "Create User!")
    @PostMapping("/users")
    @ApiOperation(value = "Create User", notes = "Create User")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "Created", response = User.class),
                    @ApiResponse(code = 403, message = "Forbidden", response = String.class),
                    @ApiResponse(code = 500, message = "Internal Server Error", response = String.class)
            }
    )
    public ResponseEntity<User> createUser(@RequestBody User user) {
        throw new NotYetImplementedException();
        //HttpStatus status = HttpStatus.CREATED;
        //User saved = userService.save(user);
        //return new ResponseEntity<>(saved, status);
    }

    @Operation(summary ="Get User", description = "Return all Users")
    @GetMapping("/users")
    public List<User> getAllUsers() {
        throw new NotYetImplementedException();
        //return userService.getAllUsers();
    }

    @Operation(summary ="Get User", description = "Return User by ID")
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        throw new NotYetImplementedException();
        //return userService.getUser(id)
        //        .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
        //        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary ="Put User", description = "Update User by ID")
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        throw new NotYetImplementedException();
        //return userService.getUser(id)
        //        .map(existingUser -> {
        //            user.setId(existingUser.getId());
        //            User saved = userService.save(user);
        //            return new ResponseEntity<>(saved, HttpStatus.OK);
        //        })
        //        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary ="Delete User", description = "Delete User by ID")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        throw new NotYetImplementedException();
        //return userService.getUser(id)
        //        .map(user -> {
        //            userService.delete(user);
        //            return new ResponseEntity<>(HttpStatus.OK);
        //        })
        //        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
