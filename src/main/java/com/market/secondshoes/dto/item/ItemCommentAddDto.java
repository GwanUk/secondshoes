package com.market.secondshoes.dto.item;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class ItemCommentAddDto {

    private String comment;
    private Long itemId;
    private Long memberId;
}
