package reminder.base.br.unb.cic.reminders.model;

/*** added by dService
 */
public class InvalidFormatException extends RuntimeException {
	private static final long serialVersionUID = 2850584940310533216L;
	public InvalidFormatException(Object o) {
		super(o + " is in the wrong format");
	}
}