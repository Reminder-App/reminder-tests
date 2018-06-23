package reminder.daterangestaticcategorysearch.br.unb.cic.reminders.view;

import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import
reminder.daterangestaticcategorysearch.br.unb.cic.framework.persistence.DBException;
import
reminder.daterangestaticcategorysearch.br.unb.cic.reminders.controller.Controller;
import
reminder.daterangestaticcategorysearch.br.unb.cic.reminders.model.Reminder;
import
reminder.daterangestaticcategorysearch.br.unb.cic.reminders.model.Category;
/*** added by dManageReminder* modified by dDateRange* modified by
dStaticCategory
 */
public class ExternalAddReminderActivity extends ReminderActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		reminder = new Reminder();
		try {
			setReminderFromIntent();
		}
		catch(Exception e) {
			Intent intent2 = new Intent(getApplicationContext(),
				AddReminderActivity.class);
			startActivity(intent2);
			finish();
		}
		super.onCreate(savedInstanceState);
	}
	private void setReminderFromIntent() throws Exception {
		Intent intent = getIntent();
		String action = intent.getAction();
		String type = intent.getType();
		if(action.equals("br.com.positivo.reminders.ADD_REMINDER") &&
			"text/plain".equals(type)) {
			String text = intent.getStringExtra("text");
			String details = intent.getStringExtra("details");
			reminder.setText(text);
			reminder.setDetails(details);
			reminderFromIntent(intent);
		}
		else reminder = null;
	}
	@Override
	protected void initializeValues() {
		if(! reminder.isValid()) return;
		edtReminder.setText(reminder.getText());
		edtDetails.setText(reminder.getDetails());
		try {
			initialize();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void persist(Reminder reminder) {
		try {
			Controller.instance(getApplicationContext()).addReminder(reminder);
		}
		catch(DBException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*** added by dDateRange* modified by dStaticCategory
	 */
	private void reminderFromIntent(Intent intent) throws Exception {
		reminderFromIntent_original0(intent);
		setNewCategory(intent);
		reminder.setCategory(newCategory);
	}
	/*** added by dDateRange* modified by dStaticCategory
	 */
	private void initialize() throws Exception {
		initialize_original2();
		spinnerCategory.setSelection(categoryToIndex(reminder.getCategory()));
	}
	/*** added by dStaticCategory
	 */
	private Category newCategory = null;
	/*** added by dStaticCategory
	 */
	private void setNewCategory(Intent intent) throws Exception {
		String categoryName = intent.getStringExtra("category_name");
		List<Category> categories =
		Controller.instance(getApplicationContext()).listCategories();
		for(Category c : categories) {
			if(c.getName().equals(categoryName)) {
				newCategory = c;
				break;
			}
		}
	}
	/*** added by dDateRange* modified by dStaticCategory
	 */
	private void reminderFromIntent_original0(Intent intent) throws Exception {
		String dateStart = intent.getStringExtra("dateStart");
		String hourStart = intent.getStringExtra("hourStart");
		String dateFinal = intent.getStringExtra("dateFinal");
		String hourFinal = intent.getStringExtra("hourFinal");
		reminder.setDateStart(dateStart);
		reminder.setHourStart(hourStart);
		reminder.setDateFinal(dateFinal);
		reminder.setHourFinal(hourFinal);
	}
	/*** added by dDateRange* modified by dStaticCategory
	 */
	private void initialize_original2() throws Exception {
		updateSpinnerDateHour(spinnerDateStart, reminder.getDateStart());
		updateDateFromString(reminder.getDateStart(), false);
		updateSpinnerDateHour(spinnerTimeStart, reminder.getHourStart());
		updateTimeFromString(reminder.getHourStart(), false);
		updateSpinnerDateHour(spinnerDateStart, reminder.getDateFinal());
		updateDateFromString(reminder.getDateFinal(), true);
		updateSpinnerDateHour(spinnerTimeStart, reminder.getHourFinal());
		updateTimeFromString(reminder.getHourFinal(), true);
	}
	/*** added by dStaticCategory
	 */
	@Override
	protected List<Category> getCategories() throws Exception {
		List<Category> categories = super.getCategories();
		return categories;
	}
	/*** added by dStaticCategory
	 */
	private Category findCategory(Category category) throws Exception {
		List<Category> categories =
		Controller.instance(getApplicationContext()).listCategories();
		for(Category c : categories) {
			if(c.getName().equals(category.getName())) return c;
		}
		return null;
	}
	/*** added by dStaticCategory
	 */
	private int categoryToIndex(Category category) throws Exception {
		List<Category> categories =
		Controller.instance(getApplicationContext()).listCategories();
		int i = 0;
		for(Category c : categories) {
			if(c.getName().equals(category.getName())) {
				return i;
			}
			i ++;
		}
		return 0;
	}
}