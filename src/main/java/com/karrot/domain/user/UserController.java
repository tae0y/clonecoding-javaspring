package com.karrot.domain.user;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Users", description = "사용자 정보")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * <ul>
     *    <li>name : createUser
     *    <li>desc : 사용자를 생성한다.
     * </ul>
     * @param UsersResponseDTO user
     * @return ResponseEntity<UsersResponseDTO>
     */
    @Operation(summary ="Create User", description = "Create User!")
    @PostMapping("/")
    @ApiResponses(
        value = {
            //TODO : [개선] 응답코드에 따라 객체 언마샬 로직이 분기를 타야함. responseCode, responseMessage, responseSchema를 포함한 객체를 반환하도록 수정
            @ApiResponse(responseCode = "201", description = "Create User Succeess", content = @Content(examples={}, schema = @Schema(implementation = UsersResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content= @Content(examples={@ExampleObject(name="", summary="", description="", value="Invalid input")}, schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content= @Content(examples={@ExampleObject(name="", summary="", description="", value="Forbidden")}, schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "409", description = "User already exists", content= @Content(examples={@ExampleObject(name="", summary="", description="", value="User Already exists")}, schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "503", description = "Internal server error", content= @Content(examples={@ExampleObject(name="", summary="", description="", value="Internal server error")}, schema = @Schema(implementation = String.class)))
        }
    )
    public ResponseEntity<UsersResponseDTO> createUser(@RequestBody UsersResponseDTO user) {
        throw new NotYetImplementedException();
        //HttpStatus status = HttpStatus.CREATED;
        //User saved = userService.save(user);
        //return new ResponseEntity<>(saved, status);
    }

    /**
     * <ul>
     *    <li>name : getAllUsers
     *    <li>desc : 모든 사용자 정보를 조회한다.
     * </ul>
     * @param 
     * @return List<UsersResponseDTO>
     */
    @Operation(summary ="Get User", description = "Return all Users")
    @GetMapping("/")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "Get User Success", content = @Content(schema = @Schema(implementation = UsersResponseDTO.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content= @Content(examples={@ExampleObject(name="", summary="", description="", value="Forbidden")}, schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "503", description = "Internal server error", content= @Content(examples={@ExampleObject(name="", summary="", description="", value="Internal server error")}, schema = @Schema(implementation = String.class)))
        }
    )
    public List<UsersResponseDTO> getAllUsers() {
        throw new NotYetImplementedException();
        //return userService.getAllUsers();
    }

    /**
     * <ul>
     *    <li>name : getUser
     *    <li>desc : 특정 사용자 정보를 조회한다.
     * </ul>
     * @param Long id
     * @return ResponseEntity<UsersResponseDTO>
     */
    @Operation(summary ="Get User", description = "Return User by ID")
    @Parameter(name = "id", description = "사용자 ID", required = true)
    @GetMapping("/{id}")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "Get User Success", content = @Content(schema = @Schema(implementation = UsersResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content= @Content(examples={@ExampleObject(name="", summary="", description="", value="Invalid input")}, schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content= @Content(examples={@ExampleObject(name="", summary="", description="", value="Forbidden")}, schema = @Schema(implementation = String.class))),
            //TODO : [개선] user not found로 사용자 존재여부를 추정할 수 있음, 보안을 위해 제거하는게 좋을까?
            @ApiResponse(responseCode = "404", description = "User not found", content= @Content(examples={@ExampleObject(name="", summary="", description="", value="User not found")}, schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "503", description = "Internal server error", content= @Content(examples={@ExampleObject(name="", summary="", description="", value="Internal server error")}, schema = @Schema(implementation = String.class)))
        }
    )
    public ResponseEntity<UsersResponseDTO> getUser(@PathVariable Long id) {
        throw new NotYetImplementedException();
        //return userService.getUser(id)
        //        .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
        //        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * <ul>
     *    <li>name : updateUser
     *    <li>desc : 사용자 정보를 변경한다.
     * </ul>
     * @param Long id, UsersRequestDTO user
     * @return UsersResponseDTO<Users>
     */
    @Operation(summary ="Put User", description = "Update User by ID")
    @Parameter(name = "id", description = "사용자 ID", required = true)
    @PutMapping("/{id}")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "Update User Success", content = @Content(schema = @Schema(implementation = UsersResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content= @Content(examples={@ExampleObject(name="", summary="", description="", value="Invalid input")}, schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content= @Content(examples={@ExampleObject(name="", summary="", description="", value="Forbidden")}, schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content= @Content(examples={@ExampleObject(name="", summary="", description="", value="User not found")}, schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "503", description = "Internal server error", content= @Content(examples={@ExampleObject(name="", summary="", description="", value="Internal server error")}, schema = @Schema(implementation = String.class)))
        }
    )
    public ResponseEntity<UsersResponseDTO> updateUser(@PathVariable Long id, @RequestBody UsersRequestDTO user) {
        throw new NotYetImplementedException();
        //return userService.getUser(id)
        //        .map(existingUser -> {
        //            user.setId(existingUser.getId());
        //            User saved = userService.save(user);
        //            return new ResponseEntity<>(saved, HttpStatus.OK);
        //        })
        //        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * <ul>
     *    <li>name : deleteUser
     *    <li>desc : 특정 사용자 정보를 삭제한다.
     * </ul>
     * @param Long id
     * @return ResponseEntity<?> //TODO : 삭제 성공시 반환값 타입 정하기
     */
    @Operation(summary ="Delete User", description = "Delete User by ID")
    @Parameter(name = "id", description = "사용자 ID", required = true)
    @DeleteMapping("/{id}")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "Delete User Success", content = @Content(examples={@ExampleObject(name="", summary="", description="", value="Delete User Success")}, schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content= @Content(examples={@ExampleObject(name="", summary="", description="", value="Invalid input")}, schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content= @Content(examples={@ExampleObject(name="", summary="", description="", value="Forbidden")}, schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content= @Content(examples={@ExampleObject(name="", summary="", description="", value="User not found")}, schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "503", description = "Internal server error", content= @Content(examples={@ExampleObject(name="", summary="", description="", value="Internal server error")}, schema = @Schema(implementation = String.class)))
        }
    )
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        throw new NotYetImplementedException();
        //return userService.getUser(id)
        //        .map(user -> {
        //            userService.delete(user);
        //            return new ResponseEntity<>(HttpStatus.OK);
        //        })
        //        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
