package reminder.fixeddatepriority.br.unb.cic.reminders.view;

import java.util.Iterator;
import android.content.Context;
import android.widget.ArrayAdapter;
import br.unb.cic.reminders2.R;
/*** added by dManageReminder
 */
public class SpinnerAdapterGenerator<T> {
	public ArrayAdapter<T> getSpinnerAdapter(Iterable<T> items, Context context) {
		ArrayAdapter<T> adapter = new
		ArrayAdapter<T>(context.getApplicationContext(), R.layout.spinner_item);
		adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
		if(items != null && items.iterator().hasNext()) {
			Iterator<T> it = items.iterator();
			while(it.hasNext()) {
				adapter.add(it.next());
			}
		}
		return adapter;
	}
}