package br.unb.cic.reminders.model.db;

public class DBConstants {
	public static String DROP_TABLE_STATEMENTS[] = { "DROP TABLE IF EXISTS REMINDER"
			, "DROP TABLE IF EXISTS CATEGORY"
	};
	public static String CREATE_TABLE_STATEMENTS[] = { "CREATE TABLE REMINDER ( "
			+ "PK INTEGER PRIMARY KEY AUTOINCREMENT, " + "TEXT VARCHAR(50) NOT NULL," + "DETAILS VARCHAR(50) NULL,"
			+ "FK_CATEGORY INTEGER NOT NULL REFERENCES CATEGORY ON DELETE CASCADE,"
			+ "INITIAL_DATE CHAR(10) NOT NULL," + "INITIAL_HOUR CHAR(5) NULL," + "FINAL_DATE CHAR(10) NOT NULL,"
			+ "FINAL_HOUR CHAR(5) NULL,"
			+ "DONE INTEGER NOT NULL);"
			, "CREATE TABLE CATEGORY(" + "PK INTEGER PRIMARY KEY AUTOINCREMENT, " + "NAME VARCHAR(50) NOT NULL, "
					+ "LOCKED INT NOT NULL);"
	};
	public static final String SELECT_REMINDERS = "SELECT * FROM REMINDER";
	public static String REMINDER_TABLE = "REMINDER";
	public static String REMINDER_PK_COLUMN = "PK";
	public static String REMINDER_TEXT_COLUMN = "TEXT";
	public static String REMINDER_DETAILS_COLUMN = "DETAILS";
	public static String REMINDER_DONE_COLUMN = "DONE";
	public static String REMINDER_INITIAL_DATE_COLUMN = "INITIAL_DATE";
	public static String REMINDER_INITIAL_HOUR_COLUMN = "INITIAL_HOUR";
	public static String REMINDER_FINAL_DATE_COLUMN = "FINAL_DATE";
	public static String REMINDER_FINAL_HOUR_COLUMN = "FINAL_HOUR";
	public static final String SELECT_CATEGORIES = "SELECT PK, NAME FROM CATEGORY";
	public static final String SELECT_CATEGORY_BY_NAME = "SELECT PK, NAME FROM CATEGORY WHERE NAME = ?";
	public static final String SELECT_CATEGORY_BY_ID = "SELECT PK, NAME FROM CATEGORY WHERE PK = ?";
	public static final String SELECT_REMINDERS_BY_CATEGORY = "SELECT * FROM REMINDER WHERE FK_CATEGORY = ?";
	public static String DELETE_CATEGORIES = "DELETE FROM CATEGORY WHERE PK = ?";
	public static final String PREDEFINED_CATEGORIES[] = { "INSERT INTO CATEGORY VALUES (NULL,'College',1);",
			"INSERT INTO CATEGORY VALUES (NULL,'Job',1);", "INSERT INTO CATEGORY VALUES (NULL,'Personal',1);" };
	public static String CATEGORY_TABLE = "CATEGORY";
	public static String CATEGORY_PK_COLUMN = "PK";
	public static String CATEGORY_NAME_COLUMN = "NAME";
	public static String CATEGORY_LOCKED_COLUMN = "LOCKED";
	public static String REMINDER_FK_CATEGORY_COLUMN = "FK_CATEGORY";
}