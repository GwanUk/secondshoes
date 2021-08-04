package com.market.secondshoes.dto.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Data
public class ItemSellDto {

    @NotBlank
    private String title;

    private String tags;

    private Long price;
    private String explain;
    private MultipartFile imgs;
}
