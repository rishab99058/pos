package com.vermau2k01053.pos_System.service.api;

import com.vermau2k01053.pos_System.request.StoreRequest;
import com.vermau2k01053.pos_System.response.StoreResponse;

public interface StoreService {

    StoreResponse createStore(StoreRequest storeRequest);
    StoreResponse updateStore(StoreRequest storeRequest);
    StoreResponse deleteStore(StoreRequest storeRequest);
    StoreResponse getStore(StoreRequest storeRequest);
    StoreResponse getStoreAdmin(StoreRequest storeRequest);
    StoreResponse getStoreByEmployee(StoreRequest storeRequest);
    StoreResponse getStoreListing(StoreRequest storeRequest);
}
