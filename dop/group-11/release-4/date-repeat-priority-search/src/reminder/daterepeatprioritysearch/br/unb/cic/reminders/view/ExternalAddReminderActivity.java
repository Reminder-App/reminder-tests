package reminder.daterepeatprioritysearch.br.unb.cic.reminders.view;

import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import
reminder.daterepeatprioritysearch.br.unb.cic.framework.persistence.DBException;
import
reminder.daterepeatprioritysearch.br.unb.cic.reminders.controller.Controller;
import reminder.daterepeatprioritysearch.br.unb.cic.reminders.model.Reminder;
import reminder.daterepeatprioritysearch.br.unb.cic.reminders.model.Priority;
/*** added by dManageReminder* modified by dDateRepeat* modified by dPriority
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
	/*** added by dDateRepeat* modified by dPriority
	 */
	private void reminderFromIntent(Intent intent) throws Exception {
		reminderFromIntent_original0(intent);
		String priority = intent.getStringExtra("priority");
		reminder.setPriority(Priority.fromCode(Integer.parseInt(priority)));
	}
	/*** added by dDateRepeat* modified by dPriority
	 */
	private void initialize() throws Exception {
		initialize_original2();
		spinnerPriority.setSelection(reminder.getPriority());
	}
	/*** added by dDateRepeat* modified by dPriority
	 */
	private void reminderFromIntent_original0(Intent intent) throws Exception {
		String hour = intent.getStringExtra("hour");
		int monday = intent.getIntExtra("monday", 0);
		int tuesday = intent.getIntExtra("tuesday", 0);
		int wednesday = intent.getIntExtra("wednesday", 0);
		int thursday = intent.getIntExtra("thursday", 0);
		int friday = intent.getIntExtra("friday", 0);
		int saturday = intent.getIntExtra("saturday", 0);
		int sunday = intent.getIntExtra("sunday", 0);
		reminder.setMonday(monday);
		reminder.setTuesday(tuesday);
		reminder.setWednesday(wednesday);
		reminder.setThursday(thursday);
		reminder.setFriday(friday);
		reminder.setSaturday(saturday);
		reminder.setSunday(sunday);
		reminder.setHour(hour);
	}
	/*** added by dDateRepeat* modified by dPriority
	 */
	private void initialize_original2() throws Exception {
		updateSpinnerDateHour(spinnerTime, reminder.getHour());
		updateTimeFromString(reminder.getHour());
	}
}