package com.example.contactmenagment.controllers.controllersInterface;

import com.example.contactmenagment.controllers.contactTypeDTO.ContactTypeRequestDTO;
import com.example.contactmenagment.controllers.contactTypeDTO.ContactTypeResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.UUID;

public interface ContactTypeControllerInterface extends GlobalApiResponseSetup {

    @Tag(name="Get All ContactTypeResponseDTO.")
    Page<ContactTypeResponseDTO> getAllContactTypePagination(Pageable pageable);
    @Tag(name="Get ContactType by UID.", description = "Get one ContactTypeResponseDTO by passing his UID.")
    ContactTypeResponseDTO getContactTypeByUid(@PathVariable UUID contactTypeUid);

    @Tag(name="Save ContactType", description = "Send ContactTypeRequestDTO and Save ContactType.")
    void saveContactType(@RequestBody ContactTypeRequestDTO contactTypeRequestDTO);


    @Tag(name="Update ContactType", description = "Update ContactType, pass his UID and ContactTypeRequestDTO, change the information you want!")
    void updateContactType(@PathVariable UUID contactTypeUid, @RequestBody ContactTypeRequestDTO c);

    @Tag(name="Delete ContactType", description = "Delete ContactType by passing his UID.")
    void deleteContactTypeById(@PathVariable UUID contactTypeUid);
}
