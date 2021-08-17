package com.market.secondshoes.dto.item;

import com.market.secondshoes.domain.item.Brand;
import com.market.secondshoes.domain.item.Category;
import com.market.secondshoes.domain.item.Gender;
import com.market.secondshoes.domain.item.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import java.util.List;

@Getter
@Setter
@ToString
public class ItemConditionDto {

    private String search;

    private Gender gender;

    @Range(max = Integer.MAX_VALUE)
    private Integer priceGoe;

    @Range(max = Integer.MAX_VALUE)
    private Integer priceLoe;

    private List<Size> sizes;

    private List<Brand> brands;

    private List<Category> categories;

    private Integer page;

    private Integer size;
}
