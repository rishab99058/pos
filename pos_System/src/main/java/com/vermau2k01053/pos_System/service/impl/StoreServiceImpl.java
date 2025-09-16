package com.vermau2k01053.pos_System.service.impl;

import com.vermau2k01053.pos_System.repository.StoreRepository;
import com.vermau2k01053.pos_System.request.StoreRequest;
import com.vermau2k01053.pos_System.response.StoreResponse;
import com.vermau2k01053.pos_System.service.api.StoreService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final Logger logger = LoggerFactory.getLogger(StoreServiceImpl.class);

    @Override
    public StoreResponse createStore(StoreRequest storeRequest) {
        return null;
    }

    @Override
    public StoreResponse updateStore(StoreRequest storeRequest) {
        return null;
    }

    @Override
    public StoreResponse deleteStore(StoreRequest storeRequest) {
        return null;
    }

    @Override
    public StoreResponse getStore(StoreRequest storeRequest) {
        return null;
    }

    @Override
    public StoreResponse getStoreAdmin(StoreRequest storeRequest) {
        return null;
    }

    @Override
    public StoreResponse getStoreByEmployee(StoreRequest storeRequest) {
        return null;
    }

    @Override
    public StoreResponse getStoreListing(StoreRequest storeRequest) {
        return null;
    }
}
