package reminder.daterangeprioritygooglecalendar.br.unb.cic.reminders.view;

import java.util.List;
import android.content.Intent;
import
reminder.daterangeprioritygooglecalendar.br.unb.cic.framework.persistence.DBException;
import
reminder.daterangeprioritygooglecalendar.br.unb.cic.reminders.controller.Controller;
import
reminder.daterangeprioritygooglecalendar.br.unb.cic.reminders.model.Reminder;
import
reminder.daterangeprioritygooglecalendar.br.unb.cic.reminders.model.Priority;
/*** added by dManageReminder* modified by dDateRange* modified by dPriority
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
	/*** added by dDateRange* modified by dPriority
	 */
	private void initializeValues(Intent intent) throws Exception {
		initializeValues_original0(intent);
		String priority = intent.getStringExtra("priority");
		spinnerPriority.setSelection(Priority.fromCode(Integer.parseInt(priority,
					10)).getCode());
	}
	/*** added by dDateRange* modified by dPriority
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
}