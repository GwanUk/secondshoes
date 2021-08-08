package com.market.secondshoes.dto.item;

import com.market.secondshoes.domain.item.Brand;
import com.market.secondshoes.domain.item.Category;
import com.market.secondshoes.domain.item.Gender;
import com.market.secondshoes.domain.item.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class ItemAddDto {

    @NotBlank
    private String title;

    private Gender gender;

    private Size size;

    private Brand brand;

    private Category category;

    private Long price;

    private String explain;

    private List<MultipartFile> images;
}
