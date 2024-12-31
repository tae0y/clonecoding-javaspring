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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karrot.global.common.ResponseDTOWrapper;
import com.karrot.global.common.ResponseStatusEnum;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Users", description = "사용자 정보")
@RestController
@RequestMapping("/api/users")
/**
 * UserController
 * 
 * - 사용자 정보를 생성, 조회, 변경, 삭제 Controller 레이어
 * - 예외처리는 GlobalExceptionHandler에서 처리
 */
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * <ul>
     *    <li>name : createUser
     *    <li>desc : 사용자를 생성한다.
     * </ul>
     * @param UsersRequestDTO user
     * @return ResponseEntity<ResponseDTOWrapper<UsersResponseDTO>>
     */
    @Operation(summary ="Create User", description = "Create User!")
    @PostMapping("/")
    //@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "사용자 정보", required = true, content = @Content(schema = @Schema(implementation = UsersRequestDTO.class)))
    @ApiResponses(
        value = {
            //TODO : [개선] 응답코드에 따라 객체 언마샬 로직이 분기를 타야함. responseCode, responseMessage, responseSchema를 포함한 객체를 반환하도록 수정
            @ApiResponse(responseCode = "201", description = "Create User Succeess" , content = @Content(examples={}, schema = @Schema(implementation = ResponseDTOWrapper.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"        , content = @Content(examples={}, schema = @Schema(implementation = ResponseDTOWrapper.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden"            , content = @Content(examples={}, schema = @Schema(implementation = ResponseDTOWrapper.class))),
            @ApiResponse(responseCode = "409", description = "User already exists"  , content = @Content(examples={}, schema = @Schema(implementation = ResponseDTOWrapper.class))),
            @ApiResponse(responseCode = "503", description = "Internal server error", content = @Content(examples={}, schema = @Schema(implementation = ResponseDTOWrapper.class)))
        }
    )
    public ResponseEntity<ResponseDTOWrapper<UsersResponseDTO>> createUser(@RequestBody UsersRequestDTO user) {
        // prepare
        ResponseDTOWrapper<UsersResponseDTO> response;
        HttpStatus resStatus;

        // process
        UsersResponseDTO resDto = userService.createUser(user);

        // response dto 객체로 ResponseDTOWrapper를 만들어 반환한다.
        response = new ResponseDTOWrapper<>();
        response.setData(List.of(resDto));
        response.setResponseMessage("Create User Success");
        response.setResponseStatus(ResponseStatusEnum.SUCCESS);
        response.setOriginalStatus(HttpStatus.CREATED);
        resStatus = HttpStatus.CREATED;

        // return
        return new ResponseEntity<>(response, resStatus);
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
    public ResponseEntity<ResponseDTOWrapper<UsersResponseDTO>> getAllUsers() {
        // prepare
        ResponseDTOWrapper<UsersResponseDTO> response;
        HttpStatus resStatus;

        // process
        List<UsersResponseDTO> resDtoList = userService.getAllUsers();
        response = new ResponseDTOWrapper<>();
        response.setData(resDtoList);
        response.setResponseMessage("Get User Success");
        response.setResponseStatus(ResponseStatusEnum.SUCCESS);
        response.setOriginalStatus(HttpStatus.OK);
        resStatus = HttpStatus.OK;

        // return
        return new ResponseEntity<>(response, resStatus);
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
    public ResponseEntity<ResponseDTOWrapper<UsersResponseDTO>> getUser(@PathVariable Long id) {
        // prepare
        ResponseDTOWrapper<UsersResponseDTO> response;
        HttpStatus resStatus;

        // process
        UsersResponseDTO resDto = userService.getUser(id);
        response = new ResponseDTOWrapper<>();
        if (resDto != null){
            response.setData(List.of(resDto));
            response.setResponseMessage("Get User Success");
            response.setResponseStatus(ResponseStatusEnum.SUCCESS);
            response.setOriginalStatus(HttpStatus.OK);
            resStatus = HttpStatus.OK;
        } else {
            response.setData(null);
            response.setResponseMessage("User not found");
            response.setResponseStatus(ResponseStatusEnum.FAIL_INTENDED);
            response.setOriginalStatus(HttpStatus.NOT_FOUND);
            resStatus = HttpStatus.NOT_FOUND; // 404, 예외를 던지는게 맞을까? 아니면 상태값만?
        }

        // return
        return new ResponseEntity<>(response, resStatus);
    }

    /**
     * <ul>
     *    <li>name : updateUser
     *    <li>desc : 사용자 정보를 변경한다.
     * </ul>
     * @param Long id, UsersRequestDTO user
     * @return UsersResponseDTO
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
    public ResponseEntity<ResponseDTOWrapper<UsersResponseDTO>> updateUser(@PathVariable Long id, @RequestBody UsersRequestDTO user) {
        // prepare
        ResponseDTOWrapper<UsersResponseDTO> response;
        HttpStatus resStatus;

        // process
        UsersResponseDTO resDto = userService.updateUser(id, user);
        response = new ResponseDTOWrapper<>();
        response.setData(List.of(resDto));
        response.setResponseMessage("Update User Success");
        response.setResponseStatus(ResponseStatusEnum.SUCCESS);
        response.setOriginalStatus(HttpStatus.OK);
        resStatus = HttpStatus.OK;

        // return
        return new ResponseEntity<>(response, resStatus);
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
    public ResponseEntity<ResponseDTOWrapper<UsersResponseDTO>> deleteUser(@PathVariable Long id) {
        // prepare
        ResponseDTOWrapper<UsersResponseDTO> response;
        HttpStatus resStatus;

        // process
        userService.deleteUser(id);
        response = new ResponseDTOWrapper<>();
        //response.setData(List.of("Delete User Success"));
        response.setResponseMessage("Delete User Success");
        response.setResponseStatus(ResponseStatusEnum.SUCCESS);
        response.setOriginalStatus(HttpStatus.OK);
        resStatus = HttpStatus.OK;

        // return
        return new ResponseEntity<>(response, resStatus);
    }
}
