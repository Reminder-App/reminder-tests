package reminder.daterepeatmanagecategorysearch.br.unb.cic.reminders.view;

import java.util.List;
import
reminder.daterepeatmanagecategorysearch.br.unb.cic.framework.persistence.DBException;
import
reminder.daterepeatmanagecategorysearch.br.unb.cic.reminders.controller.Controller;
import
reminder.daterepeatmanagecategorysearch.br.unb.cic.reminders.model.Reminder;
import
reminder.daterepeatmanagecategorysearch.br.unb.cic.reminders.model.Category;
/*** added by dManageReminder* modified by dStaticCategory* modified by
dManageCategory
 */
public class AddReminderActivity extends ReminderActivity {
	@Override
	protected void initializeValues() {
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
			Controller.instance(getApplicationContext()).addReminder(reminder);
		}
		catch(DBException e) {
			e.printStackTrace();
		}
	}
}