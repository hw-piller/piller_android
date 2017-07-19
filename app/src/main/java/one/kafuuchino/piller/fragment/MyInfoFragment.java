package one.kafuuchino.piller.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.github.nitrico.lastadapter.Holder;
import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import one.kafuuchino.piller.R;
import one.kafuuchino.piller.databinding.FragmentMyinfoBinding;
import one.kafuuchino.piller.databinding.InfoContentBinding;
import one.kafuuchino.piller.models.Medicine;

/**
 * Created by Junseok Oh on 2017-04-09.
 */

public class MyInfoFragment extends Fragment {
    private int mPageNumber;
    private String title;
    private FragmentMyinfoBinding binding;

    ArrayList<Medicine> arrayList = new ArrayList<>();
    LastAdapter adapter;
    RecyclerView infoRecyclerView;

    public static MyInfoFragment create(int pageNumber) {
        MyInfoFragment fragment = new MyInfoFragment();
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
        setData();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_myinfo, container, false);
        infoRecyclerView = binding.myinfoRecyclerView;
        infoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new LastAdapter(arrayList, BR.content)
                .map(Medicine.class, new ItemType<InfoContentBinding>(R.layout.info_content) {
                    @Override
                    public void onBind(@NotNull Holder<InfoContentBinding> holder) {
                        super.onBind(holder);

                    }
                })
                .into(infoRecyclerView);
        return binding.getRoot();
    }

    private void setData() {
        arrayList.add(new Medicine());
        arrayList.add(new Medicine());
        arrayList.add(new Medicine());
        arrayList.add(new Medicine());
        arrayList.add(new Medicine());
        arrayList.add(new Medicine());
        arrayList.add(new Medicine());
        arrayList.add(new Medicine());
    }

}
