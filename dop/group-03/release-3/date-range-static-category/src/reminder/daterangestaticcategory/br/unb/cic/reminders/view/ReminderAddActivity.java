package reminder.daterangestaticcategory.br.unb.cic.reminders.view;

import java.util.Arrays;
import java.util.List;
import reminder.daterangestaticcategory.util.Mask;
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
import
reminder.daterangestaticcategory.br.unb.cic.reminders.controller.Controller;
import
reminder.daterangestaticcategory.br.unb.cic.reminders.model.InvalidDateException;
import
reminder.daterangestaticcategory.br.unb.cic.reminders.model.InvalidFormatException;
import
reminder.daterangestaticcategory.br.unb.cic.reminders.model.InvalidTextException;
import reminder.daterangestaticcategory.br.unb.cic.reminders.model.Reminder;
import br.unb.cic.reminders2.R;
import reminder.daterangestaticcategory.br.unb.cic.reminders.model.Category;
/*** added by dManageReminder* modified by dDateRange* modified by
dStaticCategory
 */
public class ReminderAddActivity extends Activity {
	private EditText edtReminder, edtDetails;
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
	/*** modified by dStaticCategory
	 */
	private void configureActionListener() {
		configureActionListener_original0();
		addListenerToSpinnerCategory();
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
	/*** modified by dStaticCategory
	 */
	private void initialize(Reminder reminder) {
		try {
			spinnerCategory = getSpinnerCategory();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		initialize_original8(reminder);
	}
	/*** added by dDateRange
	 */
	private EditText edtDateStart, edtHourStart, edtDateFinal, edtHourFinal;
	/*** added by dDateRange* modified by dStaticCategory
	 */
	private Reminder createReminderAux() {
		Reminder reminder = createReminderAux_original2();
		reminder.setCategory(selectedCategory);
		return reminder;
	}
	/*** added by dDateRange* modified by dStaticCategory
	 */
	private Reminder createReminderExisting(Intent intent) {
		Reminder reminder = createReminderExisting_original4(intent);
		String categoryName = intent.getStringExtra("category_name");
		String categoryId = intent.getStringExtra("category_id");
		Category category = new Category();
		category.setName(categoryName);
		category.setId(Long.parseLong(categoryId));
		reminder.setCategory(category);
		return reminder;
	}
	/*** added by dDateRange* modified by dStaticCategory
	 */
	private Reminder createReminderIntent(Intent intent) {
		Reminder reminder = createReminderIntent_original6(intent);
		String category = intent.getStringExtra("category");
		Category auxCategory = new Category();
		auxCategory.setName(category);
		reminder.setCategory(auxCategory);
		return reminder;
	}
	/*** added by dDateRange* modified by dStaticCategory
	 */
	private void updateFieldsFromReminder(Reminder reminder) throws Exception {
		updateFieldsFromReminder_original10(reminder);
		spinnerCategory.setSelection(categoryToIndex(reminder.getCategory()));
	}
	/*** added by dStaticCategory
	 */
	private Category selectedCategory;
	/*** added by dStaticCategory
	 */
	private Spinner spinnerCategory;
	/*** modified by dStaticCategory
	 */
	private void configureActionListener_original0() {
		addListenerToBtnSave();
		addListenerToBtnCancel();
	}
	/*** added by dStaticCategory
	 */
	private void addListenerToSpinnerCategory() {
		spinnerCategory.setOnItemSelectedListener(new OnItemSelectedListener() {
				public void onItemSelected(AdapterView<? extends Object> parent, View view,
					int pos, long id) {
					selectedCategory = ( Category ) parent.getItemAtPosition(pos);
				}
				public void onNothingSelected(AdapterView<? extends Object> parent) {
				}
			});
	}
	/*** added by dDateRange* modified by dStaticCategory
	 */
	private Reminder createReminderAux_original2() {
		Reminder reminder = new Reminder();
		reminder.setDateStart(edtDateStart.getText().toString());
		reminder.setHourStart(edtHourStart.getText().toString());
		reminder.setDateFinal(edtDateFinal.getText().toString());
		reminder.setHourFinal(edtHourFinal.getText().toString());
		return reminder;
	}
	/*** added by dDateRange* modified by dStaticCategory
	 */
	private Reminder createReminderExisting_original4(Intent intent) {
		Reminder reminder = new Reminder();
		String dateStart = intent.getStringExtra("dateStart");
		String hourStart = intent.getStringExtra("hourStart");
		String dateFinal = intent.getStringExtra("dateFinal");
		String hourFinal = intent.getStringExtra("hourFinal");
		reminder.setDateStart(dateStart);
		reminder.setHourStart(hourStart);
		reminder.setDateFinal(dateFinal);
		reminder.setHourFinal(hourFinal);
		return reminder;
	}
	/*** added by dDateRange* modified by dStaticCategory
	 */
	private Reminder createReminderIntent_original6(Intent intent) {
		Reminder reminder = new Reminder();
		String dateStart = intent.getStringExtra("dateStart");
		String hourStart = intent.getStringExtra("hourStart");
		String dateFinal = intent.getStringExtra("dateFinal");
		String hourFinal = intent.getStringExtra("hourFinal");
		reminder.setDateStart(dateStart);
		reminder.setHourStart(hourStart);
		reminder.setDateFinal(dateFinal);
		reminder.setHourFinal(hourFinal);
		return reminder;
	}
	/*** modified by dStaticCategory
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
	/*** added by dDateRange* modified by dStaticCategory
	 */
	private void updateFieldsFromReminder_original10(Reminder reminder) throws
	Exception {
		edtReminder.setText(reminder.getText());
		edtDetails.setText(reminder.getDetails());
		edtDateStart.setText(reminder.getDateStart());
		edtHourStart.setText(reminder.getHourStart());
		edtDateFinal.setText(reminder.getDateFinal());
		edtHourFinal.setText(reminder.getHourFinal());
	}
	/*** added by dStaticCategory
	 */
	private Spinner getSpinnerCategory() throws Exception {
		Spinner spinner = ( Spinner ) findViewById(R.id.spinnerCategories);
		SpinnerAdapterGenerator<Category> adapterCategoryGenerator = new
		SpinnerAdapterGenerator<Category>();
		List<Category> categories =
		Controller.instance(getApplicationContext()).listCategories();
		spinner.setAdapter(adapterCategoryGenerator.getSpinnerAdapter(categories,
				this));
		return spinner;
	}
	/*** added by dStaticCategory
	 */
	private int categoryToIndex(Category category) throws Exception {
		List<Category> categories =
		Controller.instance(getApplicationContext()).listCategories();
		int i = 0;
		for(Category c : categories) {
			if(c.getName().equals(category.getName())) {
				return i;
			}
			i ++;
		}
		return 0;
	}
}