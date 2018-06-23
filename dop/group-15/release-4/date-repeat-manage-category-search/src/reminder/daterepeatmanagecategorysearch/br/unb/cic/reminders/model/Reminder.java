package reminder.daterepeatmanagecategorysearch.br.unb.cic.reminders.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import reminder.daterepeatmanagecategorysearch.util.Patterns;
import
reminder.daterepeatmanagecategorysearch.br.unb.cic.framework.persistence.DBTypes;
import
reminder.daterepeatmanagecategorysearch.br.unb.cic.framework.persistence.annotations.Column;
import
reminder.daterepeatmanagecategorysearch.br.unb.cic.framework.persistence.annotations.Entity;
import
reminder.daterepeatmanagecategorysearch.br.unb.cic.framework.persistence.annotations.ForeignKey;
import
reminder.daterepeatmanagecategorysearch.br.unb.cic.reminders.view.InvalidHourException;
/*** added by dManageReminder* modified by dDateRepeat* modified by
dStaticCategory
 */
@Entity(table = "REMINDER")
public class Reminder {
	@Column(column = "PK", primaryKey = true, type = DBTypes.LONG)
	private Long id;
	@Column(column = "TEXT", type = DBTypes.TEXT)
	private String text;
	@Column(column = "DETAILS", type = DBTypes.TEXT)
	private String details;
	@Column(column = "HOUR", type = DBTypes.TEXT)
	private String hour;
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
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		if(!(hour == null || hour.equals("")) && ! checkFormat(hour,
				Patterns.HOUR_PATTERN)) {
			throw new InvalidHourException(hour);
		}
		this.hour = hour;
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
	/*** added by dDateRepeat
	 */
	@Column(column = "MONDAY", type = DBTypes.INT)
	private boolean monday;
	/*** added by dDateRepeat
	 */
	@Column(column = "TUESDAY", type = DBTypes.INT)
	private boolean tuesday;
	/*** added by dDateRepeat
	 */
	@Column(column = "WEDNESDAY", type = DBTypes.INT)
	private boolean wednesday;
	/*** added by dDateRepeat
	 */
	@Column(column = "THURSDAY", type = DBTypes.INT)
	private boolean thursday;
	/*** added by dDateRepeat
	 */
	@Column(column = "FRIDAY", type = DBTypes.INT)
	private boolean friday;
	/*** added by dDateRepeat
	 */
	@Column(column = "SATURDAY", type = DBTypes.INT)
	private boolean saturday;
	/*** added by dDateRepeat
	 */
	@Column(column = "SUNDAY", type = DBTypes.INT)
	private boolean sunday;
	/*** added by dDateRepeat* modified by dStaticCategory
	 */
	public boolean isValid() {
		return(category != null && isValid_original0());
	}
	/*** added by dDateRepeat
	 */
	public boolean isOneCbSelected() {
		return(monday != false || tuesday != false || wednesday != false || thursday
			!= false || friday != false || saturday != false);
	}
	/*** added by dDateRepeat
	 */
	public boolean isMonday() {
		return monday;
	}
	/*** added by dDateRepeat
	 */
	public void setMonday(boolean monday) {
		this.monday = monday;
	}
	/*** added by dDateRepeat
	 */
	public void setMonday(int monday) {
		this.monday =(monday == 0 ? false : true);
	}
	/*** added by dDateRepeat
	 */
	public int getMonday() {
		return monday ? 1 : 0;
	}
	/*** added by dDateRepeat
	 */
	public boolean isTuesday() {
		return tuesday;
	}
	/*** added by dDateRepeat
	 */
	public void setTuesday(boolean tuesday) {
		this.tuesday = tuesday;
	}
	/*** added by dDateRepeat
	 */
	public void setTuesday(int tuesday) {
		this.tuesday =(tuesday == 0 ? false : true);
	}
	/*** added by dDateRepeat
	 */
	public int getTuesday() {
		return tuesday ? 1 : 0;
	}
	/*** added by dDateRepeat
	 */
	public boolean isWednesday() {
		return wednesday;
	}
	/*** added by dDateRepeat
	 */
	public void setWednesday(boolean wednesday) {
		this.wednesday = wednesday;
	}
	/*** added by dDateRepeat
	 */
	public void setWednesday(int wednesday) {
		this.wednesday =(wednesday == 0 ? false : true);
	}
	/*** added by dDateRepeat
	 */
	public int getWednesday() {
		return wednesday ? 1 : 0;
	}
	/*** added by dDateRepeat
	 */
	public boolean isThursday() {
		return thursday;
	}
	/*** added by dDateRepeat
	 */
	public void setThursday(boolean thursday) {
		this.thursday = thursday;
	}
	/*** added by dDateRepeat
	 */
	public void setThursday(int thursday) {
		this.thursday =(thursday == 0 ? false : true);
	}
	/*** added by dDateRepeat
	 */
	public int getThursday() {
		return thursday ? 1 : 0;
	}
	/*** added by dDateRepeat
	 */
	public boolean isFriday() {
		return friday;
	}
	/*** added by dDateRepeat
	 */
	public void setFriday(boolean friday) {
		this.friday = friday;
	}
	/*** added by dDateRepeat
	 */
	public void setFriday(int friday) {
		this.friday =(friday == 0 ? false : true);
	}
	/*** added by dDateRepeat
	 */
	public int getFriday() {
		return friday ? 1 : 0;
	}
	/*** added by dDateRepeat
	 */
	public boolean isSaturday() {
		return saturday;
	}
	/*** added by dDateRepeat
	 */
	public void setSaturday(boolean saturday) {
		this.saturday = saturday;
	}
	/*** added by dDateRepeat
	 */
	public void setSaturday(int saturday) {
		this.saturday =(saturday == 0 ? false : true);
	}
	/*** added by dDateRepeat
	 */
	public int getSaturday() {
		return saturday ? 1 : 0;
	}
	/*** added by dDateRepeat
	 */
	public boolean isSunday() {
		return sunday;
	}
	/*** added by dDateRepeat
	 */
	public void setSunday(boolean sunday) {
		if(! isOneCbSelected() && ! sunday) {
			throw new InvalidDaysException("are all false");
		}
		this.sunday = sunday;
	}
	/*** added by dDateRepeat
	 */
	public void setSunday(int sunday) {
		this.sunday =(sunday == 0 ? false : true);
	}
	/*** added by dDateRepeat
	 */
	public int getSunday() {
		return sunday ? 1 : 0;
	}
	/*** added by dStaticCategory
	 */
	@Column(column = "FK_CATEGORY", type = DBTypes.LONG)
	@ForeignKey(mappedBy = "id")
	private Category category;
	/*** added by dStaticCategory
	 */
	public Category getCategory() {
		return category;
	}
	/*** added by dStaticCategory
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	/*** added by dDateRepeat* modified by dStaticCategory
	 */
	public boolean isValid_original0() {
		return(isOneCbSelected() && text != null && hour != null);
	}
}