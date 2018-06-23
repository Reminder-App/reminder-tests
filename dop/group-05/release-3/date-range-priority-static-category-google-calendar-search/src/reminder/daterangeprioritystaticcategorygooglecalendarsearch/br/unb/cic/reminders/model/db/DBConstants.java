package
reminder.daterangeprioritystaticcategorygooglecalendarsearch.br.unb.cic.reminders.model.db;

/*** added by dManageReminder* modified by dDateRange* modified by dPriority*
modified by dStaticCategory
 */
public class DBConstants {
	public static final String SELECT_REMINDERS = "SELECT * FROM REMINDER";
	public static String REMINDER_TABLE = "REMINDER";
	public static String REMINDER_PK_COLUMN = "PK";
	public static String REMINDER_TEXT_COLUMN = "TEXT";
	public static String REMINDER_DETAILS_COLUMN = "DETAILS";
	public static String REMINDER_DONE_COLUMN = "DONE";
	/*** added by dDateRange* modified by dPriority* modified by dStaticCategory
	 */
	private static String tableStatement() {
		return "FK_CATEGORY INTEGER NOT NULL REFERENCES CATEGORY ON DELETE CASCADE,"
		+ tableStatement_original2();
	}
	/*** added by dDateRange
	 */
	public static String REMINDER_INITIAL_DATE_COLUMN = "INITIAL_DATE";
	/*** added by dDateRange
	 */
	public static String REMINDER_INITIAL_HOUR_COLUMN = "INITIAL_HOUR";
	/*** added by dDateRange
	 */
	public static String REMINDER_FINAL_DATE_COLUMN = "FINAL_DATE";
	/*** added by dDateRange
	 */
	public static String REMINDER_FINAL_HOUR_COLUMN = "FINAL_HOUR";
	/*** added by dDateRange* modified by dPriority
	 */
	private static String tableStatement_original0() {
		return "INITIAL_DATE CHAR(10) NOT NULL," + "INITIAL_HOUR CHAR(5) NULL," +
		"FINAL_DATE CHAR(10) NOT NULL," + "FINAL_HOUR CHAR(5) NULL,";
	}
	/*** added by dPriority
	 */
	public static String REMINDER_PRIORITY_COLUMN = "PRIORITY";
	/*** added by dStaticCategory
	 */
	public static String DROP_TABLE_STATEMENTS [] = {
		"DROP TABLE IF EXISTS REMINDER", "DROP TABLE IF EXISTS CATEGORY"
	};
	/*** added by dStaticCategory
	 */
	public static String CREATE_TABLE_STATEMENTS [] = {
		"CREATE TABLE CATEGORY(" + "PK INTEGER PRIMARY KEY AUTOINCREMENT, " +
		"NAME VARCHAR(50) NOT NULL, " + "LOCKED INT NOT NULL);",
		"CREATE TABLE REMINDER ( " + "PK INTEGER PRIMARY KEY AUTOINCREMENT, " +
		"TEXT VARCHAR(50) NOT NULL," + "DETAILS VARCHAR(50) NULL," + tableStatement()
		+ "DONE INTEGER NOT NULL);"
	};
	/*** added by dDateRange* modified by dPriority* modified by dStaticCategory
	 */
	private static String tableStatement_original2() {
		return tableStatement_original0() + "PRIORITY INTEGER NOT NULL,";
	}
	/*** added by dStaticCategory
	 */
	public static final String SELECT_CATEGORIES =
	"SELECT PK, NAME FROM CATEGORY";
	/*** added by dStaticCategory
	 */
	public static final String SELECT_CATEGORY_BY_NAME =
	"SELECT PK, NAME FROM CATEGORY WHERE NAME = ?";
	/*** added by dStaticCategory
	 */
	public static final String SELECT_CATEGORY_BY_ID =
	"SELECT PK, NAME FROM CATEGORY WHERE PK = ?";
	/*** added by dStaticCategory
	 */
	public static final String SELECT_REMINDERS_BY_CATEGORY =
	"SELECT * FROM REMINDER WHERE FK_CATEGORY = ?";
	/*** added by dStaticCategory
	 */
	public static String DELETE_CATEGORIES = "DELETE FROM CATEGORY WHERE PK = ?";
	/*** added by dStaticCategory
	 */
	public static final String PREDEFINED_CATEGORIES [] = {
		"INSERT INTO CATEGORY VALUES (NULL,'College',1);",
		"INSERT INTO CATEGORY VALUES (NULL,'Job',1);",
		"INSERT INTO CATEGORY VALUES (NULL,'Personal',1);"
	};
	/*** added by dStaticCategory
	 */
	public static String CATEGORY_TABLE = "CATEGORY";
	/*** added by dStaticCategory
	 */
	public static String CATEGORY_PK_COLUMN = "PK";
	/*** added by dStaticCategory
	 */
	public static String CATEGORY_NAME_COLUMN = "NAME";
	/*** added by dStaticCategory
	 */
	public static String CATEGORY_LOCKED_COLUMN = "LOCKED";
	/*** added by dStaticCategory
	 */
	public static String REMINDER_FK_CATEGORY_COLUMN = "FK_CATEGORY";
}