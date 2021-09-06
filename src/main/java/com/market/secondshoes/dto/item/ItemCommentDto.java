package com.market.secondshoes.dto.item;

import com.market.secondshoes.domain.item.ItemComment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ItemCommentDto {

    private Long id;
    private Long memberId;
    private String name;
    private String comment;
    private String createdDate;

    public static ItemCommentDto createItemCommentDto(ItemComment itemComment) {
        ItemCommentDto itemCommentDto = new ItemCommentDto();
        itemCommentDto.id = itemComment.getId();
        itemCommentDto.memberId = itemComment.getMember().getId();
        itemCommentDto.name = itemComment.getMember().getName();
        itemCommentDto.comment = itemComment.getComment();
        itemCommentDto.createdDate = itemComment.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return itemCommentDto;
    }
}
