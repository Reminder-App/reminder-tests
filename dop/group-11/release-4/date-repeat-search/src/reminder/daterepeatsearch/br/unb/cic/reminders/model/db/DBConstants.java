package reminder.daterepeatsearch.br.unb.cic.reminders.model.db;

/*** added by dManageReminder* modified by dDateRepeat
 */
public class DBConstants {
	public static String DROP_TABLE_STATEMENTS [] = {
		"DROP TABLE IF EXISTS REMINDER"
	};
	public static String CREATE_TABLE_STATEMENTS [] = {
		"CREATE TABLE REMINDER ( " + "PK INTEGER PRIMARY KEY AUTOINCREMENT, " +
		"TEXT VARCHAR(50) NOT NULL," + "DETAILS VARCHAR(50) NULL," + tableStatement()
		+ "DONE INTEGER NOT NULL);"
	};
	public static final String SELECT_REMINDERS = "SELECT * FROM REMINDER";
	public static String REMINDER_TABLE = "REMINDER";
	public static String REMINDER_PK_COLUMN = "PK";
	public static String REMINDER_TEXT_COLUMN = "TEXT";
	public static String REMINDER_DETAILS_COLUMN = "DETAILS";
	public static String REMINDER_HOUR_COLUMN = "HOUR";
	public static String REMINDER_DONE_COLUMN = "DONE";
	/*** added by dDateRepeat
	 */
	private static String tableStatement() {
		return "MONDAY INTEGER NOT NULL," + "TUESDAY INTEGER NOT NULL," +
		"WEDNESDAY INTEGER NOT NULL," + "THURSDAY INTEGER NOT NULL," +
		"FRIDAY INTEGER NOT NULL," + "SATURDAY INTEGER NOT NULL," +
		"SUNDAY INTEGER NOT NULL," + "HOUR CHAR(5) NULL,";
	}
	/*** added by dDateRepeat
	 */
	public static String REMINDER_MONDAY_COLUMN = "MONDAY";
	/*** added by dDateRepeat
	 */
	public static String REMINDER_TUESDAY_COLUMN = "TUESDAY";
	/*** added by dDateRepeat
	 */
	public static String REMINDER_WEDNESDAY_COLUMN = "WEDNESDAY";
	/*** added by dDateRepeat
	 */
	public static String REMINDER_THURSDAY_COLUMN = "THURSDAY";
	/*** added by dDateRepeat
	 */
	public static String REMINDER_FRIDAY_COLUMN = "FRIDAY";
	/*** added by dDateRepeat
	 */
	public static String REMINDER_SATURDAY_COLUMN = "SATURDAY";
	/*** added by dDateRepeat
	 */
	public static String REMINDER_SUNDAY_COLUMN = "SUNDAY";
}