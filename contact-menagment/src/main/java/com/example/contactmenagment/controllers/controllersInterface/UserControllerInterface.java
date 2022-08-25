package com.example.contactmenagment.controllers.controllersInterface;

import com.example.contactmenagment.controllers.userDTO.UserRequestDTO;
import com.example.contactmenagment.controllers.userDTO.UserResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.UUID;

public interface UserControllerInterface  extends GlobalApiResponseSetup{
    @Tag(name="Get All Users.", description = "Get All UserDTO's.")
    Page<UserResponseDTO> getAllUsers(Pageable pageable);

    @Tag(name="Get one UserResponseDTO.", description = "Get one UserResponseDTO by passing UID.")
    ResponseEntity<UserResponseDTO> getUserByUid(@PathVariable UUID userUid);

    @Tag(name="Save User.", description = "Send UserRequestDTO and save User.")
    void saveUser(@Valid @RequestBody UserRequestDTO s);


    @Tag(name="Update User.", description = "Update User by sending His UID and UserRequestDTO as body.")
    ResponseEntity<UserResponseDTO> updateUser(@Valid @PathVariable UUID userUid, @RequestBody UserRequestDTO user);

    @Tag(name="Delete User", description = "Delete User by sending his UID.")
    void deleteUserByUid(@PathVariable UUID userUid);
}
