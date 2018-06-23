package reminder.daterepeat.br.unb.cic.reminders.view;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import reminder.daterepeat.br.unb.cic.reminders.controller.Controller;
import reminder.daterepeat.br.unb.cic.reminders.model.InvalidDateException;
import reminder.daterepeat.br.unb.cic.reminders.model.InvalidTextException;
import reminder.daterepeat.br.unb.cic.reminders.model.Reminder;
import br.unb.cic.reminders2.R;
import reminder.daterepeat.br.unb.cic.reminders.model.InvalidDaysException;
/*** added by dManageReminder* modified by dDateRepeat
 */
public abstract class ReminderActivity extends Activity {
	protected Reminder reminder;
	protected Calendar time;
	protected EditText edtReminder, edtDetails, edtTime;
	protected Spinner spinnerTime;
	private Button btnSave, btnCancel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reminder_add);
		if(reminder == null) reminder = new Reminder();
		initializeFields();
		initializeListeners();
		initializeValues();
	}
	protected abstract void initializeValues();
	private void addListenerToBtnSave() {
		btnSave.setOnClickListener(new Button.OnClickListener() {
				public void onClick(View v) {
					try {
						createReminder();
						persist(reminder);
						finish();
					}
					catch(Exception e) {
						Log.e("ReminderActivity", e.getMessage());
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
	private void addListenerToSpinnerTime() {
		spinnerTime.setOnTouchListener(new View.OnTouchListener() {
				public boolean onTouch(View v, MotionEvent event) {
					spinnerTime = getSpinnerTime();
					return false;
				}
			});
		spinnerTime.setOnKeyListener(new View.OnKeyListener() {
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					spinnerTime = getSpinnerTime();
					return false;
				}
			});
		spinnerTime.setOnItemSelectedListener(new OnItemSelectedListener() {
				public void onItemSelected(AdapterView<? extends Object> parent, View view,
					int pos, long id) {
					switch(pos) {
						case 0 : time = null;
						break;
						case 1 : if(time == null) time = Calendar.getInstance();
						DialogFragment newFragment = new TimePickerDialogFragment(time,
							spinnerTime);
						newFragment.show(getFragmentManager(), "timePicker");
						break;
						default :
					}
				}
				public void onNothingSelected(AdapterView<? extends Object> arg0) {
				}
			});
	}
	private void createReminder() {
		try {
			reminder.setText(edtReminder.getText().toString());
			reminder.setDetails(edtDetails.getText().toString());
			setValuesOnReminder();
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
		catch(Exception e) {
			Toast.makeText(getApplicationContext(), "Serious error.",
				Toast.LENGTH_SHORT).show();
		}
	}
	private String timeToString() {
		if(time == null) return null;
		String sTime;
		sTime = Integer.toString(time.get(Calendar.MINUTE));
		if(time.get(Calendar.MINUTE) < 10) sTime = "0" + sTime;
		sTime = Integer.toString(time.get(Calendar.HOUR_OF_DAY)) + ":" + sTime;
		if(time.get(Calendar.HOUR_OF_DAY) < 10) sTime = "0" + sTime;
		return sTime;
	}
	protected void updateTimeFromString(String sTime) {
		if(sTime == null || sTime.equals("")) {
			time = null;
			return;
		}
		char sHour [] = {
			sTime.charAt(0), sTime.charAt(1)
		};
		int hour = Integer.parseInt(new String(sHour), 10);
		char sMinute [] = {
			sTime.charAt(3), sTime.charAt(4)
		};
		int minute = Integer.parseInt(new String(sMinute), 10);
		if(time == null) time = Calendar.getInstance();
		time.set(Calendar.MINUTE, minute);
		time.set(Calendar.HOUR_OF_DAY, hour);
	}
	@SuppressWarnings("unchecked")
	protected void updateSpinnerDateHour(Spinner spinner, String dateOrHour) {
		if(dateOrHour == null) return;
		ArrayAdapter<String> adapter = ( ArrayAdapter<String> ) spinner.getAdapter();
		int count = adapter.getCount();
		if(count > 2) {
			for(int i = 2;
				i < count;
				++ i) adapter.remove(adapter.getItem(i));
		}
		adapter.add(dateOrHour);
		spinner.setSelection(2);
	}
	private Spinner getSpinnerTime() {
		Spinner spinner = ( Spinner ) findViewById(R.id.spinnerTime);
		SpinnerAdapterGenerator<String> adapterTimeGenerator = new
		SpinnerAdapterGenerator<String>();
		List<String> items = new ArrayList<String>();
		items.add("No time");
		items.add("+ Select");
		spinner.setAdapter(adapterTimeGenerator.getSpinnerAdapter(items, this));
		return spinner;
	}
	protected abstract void persist(Reminder reminder);
	/*** added by dDateRepeat
	 */
	protected CheckBox cbMonday, cbTuesday, cbWednesday, cbThursday, cbFriday,
	cbSaturday, cbSunday;
	/*** added by dDateRepeat
	 */
	private void initializeFields() {
		btnSave = ( Button ) findViewById(R.id.btnSave);
		btnCancel = ( Button ) findViewById(R.id.btnCancel);
		edtReminder = ( EditText ) findViewById(R.id.edtReminder);
		edtDetails = ( EditText ) findViewById(R.id.edtDetails);
		cbMonday = ( CheckBox ) findViewById(R.id.cbMonday);
		cbTuesday = ( CheckBox ) findViewById(R.id.cbTuesday);
		cbWednesday = ( CheckBox ) findViewById(R.id.cbWednesday);
		cbThursday = ( CheckBox ) findViewById(R.id.cbThursday);
		cbFriday = ( CheckBox ) findViewById(R.id.cbFriday);
		cbSaturday = ( CheckBox ) findViewById(R.id.cbSaturday);
		cbSunday = ( CheckBox ) findViewById(R.id.cbSunday);
		spinnerTime = getSpinnerTime();
	}
	/*** added by dDateRepeat
	 */
	private void initializeListeners() {
		addListenerToBtnSave();
		addListenerToBtnCancel();
		addListenerToSpinnerTime();
	}
	/*** added by dDateRepeat
	 */
	private void setValuesOnReminder() throws Exception {
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
		reminder.setHour(timeToString());
	}
}