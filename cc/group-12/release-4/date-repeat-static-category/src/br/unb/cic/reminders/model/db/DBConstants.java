package br.unb.cic.reminders.model.db;

public class DBConstants {
	public static String DROP_TABLE_STATEMENTS[] = { "DROP TABLE IF EXISTS REMINDER"
			, "DROP TABLE IF EXISTS CATEGORY"
	};
	public static String CREATE_TABLE_STATEMENTS[] = { "CREATE TABLE REMINDER ( "
			+ "PK INTEGER PRIMARY KEY AUTOINCREMENT, " + "TEXT VARCHAR(50) NOT NULL," + "DETAILS VARCHAR(50) NULL,"
			+ "FK_CATEGORY INTEGER NOT NULL REFERENCES CATEGORY ON DELETE CASCADE,"
			+ "HOUR CHAR(5) NULL,"
			+ "MONDAY INTEGER NOT NULL," + "TUESDAY INTEGER NOT NULL," + "WEDNESDAY INTEGER NOT NULL,"
			+ "THURSDAY INTEGER NOT NULL," + "FRIDAY INTEGER NOT NULL," + "SATURDAY INTEGER NOT NULL,"
			+ "SUNDAY INTEGER NOT NULL,"
			+ "DONE INTEGER NOT NULL);"
			, "CREATE TABLE CATEGORY(" + "PK INTEGER PRIMARY KEY AUTOINCREMENT, " + "NAME VARCHAR(50) NOT NULL, "
					+ "LOCKED INT NOT NULL);"
	};
	public static final String SELECT_REMINDERS = "SELECT * FROM REMINDER";
	public static String REMINDER_TABLE = "REMINDER";
	public static String REMINDER_PK_COLUMN = "PK";
	public static String REMINDER_TEXT_COLUMN = "TEXT";
	public static String REMINDER_DETAILS_COLUMN = "DETAILS";
	public static String REMINDER_HOUR_COLUMN = "HOUR";
	public static String REMINDER_DONE_COLUMN = "DONE";
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
	public static String REMINDER_MONDAY_COLUMN = "MONDAY";
	public static String REMINDER_TUESDAY_COLUMN = "TUESDAY";
	public static String REMINDER_WEDNESDAY_COLUMN = "WEDNESDAY";
	public static String REMINDER_THURSDAY_COLUMN = "THURSDAY";
	public static String REMINDER_FRIDAY_COLUMN = "FRIDAY";
	public static String REMINDER_SATURDAY_COLUMN = "SATURDAY";
	public static String REMINDER_SUNDAY_COLUMN = "SUNDAY";
}