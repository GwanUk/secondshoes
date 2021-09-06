package com.market.secondshoes.dto.item;

import com.market.secondshoes.domain.item.Item;
import com.market.secondshoes.domain.item.UploadImage;
import com.market.secondshoes.domain.member.Member;
import com.market.secondshoes.dto.member.MemberInfoDto;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Slf4j
public class ItemThumbDto {

    private Long id;
    private String title;
    private Integer price;
    private UploadImage uploadImage;
    private MemberInfoDto memberInfoDto;
    private String createdDate;
    private boolean itemWished = false;
    private Long viewCount;

    public static ItemThumbDto createItemThumbDto(Long id, String title, Integer price, UploadImage uploadImage, Member member, LocalDateTime createdDate, Long viewCount) {
        ItemThumbDto itemThumbDto = new ItemThumbDto();
        itemThumbDto.id = id;
        itemThumbDto.title = title;
        itemThumbDto.price = price;
        itemThumbDto.uploadImage = uploadImage;
        itemThumbDto.memberInfoDto = MemberInfoDto.createMemberInfoDto(member);
        itemThumbDto.createdDate = createdDate.format(DateTimeFormatter.ofPattern("yy-MM-dd H:mm"));
        itemThumbDto.viewCount = viewCount;
        return itemThumbDto;
    }

    public static ItemThumbDto createItemThumbDto(Item item) {
        UploadImage uploadImage = null;
        if (!item.getUploadImages().isEmpty()) {
            uploadImage = item.getUploadImages().get(0);
        }
        return createItemThumbDto(item.getId(), item.getTitle(), item.getPrice(), uploadImage, item.getMember(), item.getCreatedDate(), item.getViewCount());
    }

    public void wished() {
        itemWished = true;
    }
}
