package com.openclassrooms.chatop.dto.rental;

import com.openclassrooms.chatop.validators.multipart.NotEmptyMultipartFile;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class RentalCreateDto extends BaseRentalDto {

    @NotEmptyMultipartFile
    private MultipartFile picture;
}
