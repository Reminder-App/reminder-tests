package reminder.daterangemanagecategory.br.unb.cic.reminders.model.db;

import java.util.List;
import
reminder.daterangemanagecategory.br.unb.cic.framework.persistence.DBException;
import reminder.daterangemanagecategory.br.unb.cic.reminders.model.Category;
/*** added by dStaticCategory* modified by dManageCategory
 */
public interface CategoryDAO {
	public List<Category> listCategories() throws DBException;
	public Category findCategory(String name) throws DBException;
	public Category findCategoryById(Long id) throws DBException;
	/*** added by dManageCategory
	 */
	public void saveCategory(Category category) throws DBException;
	/*** added by dManageCategory
	 */
	public void updateCategory(Category category) throws DBException;
	/*** added by dManageCategory
	 */
	public void deleteCategory(Category category) throws DBException;
}