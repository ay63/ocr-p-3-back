package com.openclassrooms.chatop.dto.rental;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.openclassrooms.chatop.constraints.multipart.NotEmptyMultipartFile;
import com.openclassrooms.chatop.constraints.multipart.fileFormat.IsImage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class RentalCreateDto extends BaseRentalDto {

    @IsImage
    @NotEmptyMultipartFile
    private MultipartFile picture;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

}
