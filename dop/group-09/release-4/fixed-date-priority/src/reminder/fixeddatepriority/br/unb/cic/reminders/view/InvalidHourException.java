package reminder.fixeddatepriority.br.unb.cic.reminders.view;

import
reminder.fixeddatepriority.br.unb.cic.reminders.model.InvalidFormatException;
/*** added by dManageReminder
 */
public class InvalidHourException extends InvalidFormatException {
	private static final long serialVersionUID = 8341230773647025695L;
	public InvalidHourException(Object o) {
		super("This hour " + o);
	}
}