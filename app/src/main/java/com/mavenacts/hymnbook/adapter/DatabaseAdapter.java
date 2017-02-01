package com.mavenacts.hymnbook.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseAdapter {

	// Database Names
	private static final String DATABASE_NAME = "hymns.db";

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Table Names
	private static final String TABLE_HYMN = "hymn";
	private static final String TABLE_APPENDIX = "appendix";

	// TABLE_HYMN column names
	public static final String HYMN_ID = "_id";
	public static final String HYMN_NO = "hymn_no";
	public static final String HYMN_TITLE = "hymn_title";
	public static final String HYMN_SCRIPTURE = "hymn_scripture";
	public static final String HYMN_STANZAS = "hymn_stanzas";
	public static final String HYMN_CHORUS = "hymn_chorus";
	public static final String HYMN_AUTHOR = "hymn_author";

	// TABLE_APPENDIX column names
	public static final String APX_ID = "_id";
	public static final String APX_NO = "apx_no";
	public static final String APX_TITLE = "apx_title";
	public static final String APX_SCRIPTURE = "apx_scripture";
	public static final String APX_STANZAS = "apx_stanzas";
	public static final String APX_CHORUS = "apx_chorus";
	public static final String APX_AUTHOR = "apx_author";

	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	private final Context mCtx;

	private static class DatabaseHelper extends SQLiteAssetHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// on upgrade drop older tables
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_HYMN);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPENDIX);

		}

		public SQLiteDatabase getHymnFromDb() {

			SQLiteDatabase db = getReadableDatabase();

			return db;
		}

	}

	public DatabaseAdapter(Context ctx) {
		this.mCtx = ctx;
	}

	public DatabaseAdapter open() throws SQLException {
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		if (mDbHelper != null) {
			mDbHelper.close();
		}
	}

	public Cursor getHymns() {

		DatabaseHelper dbHelper = new DatabaseAdapter.DatabaseHelper(mCtx);

		SQLiteDatabase db = dbHelper.getHymnFromDb();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		qb.setTables(TABLE_HYMN);
		Cursor c = qb.query(db, new String[] { HYMN_ID, HYMN_TITLE }, null,
				null, null, null, null);
		c.moveToFirst();

		return c;
	}
	
	/*@SuppressWarnings("unchecked")
	public ArrayList<String> getHyData() {

		DatabaseHelper dbHelper = new DatabaseAdapter.DatabaseHelper(mCtx);

		SQLiteDatabase db = dbHelper.getHymnFromDb();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		qb.setTables(TABLE_HYMN);
		Cursor c = qb.query(db, new String[] {HYMN_TITLE }, null,
				null, null, null, null);
		c.moveToFirst();

		return (ArrayList<String>) c;
	}*/

	public Cursor getAppendix() {
		DatabaseHelper dbHelper = new DatabaseAdapter.DatabaseHelper(mCtx);

		SQLiteDatabase db = dbHelper.getHymnFromDb();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		qb.setTables(TABLE_APPENDIX);
		Cursor c = qb.query(db, new String[] { APX_ID, APX_TITLE }, null, null,
				null, null, null);
		c.moveToFirst();

		return c;
	}

	public Cursor getHymnItem(String id) {

		DatabaseHelper dbHelper = new DatabaseAdapter.DatabaseHelper(mCtx);

		SQLiteDatabase db = dbHelper.getHymnFromDb();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		Cursor c = null;

		qb.setTables(TABLE_HYMN);

		try {

			c = qb.query(db, new String[] { HYMN_ID, HYMN_TITLE,
					HYMN_SCRIPTURE, HYMN_STANZAS, HYMN_AUTHOR }, HYMN_ID + "="
					+ id, null, null, null, null);

			c.moveToFirst();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return c;

	}

	public Cursor getAppendixItem(String id) {

		DatabaseHelper dbHelper = new DatabaseAdapter.DatabaseHelper(mCtx);

		SQLiteDatabase db = dbHelper.getHymnFromDb();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		Cursor c = null;

		qb.setTables(TABLE_APPENDIX);

		try {

			c = qb.query(db, new String[] { APX_ID, APX_TITLE, APX_SCRIPTURE,
					APX_STANZAS, APX_AUTHOR }, APX_ID + "=" + id, null, null,
					null, null);

			c.moveToFirst();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return c;

	}

    public Cursor searchHymn(String query) {
        DatabaseHelper dbHelper = new DatabaseAdapter.DatabaseHelper(mCtx);

        SQLiteDatabase db = dbHelper.getHymnFromDb();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        qb.setTables(TABLE_HYMN);

        Cursor c = qb.query(db, new String[]{HYMN_ID, HYMN_TITLE}, HYMN_ID + "=?", new String[]{query}, null, null, HYMN_ID, null);
        c.moveToFirst();

        return c;
    }

    public Cursor searchAppendix(String query) {
        DatabaseHelper dbHelper = new DatabaseAdapter.DatabaseHelper(mCtx);

        SQLiteDatabase db = dbHelper.getHymnFromDb();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        qb.setTables(TABLE_APPENDIX);

        Cursor c = qb.query(db, new String[] {APX_ID, APX_TITLE },APX_ID + "=?" ,new String[]{query},null,null,APX_ID,null);
        c.moveToFirst();

        return c;
    }


//        @param db the database to query on
//        * @param projectionIn A list of which columns to return. Passing
//                *   null will return all columns, which is discouraged to prevent
//        *   reading data from storage that isn't going to be used.
//                * @param selection A filter declaring which rows to return,
//        *   formatted as an SQL WHERE clause (excluding the WHERE
//        *   itself). Passing null will return all rows for the given URL.
//                * @param selectionArgs You may include ?s in selection, which
//        *   will be replaced by the values from selectionArgs, in order
//                *   that they appear in the selection. The values will be bound
//                *   as Strings.
//                * @param groupBy A filter declaring how to group rows, formatted
//                *   as an SQL GROUP BY clause (excluding the GROUP BY
//                *   itself). Passing null will cause the rows to not be grouped.
//        * @param having A filter declare which row groups to include in
//        *   the cursor, if row grouping is being used, formatted as an
//        *   SQL HAVING clause (excluding the HAVING itself).  Passing
//                *   null will cause all row groups to be included, and is
//                *   required when row grouping is not being used.
//                * @param sortOrder How to order the rows, formatted as an SQL
//                *   ORDER BY clause (excluding the ORDER BY itself). Passing null
//                *   will use the default sort order, which may be unordered.
//                * @param limit Limits the number of rows returned by the query,
//        *   formatted as LIMIT clause. Passing null denotes no LIMIT clause.
//        * @return a cursor over the result set
//                * @see android.content.ContentResolver#query(android.net.Uri, String[],
//        *      String, String[], String)
//        */

}
