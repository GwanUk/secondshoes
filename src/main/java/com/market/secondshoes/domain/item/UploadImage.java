package com.market.secondshoes.domain.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UploadImage {

    private String uploadImageNames;
    private String storeImageName;

    public static UploadImage createUploadImage(String uploadImageNames, String storeImageName) {

        UploadImage uploadImage = new UploadImage();
        uploadImage.uploadImageNames = uploadImageNames;
        uploadImage.storeImageName = storeImageName;

        return uploadImage;
    }
}
