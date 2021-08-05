package com.market.secondshoes.dto.item;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class ItemSellDto {

    @NotBlank
    private String title;

//    private String tags;

    private Long price;
    private String explain;
    private List<MultipartFile> images;
}
