package com.example.thancv.notepaid.database;

import com.example.thancv.notepaid.model.PaidModel;

import java.util.List;

/**
 * Created by ThanCV on 8/3/2017.
 */

public interface CRUDManagerInterface<O> {
    void insertNewObject(O o);

    List<O> getAllObjects();

    void updateObject(O o);

    void deleteObject(O o);

    void deleteAll();
}
