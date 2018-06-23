package reminder.daterepeat.br.unb.cic.reminders.model.db;

import java.util.List;
import reminder.daterepeat.br.unb.cic.framework.persistence.DBException;
import reminder.daterepeat.br.unb.cic.reminders.model.Reminder;
/*** added by dManageReminder
 */
public interface ReminderDAO {
	public Long saveReminder(Reminder r) throws DBException;
	public List<Reminder> listReminders() throws DBException;
	public void updateReminder(Reminder reminder) throws DBException;
	public void deleteReminder(Reminder reminder) throws DBException;
	public void persistReminder(Reminder reminder) throws DBException;
}