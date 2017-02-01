/*package helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseHelper extends SQLiteAssetHelper {

	

	// Table Create Statements
	// HYMN table create statement
	private static final String CREATE_TABLE_HYMN = "CREATE TABLE "
			+ TABLE_HYMN + "(" + HYMN_ID + " INTEGER PRIMARY KEY," + HYMN_NO
			+ " INTEGER," + HYMN_TITLE + " TEXT," + HYMN_SCRIPTURE + " TEXT,"
			+ HYMN_CONTENT + "TEXT," + HYMN_AUTHOR + "TEXT" + ")";

	// APPENDIX table create statement
	private static final String CREATE_TABLE_APX = "CREATE TABLE "
			+ TABLE_APPENDIX + "(" + APX_ID + " INTEGER PRIMARY KEY," + APX_NO
			+ " INTEGER," + APX_TITLE + " TEXT," + APX_SCRIPTURE + " TEXT,"
			+ APX_CONTENT + "TEXT," + APX_AUTHOR + "TEXT" + ")";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	

	@Override
	public void onCreate(SQLiteDatabase hymn_db) {

		// creating required tables
		hymn_db.execSQL(CREATE_TABLE_HYMN);
		hymn_db.execSQL(CREATE_TABLE_APX);

	}
	

	public Cursor getHymnItems(){
		
		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		
		qb.setTables(TABLE_HYMN);
		Cursor c = qb.query(db, null, null, null, null, null, null);
		c.moveToFirst();
		
		return c;
	}
	
public Cursor getAppendixItems(){
		
		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		
		qb.setTables(TABLE_APPENDIX);
		Cursor c = qb.query(db, null, null, null, null, null, null);
		c.moveToFirst();
		
		return c;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// on upgrade drop older tables
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_HYMN);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPENDIX);

		// create new tables
		onCreate(db);
	}

	public long createHymn(Hymns hymn) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(HYMN_NO, hymn.getHymnNo());
		values.put(HYMN_TITLE, hymn.getHymnTitle());
		values.put(HYMN_SCRIPTURE, hymn.getHymnScripture());
		values.put(HYMN_CONTENT, hymn.getHymnContent());
		values.put(HYMN_AUTHOR, hymn.getHymnAuthor());

		return db.insert(TABLE_HYMN, null, values);

	}

	public long createAppendix(Appendix appendix) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(APX_NO, appendix.getApxNo());
		values.put(APX_TITLE, appendix.getApxTitle());
		values.put(APX_SCRIPTURE, appendix.getApxScripture());
		values.put(APX_CONTENT, appendix.getApxContent());
		values.put(APX_AUTHOR, appendix.getApxAuthor());

		return db.insert(TABLE_APPENDIX, null, values);

	}

	
	 * get single hymn
	 
	public Hymns getHymn(int hymn_no) {
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_HYMN + " WHERE "
				+ HYMN_NO + " = " + hymn_no;

		Log.e(LOG, selectQuery);

		Cursor c = db.rawQuery(selectQuery, null);

		if (c != null)
			c.moveToFirst();

		Hymns hymn = new Hymns();
		hymn.setId(c.getInt(c.getColumnIndex(HYMN_ID)));
		hymn.setHymnNo(c.getInt(c.getColumnIndex(HYMN_NO)));
		hymn.setHymnTitle(c.getString(c.getColumnIndex(HYMN_TITLE)));
		hymn.setHymnScripture(c.getString(c.getColumnIndex(HYMN_SCRIPTURE)));
		hymn.setHymnContent(c.getString(c.getColumnIndex(HYMN_CONTENT)));
		hymn.setHymnAuthor(c.getString(c.getColumnIndex(HYMN_AUTHOR)));

		return hymn;
	}

	
	 * get single appendix
	 
	public Appendix getAppendix(int apx_no) {
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_APPENDIX + " WHERE "
				+ APX_NO + " = " + apx_no;

		Log.e(LOG, selectQuery);

		Cursor c = db.rawQuery(selectQuery, null);

		if (c != null)
			c.moveToFirst();

		Appendix apx = new Appendix();
		apx.setId(c.getInt(c.getColumnIndex(APX_ID)));
		apx.setApxNo(c.getInt(c.getColumnIndex(APX_NO)));
		apx.setApxTitle(c.getString(c.getColumnIndex(APX_TITLE)));
		apx.setApxScripture(c.getString(c.getColumnIndex(APX_SCRIPTURE)));
		apx.setApxContent(c.getString(c.getColumnIndex(APX_CONTENT)));
		apx.setApxAuthor(c.getString(c.getColumnIndex(APX_AUTHOR)));

		return apx;
	}

	*//**
	 * getting all hymns
	 * *//*
	public List<Hymns> getAllHymns() {
		List<Hymns> hymns = new ArrayList<Hymns>();
		String selectQuery = "SELECT  * FROM " + TABLE_HYMN;

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Hymns hymn = new Hymns();
				hymn.setId(c.getInt(c.getColumnIndex(HYMN_ID)));
				hymn.setHymnNo(c.getInt(c.getColumnIndex(HYMN_NO)));
				hymn.setHymnTitle(c.getString(c.getColumnIndex(HYMN_TITLE)));
				hymn.setHymnScripture(c.getString(c
						.getColumnIndex(HYMN_SCRIPTURE)));
				hymn.setHymnContent(c.getString(c.getColumnIndex(HYMN_CONTENT)));
				hymn.setHymnAuthor(c.getString(c.getColumnIndex(HYMN_AUTHOR)));

				// adding to hymns list
				hymns.add(hymn);
			} while (c.moveToNext());
		}

		return hymns;
	}

	public List<Appendix> getAllAppendix() {
		List<Appendix> appendix = new ArrayList<Appendix>();
		String selectQuery = "SELECT  * FROM " + TABLE_APPENDIX;

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {

				Appendix apx = new Appendix();
				apx.setId(c.getInt(c.getColumnIndex(APX_ID)));
				apx.setApxNo(c.getInt(c.getColumnIndex(APX_NO)));
				apx.setApxTitle(c.getString(c.getColumnIndex(APX_TITLE)));
				apx.setApxScripture(c.getString(c.getColumnIndex(APX_SCRIPTURE)));
				apx.setApxContent(c.getString(c.getColumnIndex(APX_CONTENT)));
				apx.setApxAuthor(c.getString(c.getColumnIndex(APX_AUTHOR)));

				// adding to hymns list
				appendix.add(apx);
			} while (c.moveToNext());
		}

		return appendix;
	}

	// closing database
	public void closeDB() {
		SQLiteDatabase db = this.getReadableDatabase();
		if (db != null && db.isOpen())
			db.close();

	}

}
*/