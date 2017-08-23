package com.example.thancv.notepaid.config;

/**
 * Created by ThanCV on 8/22/2017.
 */

public class Constants {
    //database sqlite
    // All Static variables
    // Database Version
    public static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "note_paid";

    // Configuration table name
    public static final String TABLE_PAID = "table_paid";

    // Configuration Table Columns names
    public static final String KEY_ID = "KEY_ID";
    public static final String KEY_TITLE_NAME = "KEY_TITLE_NAME";
    public static final String KEY_MONEY_PAID = "KEY_MONEY_PAID";

    /**
     * Empty constructor
     * Fix Sonar warning
     */
    private Constants() {
        //Request a private constructor
    }
}
