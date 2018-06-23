package reminder.daterepeatstaticcategory.br.unb.cic.reminders.model.db;

import java.util.List;
import
reminder.daterepeatstaticcategory.br.unb.cic.framework.persistence.DBException;
import reminder.daterepeatstaticcategory.br.unb.cic.reminders.model.Category;
/*** added by dStaticCategory
 */
public interface CategoryDAO {
	public List<Category> listCategories() throws DBException;
	public Category findCategory(String name) throws DBException;
	public Category findCategoryById(Long id) throws DBException;
}