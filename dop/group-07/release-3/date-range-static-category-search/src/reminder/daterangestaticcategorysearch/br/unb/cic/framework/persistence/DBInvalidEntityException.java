package reminder.daterangestaticcategorysearch.br.unb.cic.framework.persistence;

/*** added by dManageReminder
 */
public class DBInvalidEntityException extends Exception {
	private static final long serialVersionUID = 1L;
	public DBInvalidEntityException(Object e) {
		super(e.getClass().getCanonicalName() +
			"is not a valid entity according to the persistence framework.");
	}
}