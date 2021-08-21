package com.market.secondshoes.dto.item;

import com.market.secondshoes.domain.item.*;
import com.market.secondshoes.domain.member.Member;
import com.market.secondshoes.dto.member.MemberInfoDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ItemThumbDto {

    private Long id;
    private String title;
    private Integer price;
    private UploadImage uploadImage;
    private String member_name;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public static ItemThumbDto createItemThumbDto(Long id, String title, Integer price, UploadImage uploadImage, String member_name, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        ItemThumbDto itemThumbDto = new ItemThumbDto();
        itemThumbDto.id = id;
        itemThumbDto.title = title;
        itemThumbDto.price = price;
        itemThumbDto.uploadImage = uploadImage;
        itemThumbDto.member_name = member_name;
        itemThumbDto.createdDate = createdDate;
        itemThumbDto.lastModifiedDate = lastModifiedDate;
        return itemThumbDto;
    }

    public static ItemThumbDto createItemThumbDto(Item item) {
        return createItemThumbDto(item.getId(), item.getTitle(), item.getPrice(), item.getUploadImages().get(0), item.getMember().getName(), item.getCreatedDate(), item.getLastModifiedDate());
    }
}
