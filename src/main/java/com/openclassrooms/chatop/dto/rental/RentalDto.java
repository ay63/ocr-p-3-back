package com.openclassrooms.chatop.dto.rental;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class RentalDto extends BaseRentalDto {

    private MultipartFile picture;
}
