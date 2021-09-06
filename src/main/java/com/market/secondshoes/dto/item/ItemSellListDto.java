package com.market.secondshoes.dto.item;

import com.market.secondshoes.domain.item.Item;
import com.market.secondshoes.domain.item.UploadImage;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
public class ItemSellListDto {

    private Long id;
    private String title;
    private List<UploadImage> uploadImages;
    private Long wishCount;
    private Long viewCount;
    private String createdDate;

    public static ItemSellListDto createItemSellListDto(Item item) {
        ItemSellListDto itemSellListDto = new ItemSellListDto();
        itemSellListDto.id = item.getId();
        itemSellListDto.title = item.getTitle();
        itemSellListDto.uploadImages = item.getUploadImages();
        itemSellListDto.wishCount = item.getWishCount();
        itemSellListDto.viewCount = item.getViewCount();
        itemSellListDto.createdDate = item.getCreatedDate().format(DateTimeFormatter.ofPattern("yy-MM-dd H:mm"));
        return itemSellListDto;
    }
}
