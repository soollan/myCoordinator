package com.musinsa.mycoordinator.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class LowestResponse {

    @JsonProperty(value = "최저가")
    BrandResponse lowest;

    public LowestResponse(BrandResponse brandResponse) {
        this.lowest = brandResponse;
    }
}
