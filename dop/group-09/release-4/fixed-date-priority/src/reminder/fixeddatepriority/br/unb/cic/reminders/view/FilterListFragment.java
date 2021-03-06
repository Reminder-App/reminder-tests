package reminder.fixeddatepriority.br.unb.cic.reminders.view;

import java.util.ArrayList;
import java.util.List;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import reminder.fixeddatepriority.br.unb.cic.framework.persistence.DBException;
import
reminder.fixeddatepriority.br.unb.cic.reminders.controller.AllRemindersFilter;
import reminder.fixeddatepriority.br.unb.cic.reminders.controller.Controller;
import
reminder.fixeddatepriority.br.unb.cic.reminders.controller.PriorityFilter;
import
reminder.fixeddatepriority.br.unb.cic.reminders.controller.ReminderFilter;
import reminder.fixeddatepriority.br.unb.cic.reminders.model.Priority;
import br.unb.cic.reminders2.R;
/*** added by dPriority
 */
public class FilterListFragment extends Fragment implements OnItemClickListener
{
	private static final String CURRENT_FILTER_KEY = "current_filter";
	private static String TAG = "filter fragment list";
	private int currentFilterIndex;
	private List<FiltersListChangeListener> listeners;
	private FiltersListChangeListener filtersChangeListener;
	private int currentFilterId;
	private ReminderFilterArrayAdapter adapter;
	private View view;
	private Button btAddCategory;
	private ListView lvFilters;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if(savedInstanceState != null) {
			currentFilterIndex = savedInstanceState.getInt(CURRENT_FILTER_KEY);
		}
		currentFilterId = 0;
		adapter = null;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
		savedInstanceState) {
		view = inflater.inflate(R.layout.categories_list_fragment, container, false);
		createUI();
		return view;
	}
	@Override
	public void onResume() {
		super.onResume();
		updateListView();
	}
	public void addListener(FiltersListChangeListener filter) {
		if(listeners == null) listeners = new ArrayList<FiltersListChangeListener>();
		listeners.add(filter);
	}
	public void notifyListeners(ReminderFilter filter) {
		for(FiltersListChangeListener c : listeners) {
			c.onSelectedFilterChanged(filter);
		}
	}
	private void createUI() {
		lvFilters = ( ListView ) view.findViewById(R.id.listCategories);
		lvFilters.setOnItemClickListener(this);
		registerForContextMenu(lvFilters);
		updateListView();
	}
	private void updateListView() {
		List<ReminderFilter> filters = listOfFilters();
		adapter = new
		ReminderFilterArrayAdapter(getActivity().getApplicationContext(), filters);
		lvFilters.setAdapter(adapter);
	}
	private List<ReminderFilter> listOfFilters() {
		List<ReminderFilter> filters = new ArrayList<ReminderFilter>();
		AllRemindersFilter allRemindersFilter = new
		AllRemindersFilter(getActivity());
		filters.add(allRemindersFilter);
		PriorityFilter highPriorityFilter = new PriorityFilter(Priority.HIGH,
			getActivity());
		filters.add(highPriorityFilter);
		PriorityFilter normalPriorityFilter = new PriorityFilter(Priority.NORMAL,
			getActivity());
		filters.add(normalPriorityFilter);
		PriorityFilter lowPriorityFilter = new PriorityFilter(Priority.LOW,
			getActivity());
		filters.add(lowPriorityFilter);
		return filters;
	}
	public void onItemClick(AdapterView<? extends Object> adapterView, View view,
		int position, long id) {
		notifyListeners(adapter.getItem(position));
	}
	public void onNothingSelected(AdapterView<? extends Object> arg0) {
	}
}