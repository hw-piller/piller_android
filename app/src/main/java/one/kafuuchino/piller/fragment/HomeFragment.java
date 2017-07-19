package one.kafuuchino.piller.fragment;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.databinding.library.baseAdapters.BR;
import com.github.nitrico.lastadapter.Holder;
import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import one.kafuuchino.piller.R;
import one.kafuuchino.piller.databinding.FragmentHomeBinding;
import one.kafuuchino.piller.databinding.MainFeedContentBinding;
import one.kafuuchino.piller.models.Medicine;
import one.kafuuchino.piller.utils.CredentialsManager;
import one.kafuuchino.piller.utils.NetworkHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Junseok Oh on 2017-04-09.
 */

public class HomeFragment extends Fragment {
    private int CAPTURE_CODE = 6974;
    private LastAdapter mainAdapter;
    private int mPageNumber;
    private String title;
    private FragmentHomeBinding binding;
    String s = "";

    ArrayList<Medicine> arrayList = new ArrayList<>();
    LastAdapter savedCardAdapter;
    RecyclerView feed;
    private GridLayoutManager layoutManager;

    public static HomeFragment create(int pageNumber) {
        HomeFragment fragment = new HomeFragment();
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        feed = binding.mainRecyclerView;
        feed.setLayoutManager(new LinearLayoutManager(getContext()));
        setData();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getContext(), edu.sfsu.cs.orange.ocr.CaptureActivity.class), CAPTURE_CODE);
            }
        });

        return binding.getRoot();
    }

    private void setData() {
        NetworkHelper.getNetworkInstance().getMedicMyList(
                CredentialsManager.getInstance().getActiveUser().second.getToken()
        ).enqueue(new Callback<ArrayList<Medicine>>() {
            @Override
            public void onResponse(Call<ArrayList<Medicine>> call, Response<ArrayList<Medicine>> response) {
                switch (response.code()) {
                    case 200:
                        arrayList = response.body();
                        mainAdapter = new LastAdapter(arrayList, BR.content)
                                .map(Medicine.class, new ItemType<MainFeedContentBinding>(R.layout.main_feed_content) {
                                    @Override
                                    public void onBind(@NotNull Holder<MainFeedContentBinding> holder) {
                                        super.onBind(holder);
                                    }
                                })
                                .into(feed);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Medicine>> call, Throwable t) {
                Log.e("asdf", t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void onActivityResult(final int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == CAPTURE_CODE) {
            final String[] array = data.getStringArrayExtra("result");
            final ArrayList<String> arrayList = new ArrayList<>();
            for (int i = 0; i < array.length; i++) {
                new MaterialDialog.Builder(getActivity())
                        .title("코드를 확인해주세요!")
                        .input(array[i], null, false, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                                s = input.toString();

                            }
                        })
                        .positiveText("확인")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                arrayList.add(s);
                            }
                        })
                        .show();
            }
            for (String s : arrayList) {
                NetworkHelper.getNetworkInstance().getMedicineInfo(
                        CredentialsManager.getInstance().getActiveUser().second.getToken(),
                        s
                ).enqueue(new Callback<Medicine>() {
                    @Override
                    public void onResponse(Call<Medicine> call, Response<Medicine> response) {
                    }

                    @Override
                    public void onFailure(Call<Medicine> call, Throwable t) {

                    }
                });
            }
        }

    }
}
