package br.unb.cic.reminders.view;

import android.content.Intent;
import br.unb.cic.framework.persistence.DBException;
import br.unb.cic.reminders.controller.Controller;
import br.unb.cic.reminders.model.Reminder;
import br.unb.cic.reminders.model.Priority;

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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initializeValues(Intent intent) throws Exception {

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

		String priority = intent.getStringExtra("priority");
		spinnerPriority.setSelection(Priority.fromCode(Integer.parseInt(priority, 10)).getCode());
	}

	@Override
	protected void persist(Reminder reminder) {

		try {
			Controller.instance(getApplicationContext()).updateReminder(reminder);
		} catch (DBException e) {
			e.printStackTrace();
		}
	}

}
