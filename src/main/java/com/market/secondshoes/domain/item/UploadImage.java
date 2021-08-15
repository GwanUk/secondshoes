package com.market.secondshoes.domain.item;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class UploadImage {

    private String uploadImageNames;
    private String storeImageName;

    protected UploadImage() {
    }

    public static UploadImage createUploadImage(String uploadImageNames, String storeImageName) {

        UploadImage uploadImage = new UploadImage();
        uploadImage.uploadImageNames = uploadImageNames;
        uploadImage.storeImageName = storeImageName;

        return uploadImage;
    }

    @Override
    public String toString() {
        return "UploadImage{" +
                "uploadImageNames='" + uploadImageNames + '\'' +
                ", storeImageName='" + storeImageName + '\'' +
                '}';
    }
}
