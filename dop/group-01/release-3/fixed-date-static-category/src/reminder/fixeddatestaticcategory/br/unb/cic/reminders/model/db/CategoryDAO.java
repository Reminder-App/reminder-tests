package reminder.fixeddatestaticcategory.br.unb.cic.reminders.model.db;

import java.util.List;
import
reminder.fixeddatestaticcategory.br.unb.cic.framework.persistence.DBException;
import reminder.fixeddatestaticcategory.br.unb.cic.reminders.model.Category;
/*** added by dStaticCategory
 */
public interface CategoryDAO {
	public List<Category> listCategories() throws DBException;
	public Category findCategory(String name) throws DBException;
	public Category findCategoryById(Long id) throws DBException;
}