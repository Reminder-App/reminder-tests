package reminder.base.br.unb.cic.reminders.view;

import java.util.List;
import reminder.base.br.unb.cic.framework.persistence.DBException;
import reminder.base.br.unb.cic.reminders.controller.Controller;
import reminder.base.br.unb.cic.reminders.model.Reminder;
/*** added by dService
 */
public class AddReminderActivity extends ReminderActivity {
	@Override
	protected void initializeValues() {
	}
	@Override
	protected void persist(Reminder reminder) {
		try {
			Controller.instance(getApplicationContext()).addReminder(reminder);
		}
		catch(DBException e) {
			e.printStackTrace();
		}
	}
}