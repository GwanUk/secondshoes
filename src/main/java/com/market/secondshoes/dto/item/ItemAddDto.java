package com.market.secondshoes.dto.item;

import com.market.secondshoes.domain.item.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class ItemAddDto {

    private Long id;

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

    @Range(max = Integer.MAX_VALUE)
    private Integer price;

    @Length(max = 10)
    private String explain;

    private List<MultipartFile> images;

    public static ItemAddDto createItemAddDto(Item item) {
        ItemAddDto itemAddDto = new ItemAddDto();
        if (item.getId() != null) {
            itemAddDto.id = item.getId();
        }
        itemAddDto.title = item.getTitle();
        itemAddDto.gender = item.getGender();
        itemAddDto.size = item.getSize();
        itemAddDto.brand = item.getBrand();
        itemAddDto.category = item.getCategory();
        itemAddDto.price = item.getPrice();
        itemAddDto.explain = item.getExplain();

        return itemAddDto;
    }
}
