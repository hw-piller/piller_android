package one.kafuuchino.piller.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.nitrico.lastadapter.LastAdapter;

import java.util.ArrayList;

import one.kafuuchino.piller.R;
import one.kafuuchino.piller.databinding.FragmentSettingsBinding;
import one.kafuuchino.piller.models.SettingsItem;

/**
 * Created by Junseok Oh on 2017-04-09.
 */

public class SettingsFragment extends Fragment {
    private int mPageNumber;
    private String title;
    private FragmentSettingsBinding binding;

    ArrayList<SettingsItem> arrayList = new ArrayList<>();
    LastAdapter settingsAdapter;
    RecyclerView settingsRecyclerView;

    public static SettingsFragment create(int pageNumber) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putInt("page", pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt("page");
        title = getArguments().getString("exchange");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);
//        settingsRecyclerView = binding.settingsRecyclerView;
//        settingsAdapter = new LastAdapter(arrayList, BR.content)
//                .map(String.class, new ItemType<ViewDataBinding>(R.layout.settings_content_header))
//                .map(SettingsItem.class, new ItemType<ViewDataBinding>(R.layout.settings_content))
//                .into(settingsRecyclerView);
        return binding.getRoot();
    }


}
