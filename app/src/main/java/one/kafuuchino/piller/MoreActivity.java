package one.kafuuchino.piller;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.nitrico.lastadapter.Holder;
import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import one.kafuuchino.piller.databinding.ActivityMoreBinding;

public class MoreActivity extends BaseActivity {

    ActivityMoreBinding binding;
    LastAdapter adapter;
    RecyclerView moreRecyclerView;
    ArrayList<Object> arrayList = new ArrayList<>();
    @Override
    protected void setDefault() {
        binding = (ActivityMoreBinding) baseBinding;


        binding.caution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(MoreActivity.this)
                        .title("주의사항")
                        .content("asdf")
                        .positiveText("확인")
                        .show();
            }
        });
        binding.howto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(MoreActivity.this)
                        .title("투여 방법")
                        .content("asdf")
                        .positiveText("확인")
                        .show();
            }
        });
        moreRecyclerView = binding.moreRecyclerView;
        moreRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new LastAdapter(arrayList, BR.content)
                .map(Object.class, new ItemType<ViewDataBinding>(R.layout.more_content){
                    @Override
                    public void onBind(@NotNull Holder<ViewDataBinding> holder) {
                        super.onBind(holder);
                    }
                })
                .into(moreRecyclerView);

    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_more;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return R.id.toolbar;
    }
}
