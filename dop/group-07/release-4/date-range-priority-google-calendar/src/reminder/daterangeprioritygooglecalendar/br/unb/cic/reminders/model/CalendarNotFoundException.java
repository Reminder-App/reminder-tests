package reminder.daterangeprioritygooglecalendar.br.unb.cic.reminders.model;

/*** added by dGoogleCalendar
 */
public class CalendarNotFoundException extends RuntimeException {
	private static final long serialVersionUID = - 3121418618753940886L;
	public CalendarNotFoundException() {
		super("Google Account Not Found");
	}
}