package reminder.daterangepriority.br.unb.cic.reminders.view;

import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import reminder.daterangepriority.br.unb.cic.framework.persistence.DBException;
import reminder.daterangepriority.br.unb.cic.reminders.controller.Controller;
import reminder.daterangepriority.br.unb.cic.reminders.model.Reminder;
import reminder.daterangepriority.br.unb.cic.reminders.model.Priority;
/*** added by dManageReminder* modified by dDateRange* modified by dPriority
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
	/*** added by dDateRange* modified by dPriority
	 */
	private void reminderFromIntent(Intent intent) throws Exception {
		reminderFromIntent_original0(intent);
		String priority = intent.getStringExtra("priority");
		reminder.setPriority(Priority.fromCode(Integer.parseInt(priority)));
	}
	/*** added by dDateRange* modified by dPriority
	 */
	private void initialize() throws Exception {
		initialize_original2();
		spinnerPriority.setSelection(reminder.getPriority());
	}
	/*** added by dDateRange* modified by dPriority
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
	/*** added by dDateRange* modified by dPriority
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
}