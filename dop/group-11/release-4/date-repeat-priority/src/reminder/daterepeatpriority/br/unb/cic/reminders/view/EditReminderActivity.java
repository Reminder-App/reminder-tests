package reminder.daterepeatpriority.br.unb.cic.reminders.view;

import java.util.List;
import android.content.Intent;
import
reminder.daterepeatpriority.br.unb.cic.framework.persistence.DBException;
import reminder.daterepeatpriority.br.unb.cic.reminders.controller.Controller;
import reminder.daterepeatpriority.br.unb.cic.reminders.model.Reminder;
import reminder.daterepeatpriority.br.unb.cic.reminders.model.Priority;
/*** added by dManageReminder* modified by dDateRepeat* modified by dPriority
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
	@Override
	protected void persist(Reminder reminder) {
		try {
			Controller.instance(getApplicationContext()).updateReminder(reminder);
		}
		catch(DBException e) {
			e.printStackTrace();
		}
	}
	/*** added by dDateRepeat* modified by dPriority
	 */
	private void initializeValues(Intent intent) throws Exception {
		initializeValues_original0(intent);
		String priority = intent.getStringExtra("priority");
		spinnerPriority.setSelection(Priority.fromCode(Integer.parseInt(priority,
					10)).getCode());
	}
	/*** added by dDateRepeat* modified by dPriority
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
}