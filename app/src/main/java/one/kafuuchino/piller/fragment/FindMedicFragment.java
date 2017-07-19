package one.kafuuchino.piller.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.nitrico.lastadapter.LastAdapter;

import java.util.ArrayList;

import one.kafuuchino.piller.R;
import one.kafuuchino.piller.databinding.FragmentFinddocBinding;

/**
 * Created by Junseok Oh on 2017-04-09.
 */

public class FindMedicFragment extends Fragment {
    private int mPageNumber;
    private String title;
    private FragmentFinddocBinding binding;

    ArrayList<Object> arrayList = new ArrayList<>();
    LastAdapter savedCardAdapter;
    RecyclerView savedCards;
    private GridLayoutManager layoutManager;


    public static FindMedicFragment create(int pageNumber) {
        FindMedicFragment fragment = new FindMedicFragment();
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_finddoc, container, false);
        return binding.getRoot();
    }

}
