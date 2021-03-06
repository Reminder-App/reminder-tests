package reminder.daterange.br.unb.cic.reminders.controller;

import android.content.Context;
import reminder.daterange.br.unb.cic.reminders.model.Reminder;
/*** added by dManageReminder
 */
public class AllRemindersFilter extends ReminderFilter {
	private final String name = "All";
	public AllRemindersFilter(Context context) {
		super(context);
	}
	@Override
	protected boolean selectReminder(Reminder r) {
		return true;
	}
	@Override
	public String getName() {
		return name;
	}
}