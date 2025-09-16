package com.vermau2k01053.pos_System.repository;

import com.vermau2k01053.pos_System.jpaModel.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store,String> {
}
