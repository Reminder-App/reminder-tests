package reminder.daterangestaticcategorysearch.br.unb.cic.reminders.controller;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import
reminder.daterangestaticcategorysearch.br.unb.cic.reminders.model.Reminder;
/*** added by dManageReminder* modified by dSearch
 */
public abstract class ReminderFilter {
	private List<Reminder> reminders;
	private Context context;
	public ReminderFilter(Context context) {
		this.context = context;
	}
	public List<Reminder> getReminderList() {
		updateReminders();
		return reminders;
	}
	public int getNumReminders() {
		updateReminders();
		return reminders.size();
	}
	private void updateReminders() {
		reminders = new ArrayList<Reminder>();
		List<Reminder> allReminders = null;
		try {
			allReminders = Controller.instance(context).listReminders();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		for(Reminder r : allReminders) {
			if(selectReminder(r)) {
				reminders.add(r);
			}
		}
	}
	abstract protected boolean selectReminder(Reminder r);
	abstract public String getName();
	/*** added by dSearch
	 */
	public List<Reminder> getReminders(String search) {
		updateReminders();
		if(search == null || search.trim().equals("")) {
			updateReminders();
			return reminders;
		}
		List<Reminder> values = new ArrayList<Reminder>();
		for(Reminder item : this.reminders) {
			if(item.getText().contains(search)) {
				values.add(item);
			}
		}
		return values;
	}
}