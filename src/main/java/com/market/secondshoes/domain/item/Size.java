package com.market.secondshoes.domain.item;

import lombok.Getter;

@Getter
public enum Size {

    SIZE220(220), SIZE225(225),
    SIZE230(230), SIZE235(235),
    SIZE240(240), SIZE245(245),
    SIZE250(250), SIZE255(255),
    SIZE260(260), SIZE265(265),
    SIZE270(270), SIZE275(275),
    SIZE280(280), SIZE285(285),
    SIZE290(290), SIZE295(295),
    SIZE300(300), SIZE305(305),
    SIZE310(310);

    private final Integer dimensions;

    Size(Integer dimensions) {
        this.dimensions = dimensions;
    }
    
}
