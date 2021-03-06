package reminder.daterepeatmanagecategorysearch.br.unb.cic.reminders.view;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import
reminder.daterepeatmanagecategorysearch.br.unb.cic.reminders.controller.ReminderFilter;
import br.unb.cic.reminders2.R;
/*** added by dManageReminder* modified by dStaticCategory* modified by
dManageCategory
 */
public class ReminderFilterArrayAdapter extends ArrayAdapter<ReminderFilter> {
	/*** added by dManageCategory
	 */
	public ReminderFilterArrayAdapter(Context context, List<ReminderFilter>
		objects) {
		super(context, R.layout.category_row, objects);
	}
	/*** added by dManageCategory
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout filterRow;
		if(convertView == null) {
			filterRow = new LinearLayout(getContext());
			String inflater = Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater vi;
			vi = ( LayoutInflater ) getContext().getSystemService(inflater);
			vi.inflate(R.layout.category_row, filterRow, true);
		}
		else {
			filterRow = ( LinearLayout ) convertView;
		}
		TextView tvFilter = ( TextView )
		filterRow.findViewById(R.id.row_categoryName);
		tvFilter.setText(getItem(position).getName());
		TextView tvNumReminders = ( TextView )
		filterRow.findViewById(R.id.row_categoryCounter);
		tvNumReminders.setText(Integer.toString(getItem(position).getNumReminders()));
		return filterRow;
	}
}