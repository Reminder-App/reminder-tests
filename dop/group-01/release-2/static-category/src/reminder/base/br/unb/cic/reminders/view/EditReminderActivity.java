package reminder.base.br.unb.cic.reminders.view;

import java.util.List;
import android.content.Intent;
import reminder.base.br.unb.cic.framework.persistence.DBException;
import reminder.base.br.unb.cic.reminders.controller.Controller;
import reminder.base.br.unb.cic.reminders.model.Reminder;
/*** added by dManageReminder
 */
public class EditReminderActivity extends ReminderActivity {
	@Override
	protected void initializeValues() {
		Intent intent = getIntent();
		long reminderId = intent.getLongExtra("id", 0);
		String text = intent.getStringExtra("text");
		String details = intent.getStringExtra("details");
		String date = intent.getStringExtra("date");
		String hour = intent.getStringExtra("hour");
		reminder.setId(reminderId);
		edtReminder.setText(text);
		edtDetails.setText(details);
		updateSpinnerDateHour(spinnerDate, date);
		updateDateFromString(date);
		updateSpinnerDateHour(spinnerTime, hour);
		updateTimeFromString(hour);
	}
	@Override
	protected void persist(Reminder reminder) {
		try {
			Controller.instance(getApplicationContext()).updateReminder(reminder);
		}
		catch(DBException e) {
			e.printStackTrace();
		}
	}
}