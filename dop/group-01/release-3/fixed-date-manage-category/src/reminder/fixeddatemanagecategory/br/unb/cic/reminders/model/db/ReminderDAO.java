package reminder.fixeddatemanagecategory.br.unb.cic.reminders.model.db;

import java.util.List;
import
reminder.fixeddatemanagecategory.br.unb.cic.framework.persistence.DBException;
import reminder.fixeddatemanagecategory.br.unb.cic.reminders.model.Reminder;
import reminder.fixeddatemanagecategory.br.unb.cic.reminders.model.Category;
/*** added by dManageReminder* modified by dStaticCategory
 */
public interface ReminderDAO {
	public Long saveReminder(Reminder r) throws DBException;
	public List<Reminder> listReminders() throws DBException;
	public void updateReminder(Reminder reminder) throws DBException;
	public void deleteReminder(Reminder reminder) throws DBException;
	public void persistReminder(Reminder reminder) throws DBException;
	/*** added by dStaticCategory
	 */
	public List<Reminder> listRemindersByCategory(Category category) throws
	DBException;
}