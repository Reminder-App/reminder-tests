package reminder.daterepeat.br.unb.cic.reminders.model;

/*** added by dDateRepeat
 */
public class InvalidDaysException extends InvalidFormatException {
	private static final long serialVersionUID = 7998188562654167391L;
	public InvalidDaysException(Object o) {
		super("These days " + o);
	}
}