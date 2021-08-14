package com.market.secondshoes.dto.item;

import com.market.secondshoes.domain.item.Brand;
import com.market.secondshoes.domain.item.Category;
import com.market.secondshoes.domain.item.Gender;
import com.market.secondshoes.domain.item.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.util.List;

@Getter
@Setter
public class ItemFindDto {

    private Gender gender;

    @Range(max = Integer.MAX_VALUE)
    private Integer minPrice;

    @Range(max = Integer.MAX_VALUE)
    private Integer maxPrice;

    private List<Size> sizes;

    private List<Brand> brands;

    private List<Category> categories;
}
