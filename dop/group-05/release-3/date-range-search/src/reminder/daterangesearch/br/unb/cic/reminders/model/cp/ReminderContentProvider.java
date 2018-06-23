package reminder.daterangesearch.br.unb.cic.reminders.model.cp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import reminder.daterangesearch.br.unb.cic.framework.persistence.DBException;
import reminder.daterangesearch.br.unb.cic.reminders.model.Reminder;
import reminder.daterangesearch.br.unb.cic.reminders.model.db.DBConstants;
import reminder.daterangesearch.br.unb.cic.reminders.model.db.DefaultDBFactory;
import reminder.daterangesearch.br.unb.cic.reminders.model.db.ReminderDAO;
/*** added by dManageReminder* modified by dDateRange
 */
public class ReminderContentProvider extends ContentProvider {
	private static final int REMINDERS = 10;
	private static final String SECURITY_EXCEPTION =
	"You are not allowed to call this method";
	private static final String AUTHORITY =
	"br.com.positivo.reminders.contentprovider";
	private static final String BASE_PATH = "reminders";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/"
		+ BASE_PATH);
	public static final String text() {
		return DBConstants.REMINDER_TEXT_COLUMN;
	}
	private ReminderDAO rdao;
	private static final UriMatcher sURIMatcher = new
	UriMatcher(UriMatcher.NO_MATCH);
	static {
		sURIMatcher.addURI(AUTHORITY, BASE_PATH, REMINDERS);
	}
	@Override
	public boolean onCreate() {
		rdao = DefaultDBFactory.factory(getContext()).createReminderDAO();
		return false;
	}
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		try {
			Reminder reminder = createReminderInsert(values);
			reminder.setText(values.getAsString(text()));
			Long id = rdao.saveReminder(reminder);
			getContext().getContentResolver().notifyChange(uri, null);
			return Uri.parse(BASE_PATH + "/" + id);
		}
		catch(DBException e) {
			throw new RuntimeException(e.getLocalizedMessage());
		}
	}
	@Override
	public Cursor query(Uri arg0, String [] arg1, String arg2, String [] arg3,
		String arg4) {
		return null;
	}
	@Override
	public int delete(Uri arg0, String arg1, String [] arg2) {
		throw new SecurityException(SECURITY_EXCEPTION);
	}
	@Override
	public String getType(Uri arg0) {
		return null;
	}
	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String [] arg3) {
		throw new SecurityException(SECURITY_EXCEPTION);
	}
	/*** added by dDateRange
	 */
	public static final String dateStart() {
		return DBConstants.REMINDER_INITIAL_DATE_COLUMN;
	}
	/*** added by dDateRange
	 */
	public static final String hourStart() {
		return DBConstants.REMINDER_INITIAL_HOUR_COLUMN;
	}
	/*** added by dDateRange
	 */
	public static final String dateFinal() {
		return DBConstants.REMINDER_FINAL_DATE_COLUMN;
	}
	/*** added by dDateRange
	 */
	public static final String hourFinal() {
		return DBConstants.REMINDER_FINAL_HOUR_COLUMN;
	}
	/*** added by dDateRange
	 */
	private Reminder createReminderInsert(ContentValues values) throws DBException
	{
		Reminder reminder = new Reminder();
		reminder.setDateStart(values.getAsString(dateStart()));
		reminder.setHourStart(values.getAsString(hourStart()));
		reminder.setDateFinal(values.getAsString(dateFinal()));
		reminder.setHourFinal(values.getAsString(hourFinal()));
		return reminder;
	}
}