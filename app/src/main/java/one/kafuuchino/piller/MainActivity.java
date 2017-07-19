package one.kafuuchino.piller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.MenuItem;
import android.view.ViewGroup;

import one.kafuuchino.piller.databinding.ActivityMainBinding;
import one.kafuuchino.piller.fragment.FindMedicFragment;
import one.kafuuchino.piller.fragment.HomeFragment;
import one.kafuuchino.piller.fragment.MyInfoFragment;
import one.kafuuchino.piller.fragment.SettingsFragment;
import one.kafuuchino.piller.views.ControllableViewPager;


public class MainActivity extends BaseActivity {

    ControllableViewPager mainPager;
    ActivityMainBinding binding;
    PillerPagerAdapter adapter;

    @Override
    protected void setDefault() {
        binding = (ActivityMainBinding) baseBinding;
        mainPager = binding.mainPager;
        disableToggle();
        setToolbarTitle("내 피드");
        adapter = new PillerPagerAdapter(getSupportFragmentManager());
        binding.mainPager.setAdapter(adapter);
        binding.bottomBar.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.main_home:
                                mainPager.setCurrentItem(0);
                                setToolbarTitle("내 피드");
                                break;
                            case R.id.main_findpharmacy:
                                mainPager.setCurrentItem(1);
                                setToolbarTitle("약국 찾기");
                                break;
                            case R.id.main_myinfo:
                                mainPager.setCurrentItem(2);
                                setToolbarTitle("내 정보");
                                break;
                            case R.id.main_settings:
                                mainPager.setCurrentItem(3);
                                setToolbarTitle("설정");
                                break;
                        }
                        return true;
                    }
                });
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return R.id.toolbar;
    }

    private class PillerPagerAdapter extends FragmentStatePagerAdapter {

        public PillerPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return HomeFragment.create(position);
                case 1:
                    return FindMedicFragment.create(position);
                case 2:
                    return MyInfoFragment.create(position);
                case 3:
                    return SettingsFragment.create(position);
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = (Fragment) adapter.instantiateItem(mainPager, 1);
        fragment.onActivityResult(requestCode, resultCode, data);
    }
}
