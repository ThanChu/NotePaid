package com.example.thancv.notepaid.database;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thancv.notepaid.config.Constants;
import com.example.thancv.notepaid.model.PaidModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThanCV on 8/3/2017.
 */

public class CRUDConfigurationManager implements CRUDManagerInterface<PaidModel> {
    private DatabaseHandler databaseHandler;

    public CRUDConfigurationManager(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

    @Override
    public void insertNewObject(PaidModel paidModel) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_TITLE_NAME, paidModel.getTitleName());
        values.put(Constants.KEY_MONEY_PAID, paidModel.getMoneyPaid());

        // Inserting Row
        db.insert(Constants.TABLE_PAID, null, values);
        db.close();
    }

    @Override
    public List<PaidModel> getAllObjects() {
        List<PaidModel> paidList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + Constants.TABLE_PAID;

        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PaidModel model = new PaidModel();
                model.setId(cursor.getString(0));
                model.setTitleName(cursor.getString(1));
                model.setMoneyPaid(cursor.getString(2));
                paidList.add(model);
            } while (cursor.moveToNext());
        }
        return paidList;
    }

    @Override
    public void updateObject(PaidModel paidModel) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_TITLE_NAME, paidModel.getTitleName());
        values.put(Constants.KEY_MONEY_PAID, paidModel.getMoneyPaid());

        // updating row
        db.update(Constants.TABLE_PAID, values, Constants.KEY_ID + " = ?",
                new String[]{String.valueOf(paidModel.getId())});
        db.close();
    }

    @Override
    public void deleteObject(PaidModel paidModel) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        db.delete(Constants.TABLE_PAID, Constants.KEY_ID + " = ?",
                new String[]{String.valueOf(paidModel.getId())});
        db.close();
    }

    @Override
    public void deleteAll() {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        String deleteQuery = "DELETE FROM " + Constants.TABLE_PAID;
        db.execSQL(deleteQuery);
        db.close();
    }
}
