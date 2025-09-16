package com.vermau2k01053.pos_System.jpaModel;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class StoreContact {

    private String address;
    private String email;
    private String phone;
}
