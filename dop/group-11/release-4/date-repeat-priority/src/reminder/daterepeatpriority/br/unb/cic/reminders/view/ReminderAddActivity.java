package reminder.daterepeatpriority.br.unb.cic.reminders.view;

import java.util.Arrays;
import java.util.List;
import reminder.daterepeatpriority.util.Mask;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import reminder.daterepeatpriority.br.unb.cic.reminders.controller.Controller;
import
reminder.daterepeatpriority.br.unb.cic.reminders.model.InvalidDateException;
import
reminder.daterepeatpriority.br.unb.cic.reminders.model.InvalidFormatException;
import
reminder.daterepeatpriority.br.unb.cic.reminders.model.InvalidTextException;
import reminder.daterepeatpriority.br.unb.cic.reminders.model.Reminder;
import br.unb.cic.reminders2.R;
import
reminder.daterepeatpriority.br.unb.cic.reminders.model.InvalidDaysException;
import android.widget.CheckBox;
import reminder.daterepeatpriority.br.unb.cic.reminders.model.Priority;
/*** added by dManageReminder* modified by dDateRepeat* modified by dPriority
 */
public class ReminderAddActivity extends Activity {
	private EditText edtReminder, edtDetails, edtHour;
	private Button btnSave, btnCancel;
	private boolean editingReminder;
	private Long previewReminderId;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reminder_add);
		Reminder existingReminder = getReminderFromIntent();
		if(existingReminder == null) {
			editingReminder = true;
			Reminder editReminder = getExistingReminder();
			initialize(editReminder);
		}
		else {
			editingReminder = false;
			initialize(existingReminder);
		}
		configureActionListener();
	}
	/*** modified by dPriority
	 */
	private void configureActionListener() {
		configureActionListener_original0();
		addListenerToSpinnerPriority();
	}
	private void addListenerToBtnSave() {
		btnSave.setOnClickListener(new Button.OnClickListener() {
				public void onClick(View v) {
					try {
						Reminder reminder = createReminder();
						if(editingReminder) {
							reminder.setId(previewReminderId);
							Controller.instance(getApplicationContext()).updateReminder(reminder);
						}
						else {
							Controller.instance(getApplicationContext()).addReminder(reminder);
						}
						finish();
					}
					catch(Exception e) {
						Log.e("ReminderAddActivity", e.getMessage());
						e.printStackTrace();
					}
				}
			});
	}
	private void addListenerToBtnCancel() {
		btnCancel.setOnClickListener(new Button.OnClickListener() {
				public void onClick(View v) {
					finish();
				}
			});
	}
	private Reminder createReminder() {
		Reminder reminder = new Reminder();
		try {
			reminder = createReminderAux();
			reminder.setText(edtReminder.getText().toString());
			reminder.setDetails(edtDetails.getText().toString());
		}
		catch(InvalidTextException e) {
			Toast.makeText(getApplicationContext(), "Invalid text.",
				Toast.LENGTH_SHORT).show();
		}
		catch(InvalidDateException e) {
			Toast.makeText(getApplicationContext(), "Invalid date.",
				Toast.LENGTH_SHORT).show();
		}
		catch(InvalidHourException e) {
			Toast.makeText(getApplicationContext(), "Invalid time.",
				Toast.LENGTH_SHORT).show();
		}
		return reminder;
	}
	private Reminder getExistingReminder() {
		Reminder reminder = null;
		Intent intent = getIntent();
		String action = intent.getAction();
		String type = intent.getType();
		if(Intent.ACTION_SEND.equals(action) && "text/plain".equals(type)) {
			previewReminderId = intent.getLongExtra("id", 0);
			String text = intent.getStringExtra("text");
			reminder = createReminderExisting(intent);
			reminder.setText(text);
			reminder.setId(previewReminderId);
		}
		return reminder;
	}
	private Reminder getReminderFromIntent() {
		Intent intent = getIntent();
		String action = intent.getAction();
		String type = intent.getType();
		if("br.com.positivo.reminders.ADD_REMINDER".equals(action) &&
			"text/plain".equals(type)) {
			try {
				String text = intent.getStringExtra("text");
				String details = intent.getStringExtra("details");
				Reminder reminder = createReminderIntent(intent);
				reminder.setText(text);
				reminder.setDetails(details);
				return reminder;
			}
			catch(InvalidFormatException e) {
			}
		}
		return null;
	}
	/*** modified by dPriority
	 */
	private void initialize(Reminder reminder) {
		spinnerPriority = getSpinnerPriority();
		initialize_original8(reminder);
	}
	/*** added by dDateRepeat
	 */
	private CheckBox cbMonday, cbTuesday, cbWednesday, cbThursday, cbFriday,
	cbSaturday, cbSunday;
	/*** added by dDateRepeat* modified by dPriority
	 */
	private Reminder createReminderAux() {
		Reminder reminder = createReminderAux_original2();
		reminder.setPriority(selectedPriority);
		return reminder;
	}
	/*** added by dDateRepeat* modified by dPriority
	 */
	private Reminder createReminderExisting(Intent intent) {
		Reminder reminder = createReminderExisting_original4(intent);
		String priority = intent.getStringExtra("priority");
		reminder.setPriority(Priority.fromCode(Integer.parseInt(priority, 10)));
		return reminder;
	}
	/*** added by dDateRepeat* modified by dPriority
	 */
	private Reminder createReminderIntent(Intent intent) {
		Reminder reminder = createReminderIntent_original6(intent);
		String priority = intent.getStringExtra("priority");
		return reminder;
	}
	/*** added by dDateRepeat* modified by dPriority
	 */
	private void updateFieldsFromReminder(Reminder reminder) throws Exception {
		updateFieldsFromReminder_original10(reminder);
		spinnerPriority.setSelection(reminder.getPriority());
	}
	/*** added by dPriority
	 */
	private Priority selectedPriority;
	/*** added by dPriority
	 */
	private Spinner spinnerPriority;
	/*** modified by dPriority
	 */
	private void configureActionListener_original0() {
		addListenerToBtnSave();
		addListenerToBtnCancel();
	}
	/*** added by dPriority
	 */
	private void addListenerToSpinnerPriority() {
		spinnerPriority.setOnItemSelectedListener(new OnItemSelectedListener() {
				public void onItemSelected(AdapterView<? extends Object> parent, View view,
					int pos, long id) {
					selectedPriority = ( Priority ) parent.getItemAtPosition(pos);
				}
				public void onNothingSelected(AdapterView<? extends Object> parent) {
				}
			});
	}
	/*** added by dDateRepeat* modified by dPriority
	 */
	private Reminder createReminderAux_original2() {
		Reminder reminder = new Reminder();
		reminder.setMonday(cbMonday.isChecked());
		reminder.setTuesday(cbTuesday.isChecked());
		reminder.setWednesday(cbWednesday.isChecked());
		reminder.setThursday(cbThursday.isChecked());
		reminder.setFriday(cbFriday.isChecked());
		reminder.setSaturday(cbSaturday.isChecked());
		try {
			reminder.setSunday(cbSunday.isChecked());
		}
		catch(InvalidDaysException e) {
			Toast.makeText(getApplicationContext(),
				"At least one day should be checked.", Toast.LENGTH_SHORT).show();
		}
		reminder.setHour(edtHour.getText().toString());
		return reminder;
	}
	/*** added by dDateRepeat* modified by dPriority
	 */
	private Reminder createReminderExisting_original4(Intent intent) {
		Reminder reminder = new Reminder();
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
		reminder.setHour(hour);
		return reminder;
	}
	/*** added by dDateRepeat* modified by dPriority
	 */
	private Reminder createReminderIntent_original6(Intent intent) {
		Reminder reminder = new Reminder();
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
		reminder.setHour(hour);
		return reminder;
	}
	/*** modified by dPriority
	 */
	private void initialize_original8(Reminder reminder) {
		try {
			edtReminder = ( EditText ) findViewById(R.id.edtReminder);
			edtDetails = ( EditText ) findViewById(R.id.edtDetails);
			if(reminder != null) {
				updateFieldsFromReminder(reminder);
			}
			btnSave = ( Button ) findViewById(R.id.btnSave);
			btnCancel = ( Button ) findViewById(R.id.btnCancel);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*** added by dDateRepeat* modified by dPriority
	 */
	private void updateFieldsFromReminder_original10(Reminder reminder) throws
	Exception {
		edtReminder.setText(reminder.getText());
		edtDetails.setText(reminder.getDetails());
		cbMonday.setChecked(reminder.isMonday());
		cbTuesday.setChecked(reminder.isTuesday());
		cbWednesday.setChecked(reminder.isWednesday());
		cbThursday.setChecked(reminder.isThursday());
		cbFriday.setChecked(reminder.isFriday());
		cbSaturday.setChecked(reminder.isSaturday());
		cbSunday.setChecked(reminder.isSunday());
		edtHour.setText(reminder.getHour());
	}
	/*** added by dPriority
	 */
	private Spinner getSpinnerPriority() {
		Spinner spinner = ( Spinner ) findViewById(R.id.spinnerPriorities);
		SpinnerAdapterGenerator<Priority> adapterPriorityGenerator = new
		SpinnerAdapterGenerator<Priority>();
		List<Priority> priorityValues = Arrays.asList(Priority.values());
		ArrayAdapter<Priority> priorityArrayAdapter =
		adapterPriorityGenerator.getSpinnerAdapter(priorityValues, this);
		spinner.setAdapter(priorityArrayAdapter);
		spinner.setSelection(Priority.NORMAL.getCode());
		return spinner;
	}
}