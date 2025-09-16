package com.vermau2k01053.pos_System.jpaModel;

import com.vermau2k01053.pos_System.domain.StoreStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "store")
@EqualsAndHashCode
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String name;
    @OneToOne
    private User storeAdmin;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime deletedDate;
    private String storeType;
    private StoreStatus storeStatus;
    private StoreContact storeContact;

    @PrePersist
    protected void onCreate()
    {
        createdDate = LocalDateTime.now();
        updatedDate = LocalDateTime.now();
        storeStatus = StoreStatus.WAITING_FOR_APPROVAL;
    }

    @PreUpdate
    protected void onUpdate()
    {
        updatedDate = LocalDateTime.now();
    }
}
