package com.vermau2k01053.pos_System.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vermau2k01053.pos_System.model.StoreModel;
import lombok.Data;

import java.util.List;

@Data
public class StoreResponse extends BaseResponse {

    @JsonProperty("store_details")
    private StoreModel store;

    @JsonProperty("store_list")
    private List<StoreModel> storeList;
}
