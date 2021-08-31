package com.market.secondshoes.dto.item;

import com.market.secondshoes.domain.item.Item;
import com.market.secondshoes.domain.item.UploadImage;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ItemSellListDto {

    private Long id;
    private String title;
    private List<UploadImage> uploadImages;
    private Integer wishCount;
    private Integer commentCount;
    private LocalDateTime createdDate;

    public static ItemSellListDto createItemSellListDto(Item item) {
        ItemSellListDto itemSellListDto = new ItemSellListDto();
        itemSellListDto.id = item.getId();
        itemSellListDto.title = item.getTitle();
        itemSellListDto.uploadImages = item.getUploadImages();
        itemSellListDto.wishCount = item.getItemWishes().size();
        itemSellListDto.commentCount = item.getItemComments().size();
        itemSellListDto.createdDate = item.getCreatedDate();
        return itemSellListDto;
    }
}
