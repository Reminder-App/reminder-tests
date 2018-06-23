package reminder.base.br.unb.cic.reminders.model.db;

import android.content.Context;
/*** added by dService
 */
public class DefaultDBFactory extends DBFactory {
	public DefaultDBFactory(Context context) {
		super(context);
	}
	@Override
	public ReminderDAO createReminderDAO() {
		return new DefaultReminderDAO(context);
	}
}