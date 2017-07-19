package one.kafuuchino.piller;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import one.kafuuchino.piller.databinding.ActivityAuthBinding;
import one.kafuuchino.piller.fragment.LoginFragment;
import one.kafuuchino.piller.fragment.RegisterFragment;
import one.kafuuchino.piller.models.User;
import one.kafuuchino.piller.utils.CredentialsManager;
import one.kafuuchino.piller.utils.NetworkHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends BaseActivity {

    ActivityAuthBinding binding;
    ViewPager bottomPager;
    BottomPagerAdapter adapter;

    @Override
    protected void setDefault() {
        binding = (ActivityAuthBinding) baseBinding;

        bottomPager = binding.viewPager;
        adapter = new BottomPagerAdapter(getSupportFragmentManager());
        bottomPager.setAdapter(adapter);
        binding.authTabLayout.setupWithViewPager(binding.viewPager);
        if (CredentialsManager.getInstance().getActiveUser().first) {
            Log.e("asdf", CredentialsManager.getInstance().getActiveUser().first + "");
            Log.e("asdf", CredentialsManager.getInstance().getActiveUser().second.getToken());

            NetworkHelper.getNetworkInstance().authenticateLocal(CredentialsManager.getInstance().getActiveUser().second.getToken()).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    switch (response.code()) {
                        case 200:
                            CredentialsManager.getInstance().saveUserInfo(response.body());
                            Toast.makeText(getApplicationContext(), response.body().getName() + " 님 환영합니다!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                            break;
                        default:
                            break;
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e("asdf", t.getLocalizedMessage());
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = (Fragment) adapter.instantiateItem(bottomPager, 1);
        fragment.onActivityResult(requestCode, resultCode, data);
    }

    public void switchPage(int position) {
        if (position > adapter.getCount()) return;
        bottomPager.setCurrentItem(position);
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_auth;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return 0;
    }

    class BottomPagerAdapter extends FragmentStatePagerAdapter {

        public BottomPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return (position == 0) ? new RegisterFragment() : new LoginFragment();
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return (position == 0) ? "회원가입" : "로그인";
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }
    }
}
