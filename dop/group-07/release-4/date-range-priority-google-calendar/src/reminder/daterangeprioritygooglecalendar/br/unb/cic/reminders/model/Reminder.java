package reminder.daterangeprioritygooglecalendar.br.unb.cic.reminders.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import reminder.daterangeprioritygooglecalendar.util.Patterns;
import
reminder.daterangeprioritygooglecalendar.br.unb.cic.framework.persistence.DBTypes;
import
reminder.daterangeprioritygooglecalendar.br.unb.cic.framework.persistence.annotations.Column;
import
reminder.daterangeprioritygooglecalendar.br.unb.cic.framework.persistence.annotations.Entity;
import
reminder.daterangeprioritygooglecalendar.br.unb.cic.framework.persistence.annotations.ForeignKey;
import
reminder.daterangeprioritygooglecalendar.br.unb.cic.reminders.view.InvalidHourException;
/*** added by dManageReminder* modified by dDateRange* modified by dPriority
 */
@Entity(table = "REMINDER")
public class Reminder {
	@Column(column = "PK", primaryKey = true, type = DBTypes.LONG)
	private Long id;
	@Column(column = "TEXT", type = DBTypes.TEXT)
	private String text;
	@Column(column = "DETAILS", type = DBTypes.TEXT)
	private String details;
	@Column(column = "DONE", type = DBTypes.INT)
	private boolean done;
	public Reminder() {
	}
	public Reminder(Long id, String text) {
		this.id = id;
		this.text = text;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		if(text == null || text.trim().equals("")) {
			throw new InvalidTextException(text);
		}
		this.text = text;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		if(details == null || details.trim().equals("")) {
			this.details = null;
		}
		else {
			this.details = details;
		}
	}
	private boolean checkFormat(String date, String pattern) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(date);
		return m.matches();
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public int getDone() {
		return done ? 1 : 0;
	}
	public void setDone(int done) {
		this.done =(done == 0 ? false : true);
	}
	/*** added by dDateRange
	 */
	@Column(column = "INITIAL_DATE", type = DBTypes.TEXT)
	private String dateStart;
	/*** added by dDateRange
	 */
	@Column(column = "INITIAL_HOUR", type = DBTypes.TEXT)
	private String hourStart;
	/*** added by dDateRange
	 */
	@Column(column = "FINAL_DATE", type = DBTypes.TEXT)
	private String dateFinal;
	/*** added by dDateRange
	 */
	@Column(column = "FINAL_HOUR", type = DBTypes.TEXT)
	private String hourFinal;
	/*** added by dDateRange
	 */
	public String getDateStart() {
		return dateStart;
	}
	/*** added by dDateRange
	 */
	public void setDateStart(String dateStart) {
		if(dateStart == null) throw new InvalidDateException(dateStart);
		if(!(dateStart == null || dateStart.equals("")) && ! checkFormat(dateStart,
				Patterns.DATE_PATTERN)) {
			throw new InvalidDateException(dateStart);
		}
		this.dateStart = dateStart;
	}
	/*** added by dDateRange
	 */
	public String getHourStart() {
		return hourStart;
	}
	/*** added by dDateRange
	 */
	public void setHourStart(String hourStart) {
		if(!(hourStart == null || hourStart.equals("")) && ! checkFormat(hourStart,
				Patterns.HOUR_PATTERN)) {
			throw new InvalidHourException(hourStart);
		}
		this.hourStart = hourStart;
	}
	/*** added by dDateRange
	 */
	public String getDateFinal() {
		return dateFinal;
	}
	/*** added by dDateRange
	 */
	public void setDateFinal(String dateFinal) {
		if(dateFinal == null) throw new InvalidDateException(dateFinal);
		if(!(dateFinal == null || dateFinal.equals("")) && ! checkFormat(dateFinal,
				Patterns.DATE_PATTERN)) {
			throw new InvalidDateException(dateFinal);
		}
		this.dateFinal = dateFinal;
	}
	/*** added by dDateRange
	 */
	public String getHourFinal() {
		return hourFinal;
	}
	/*** added by dDateRange
	 */
	public void setHourFinal(String hourFinal) {
		if(!(hourFinal == null || hourFinal.equals("")) && ! checkFormat(hourFinal,
				Patterns.HOUR_PATTERN)) {
			throw new InvalidHourException(hourFinal);
		}
		this.hourFinal = hourFinal;
	}
	/*** added by dDateRange* modified by dPriority
	 */
	public boolean isValid() {
		return(priority != null && isValid_original0());
	}
	/*** added by dPriority
	 */
	@Column(column = "PRIORITY", type = DBTypes.INT)
	private Priority priority;
	/*** added by dPriority
	 */
	public int getPriority() {
		return priority.getCode();
	}
	/*** added by dPriority
	 */
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	/*** added by dDateRange* modified by dPriority
	 */
	public boolean isValid_original0() {
		return(text != null && dateStart != null && hourStart != null && dateFinal !=
			null && hourFinal != null);
	}
}