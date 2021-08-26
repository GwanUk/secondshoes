package com.market.secondshoes.dto.item;

import com.market.secondshoes.domain.Wish;
import com.market.secondshoes.domain.item.Item;
import com.market.secondshoes.domain.item.UploadImage;
import com.market.secondshoes.domain.member.Member;
import com.market.secondshoes.dto.member.MemberInfoDto;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Getter
@Setter
@Slf4j
public class ItemThumbDto {

    private Long id;
    private String title;
    private Integer price;
    private UploadImage uploadImage;
    private MemberInfoDto memberInfoDto;
    private Long ago;
    private boolean wished = false;

    public static ItemThumbDto createItemThumbDto(Long id, String title, Integer price, UploadImage uploadImage, Member member, LocalDateTime localDateTime) {
        ItemThumbDto itemThumbDto = new ItemThumbDto();
        itemThumbDto.id = id;
        itemThumbDto.title = title;
        itemThumbDto.price = price;
        itemThumbDto.uploadImage = uploadImage;
        itemThumbDto.memberInfoDto = MemberInfoDto.createMemberInfoDto(member);
        itemThumbDto.ago = Duration.between(localDateTime, LocalDateTime.now()).toMinutes();
        return itemThumbDto;
    }

    public static ItemThumbDto createItemThumbDto(Item item) {
        UploadImage uploadImage = null;
        if (!item.getUploadImages().isEmpty()) {
            uploadImage = item.getUploadImages().get(0);
        }
        return createItemThumbDto(item.getId(), item.getTitle(), item.getPrice(), uploadImage, item.getMember(), item.getCreatedDate());
    }
}
