package com.xi.gamis.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class GdzcAssetdetailServiceImplTest {

    @Autowired
    GdzcAssetdetailServiceImpl sv;
    @Test
    void getStorageLocationByDepartment() {
        System.out.println(sv.GetStorageLocationByDepartment(2).toString());
    }
}