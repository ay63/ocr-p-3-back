package com.openclassrooms.chatop.dto.rental;


public class RentalResponseDto extends RentalDtoAbstract {

    private Integer id;

    private String picture;

    public String getPicture() {
        return picture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}
