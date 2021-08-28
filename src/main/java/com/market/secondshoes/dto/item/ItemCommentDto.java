package com.market.secondshoes.dto.item;

import com.market.secondshoes.domain.item.ItemComment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ItemCommentDto {

    private String name;
    private String comment;
    private LocalDateTime createdDate;

    public static ItemCommentDto createItemCommentDto(ItemComment itemComment) {
        ItemCommentDto itemCommentDto = new ItemCommentDto();
        itemCommentDto.name = itemComment.getMember().getName();
        itemCommentDto.comment = itemComment.getComment();
        itemCommentDto.createdDate = itemComment.getCreatedDate();
        return itemCommentDto;
    }
}
