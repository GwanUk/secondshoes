package com.market.secondshoes.domain.item;

import lombok.Getter;

@Getter
public enum Size {

    size220(220), size225(225),
    size230(230), size235(235),
    size240(240), size245(245),
    size250(250), size255(255),
    size260(260), size265(265),
    size270(270), size275(275),
    size280(280), size285(285),
    size290(290), size295(295),
    size300(300), size305(305),
    size310(310);

    private final int dimensions;

    Size(int dimensions) {
        this.dimensions = dimensions;
    }
    
}
