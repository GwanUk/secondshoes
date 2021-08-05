package com.market.secondshoes.dto.item;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class UploadImg {

    private String uploadImageNames;
    private String storeImageName;

    protected UploadImg() {
    }

}
