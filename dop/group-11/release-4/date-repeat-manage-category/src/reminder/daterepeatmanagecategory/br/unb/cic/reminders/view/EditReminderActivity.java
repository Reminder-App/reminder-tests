package reminder.daterepeatmanagecategory.br.unb.cic.reminders.view;

import java.util.List;
import android.content.Intent;
import
reminder.daterepeatmanagecategory.br.unb.cic.framework.persistence.DBException;
import
reminder.daterepeatmanagecategory.br.unb.cic.reminders.controller.Controller;
import reminder.daterepeatmanagecategory.br.unb.cic.reminders.model.Reminder;
import reminder.daterepeatmanagecategory.br.unb.cic.reminders.model.Category;
/*** added by dManageReminder* modified by dDateRepeat* modified by
dStaticCategory* modified by dManageCategory
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
	/*** added by dDateRepeat* modified by dStaticCategory
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
	/*** added by dDateRepeat* modified by dStaticCategory
	 */
	private void initializeValues_original0(Intent intent) throws Exception {
		boolean monday = intent.getBooleanExtra("monday", false);
		boolean tuesday = intent.getBooleanExtra("tuesday", false);
		boolean wednesday = intent.getBooleanExtra("wednesday", false);
		boolean thursday = intent.getBooleanExtra("thursday", false);
		boolean friday = intent.getBooleanExtra("friday", false);
		boolean saturday = intent.getBooleanExtra("saturday", false);
		boolean sunday = intent.getBooleanExtra("sunday", false);
		String hour = intent.getStringExtra("hour");
		reminder.setMonday(monday);
		reminder.setTuesday(tuesday);
		reminder.setWednesday(wednesday);
		reminder.setThursday(thursday);
		reminder.setFriday(friday);
		reminder.setSaturday(saturday);
		reminder.setSunday(sunday);
		cbMonday.setChecked(monday);
		cbTuesday.setChecked(tuesday);
		cbWednesday.setChecked(wednesday);
		cbThursday.setChecked(thursday);
		cbFriday.setChecked(friday);
		cbSaturday.setChecked(saturday);
		cbSunday.setChecked(sunday);
		updateSpinnerDateHour(spinnerTime, hour);
		updateTimeFromString(hour);
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
	/*** added by dManageCategory
	 */
	@Override
	protected void persist(Reminder reminder) {
		try {
			Category category = findCategory(reminder.getCategory());
			if(category != null) {
				reminder.setCategory(category);
			}
			else {
				Controller.instance(getApplicationContext()).addCategory(reminder.getCategory());
				reminder.setCategory(findCategory(reminder.getCategory()));
			}
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
}