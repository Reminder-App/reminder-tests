package reminder.fixeddate.util;

/*** added by dManageReminder
 */
public class Patterns {
	public static final String TEXT_PATTERN =
	"[^.!?\\s][^.!?]*(?:[.!?](?![']?\\s|$)[^.!?]*)*[.!?]?[']?(?=\\s|$)";
	public static final String DATE_PATTERN =
	"(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d";
	public static final String HOUR_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
}