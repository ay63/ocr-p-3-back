package com.openclassrooms.chatop.dto.rental;

import org.springframework.web.multipart.MultipartFile;

public class RentalDto extends RentalDtoAbstract {

    private MultipartFile picture;

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }

}
