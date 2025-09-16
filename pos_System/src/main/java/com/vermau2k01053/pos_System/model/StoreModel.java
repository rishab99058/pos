package com.vermau2k01053.pos_System.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vermau2k01053.pos_System.domain.StoreStatus;
import lombok.Data;

@Data
public class StoreModel {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("store_admin")
    private UserModel storeAdmin;

    @JsonProperty("store_type")
    private String storeType;

    @JsonProperty("store_status")
    private StoreStatus storeStatus;

    @JsonProperty("store_contact")
    private StoreContactModel storeContact;
}
