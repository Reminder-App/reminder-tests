package reminder.daterangestaticcategorysearch.br.unb.cic.reminders.view;

import java.util.List;
import android.content.Intent;
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
public class EditReminderActivity extends ReminderActivity {
	@Override
	protected void initializeValues() {
		Intent intent = getIntent();
		long reminderId = intent.getLongExtra("id", 0);
		String text = intent.getStringExtra("text");
		String details = intent.getStringExtra("details");
		reminder.setId(reminderId);
		edtReminder.setText(text);
		edtDetails.setText(details);
		try {
			initializeValues(intent);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*** added by dDateRange* modified by dStaticCategory
	 */
	private void initializeValues(Intent intent) throws Exception {
		initializeValues_original0(intent);
		String categoryName = intent.getStringExtra("category_name");
		String categoryId = intent.getStringExtra("category_id");
		Category category = new Category();
		category.setId(Long.parseLong(categoryId));
		category.setName(categoryName);
		spinnerCategory.setSelection(categoryToIndex(category));
	}
	/*** added by dDateRange* modified by dStaticCategory
	 */
	private void initializeValues_original0(Intent intent) throws Exception {
		String dateStart = intent.getStringExtra("dateStart");
		String hourStart = intent.getStringExtra("hourStart");
		String dateFinal = intent.getStringExtra("dateFinal");
		String hourFinal = intent.getStringExtra("hourFinal");
		updateSpinnerDateHour(spinnerDateStart, dateStart);
		updateDateFromString(dateStart, false);
		updateSpinnerDateHour(spinnerTimeStart, hourStart);
		updateTimeFromString(hourStart, false);
		updateSpinnerDateHour(spinnerDateFinal, dateFinal);
		updateDateFromString(dateFinal, true);
		updateSpinnerDateHour(spinnerTimeFinal, hourFinal);
		updateTimeFromString(hourFinal, false);
	}
	/*** added by dStaticCategory
	 */
	@Override
	protected void persist(Reminder reminder) {
		try {
			Category category = findCategory(reminder.getCategory());
			reminder.setCategory(category);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try {
			Controller.instance(getApplicationContext()).updateReminder(reminder);
		}
		catch(DBException e) {
			e.printStackTrace();
		}
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
}