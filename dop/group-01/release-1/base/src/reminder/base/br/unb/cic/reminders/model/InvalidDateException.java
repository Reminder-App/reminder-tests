package reminder.base.br.unb.cic.reminders.model;

/*** added by dService
 */
public class InvalidDateException extends InvalidFormatException {
	private static final long serialVersionUID = 7194191177254259925L;
	public InvalidDateException(Object o) {
		super("This date " + o);
	}
}