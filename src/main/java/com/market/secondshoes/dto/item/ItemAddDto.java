package com.market.secondshoes.dto.item;

import com.market.secondshoes.domain.item.Brand;
import com.market.secondshoes.domain.item.Category;
import com.market.secondshoes.domain.item.Gender;
import com.market.secondshoes.domain.item.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class ItemAddDto {

    @NotBlank
    private String title;

    @NotNull
    private Gender gender;

    @NotNull
    private Size size;

    @NotNull
    private Brand brand;

    @NotNull
    private Category category;

    private Long price;

    @Length(max = 10)
    private String explain;

    private List<MultipartFile> images;
}
