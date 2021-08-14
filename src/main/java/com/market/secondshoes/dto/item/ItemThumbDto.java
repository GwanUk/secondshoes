package com.market.secondshoes.dto.item;

import com.market.secondshoes.domain.item.Item;
import com.market.secondshoes.domain.item.UploadImage;
import com.market.secondshoes.domain.member.Member;
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
    private LocalDateTime createdDate;
    private List<UploadImage> uploadImages ;
    private Member member;

    private ItemThumbDto() {
    }

    public static ItemThumbDto createItemThumb(Long id, String title, Integer price, LocalDateTime createdDate, List<UploadImage> uploadImages, Member member) {
        ItemThumbDto itemThumbDto = new ItemThumbDto();
        itemThumbDto.id = id;
        itemThumbDto.title = title;
        itemThumbDto.price = price;
        itemThumbDto.createdDate = createdDate;
        itemThumbDto.uploadImages = uploadImages;
        itemThumbDto.member = member;

        return itemThumbDto;
    }

    public static ItemThumbDto createItemThumb(Item item) {
        return createItemThumb(item.getId(), item.getTitle(), item.getPrice(), item.getCreatedDate(), item.getUploadImages(), item.getMember());
    }
}
