package com.market.secondshoes.service;

import com.market.secondshoes.dto.item.UploadImage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ImageStore {

    @Value("${file.dir}")
    private String fileDir;

    public List<UploadImage> storeImages(List<MultipartFile> multipartFiles) {
        return multipartFiles.stream()
                .filter(multipartFile -> !multipartFile.isEmpty())
                .map(multipartFile -> storeImage(multipartFile))
                .collect(Collectors.toList());
    }

    public UploadImage storeImage(MultipartFile multipartFile){

        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalImageName = multipartFile.getOriginalFilename();
        String storeImageName = createStoreImageName(originalImageName);

        try {
            multipartFile.transferTo(new File(getFullPath(storeImageName)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return UploadImage.createUploadImage(originalImageName, storeImageName);
    }

    private String createStoreImageName(String originalImageName) {
        return UUID.randomUUID().toString().concat(extractExt(originalImageName));
    }

    private String extractExt(String originalImageName) {
        return originalImageName.substring(originalImageName.lastIndexOf("."));
    }

    private String getFullPath(String fileName) {
        return fileDir + fileName;
    }
}
