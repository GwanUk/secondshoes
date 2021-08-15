package com.market.secondshoes.service;

import com.market.secondshoes.domain.item.UploadImage;
import com.market.secondshoes.exception.ImageExceededException;
import com.market.secondshoes.exception.ImageExtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ImageStore {

    @Value("${file.dir}")
    private String fileDir;

    private final String imageExt[] = new String[]{".jpg", ".png", ".jpeg", ".gif"};

    public List<UploadImage> storeImages(List<MultipartFile> multipartFiles) {

        if (multipartFiles.size() > 5) {
            throw new ImageExceededException("이미지 파일의 개수가 5개 초과입니다.");
        }

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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return UploadImage.createUploadImage(originalImageName, storeImageName);
    }

    private String createStoreImageName(String originalImageName) {
        return UUID.randomUUID().toString().concat(extractExt(originalImageName));
    }

    private String extractExt(String originalImageName) {
        String ext = originalImageName.substring(originalImageName.lastIndexOf("."));
        imageExtCheck(ext);
        return ext;
    }

    private void imageExtCheck(String ext) {
        if (Arrays.stream(imageExt).filter(s -> s.equals(ext)).findFirst().isEmpty()) {
            throw new ImageExtException("이미지 파일이 아닙니다.");
        }
    }

    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }
}
