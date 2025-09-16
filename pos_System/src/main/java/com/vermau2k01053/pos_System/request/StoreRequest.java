package com.vermau2k01053.pos_System.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StoreRequest {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("store_admin_id")
    private String storeAdminId;
    @JsonProperty("store_type")
    private String storeType;
    @JsonProperty("store_status")
    private String storeStatus;
    @JsonProperty("store_contact")
    private StoreContactRequest storeContact;
}

