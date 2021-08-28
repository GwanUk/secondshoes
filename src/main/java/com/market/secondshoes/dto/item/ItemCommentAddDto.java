package com.market.secondshoes.dto.item;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemCommentAddDto {

    private Long itemId;
    private Long memberId;
    private String comment;

    public static ItemCommentAddDto createItemCommentAddDto(Long itemId) {
        ItemCommentAddDto itemCommentAddDto = new ItemCommentAddDto();
        itemCommentAddDto.itemId = itemId;
        return itemCommentAddDto;
    }
}
