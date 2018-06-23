package reminder.daterangemanagecategory.br.unb.cic.reminders.model.cp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import
reminder.daterangemanagecategory.br.unb.cic.framework.persistence.DBException;
import reminder.daterangemanagecategory.br.unb.cic.reminders.model.Reminder;
import
reminder.daterangemanagecategory.br.unb.cic.reminders.model.db.DBConstants;
import
reminder.daterangemanagecategory.br.unb.cic.reminders.model.db.DefaultDBFactory;
import
reminder.daterangemanagecategory.br.unb.cic.reminders.model.db.ReminderDAO;
import reminder.daterangemanagecategory.br.unb.cic.reminders.model.Category;
import
reminder.daterangemanagecategory.br.unb.cic.reminders.model.db.CategoryDAO;
/*** added by dManageReminder* modified by dDateRange* modified by
dStaticCategory* modified by dManageCategory
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
	/*** modified by dStaticCategory
	 */
	@Override
	public boolean onCreate() {
		cdao = DefaultDBFactory.factory(getContext()).createCategoryDAO();
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
	/*** added by dDateRange* modified by dStaticCategory
	 */
	private Reminder createReminderInsert(ContentValues values) throws DBException
	{
		Reminder reminder = createReminderInsert_original0(values);
		Category category = createCategoryInsert(values);
		reminder.setCategory(category);
		return reminder;
	}
	/*** added by dStaticCategory
	 */
	public static final String category() {
		return DBConstants.CATEGORY_NAME_COLUMN;
	}
	/*** added by dStaticCategory
	 */
	private CategoryDAO cdao;
	/*** added by dStaticCategory* modified by dManageCategory
	 */
	private Category createCategoryInsert(ContentValues values) throws DBException
	{
		Category category = createCategoryInsert_original2(values);
		if(category == null) {
			Category auxCategory = new Category();
			auxCategory.setName(values.getAsString(category()));
			cdao.saveCategory(auxCategory);
			category = cdao.findCategory(values.getAsString(category()));
		}
		return category;
	}
	/*** added by dDateRange* modified by dStaticCategory
	 */
	private Reminder createReminderInsert_original0(ContentValues values) throws
	DBException {
		Reminder reminder = new Reminder();
		reminder.setDateStart(values.getAsString(dateStart()));
		reminder.setHourStart(values.getAsString(hourStart()));
		reminder.setDateFinal(values.getAsString(dateFinal()));
		reminder.setHourFinal(values.getAsString(hourFinal()));
		return reminder;
	}
	/*** added by dStaticCategory* modified by dManageCategory
	 */
	private Category createCategoryInsert_original2(ContentValues values) throws
	DBException {
		Category category = cdao.findCategory(values.getAsString(category()));
		return category;
	}
}