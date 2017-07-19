package one.kafuuchino.piller.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

import one.kafuuchino.piller.MainActivity;
import one.kafuuchino.piller.R;
import one.kafuuchino.piller.databinding.FragmentLoginBinding;
import one.kafuuchino.piller.models.User;
import one.kafuuchino.piller.utils.CredentialsManager;
import one.kafuuchino.piller.utils.NetworkHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Junseok Oh on 2017-07-18.
 */

public class LoginFragment extends Fragment {
    LoginButton loginButton;
    CallbackManager callbackManager;
    FragmentLoginBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        setDefault();
        return binding.getRoot();
    }

    private void setDefault() {
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.emailInput.getText().toString().trim().equals("") || binding.passwordInput.getText().toString().trim().equals(""))
                    Toast.makeText(getContext(), "빈칸 없이 입력해주세요", Toast.LENGTH_SHORT).show();
                else {
                    NetworkHelper.getNetworkInstance().login(
                            binding.emailInput.getText().toString().trim(),
                            binding.passwordInput.getText().toString().trim()
                    ).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            switch (response.code()) {
                                case 200:
                                    CredentialsManager.getInstance().saveUserInfo(response.body());
                                    Toast.makeText(getContext(), response.body().getName() + " 님 환영합니다!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getContext(), MainActivity.class));
                                    getActivity().finish();
                                    break;
                                case 401:
                                    Toast.makeText(getContext(), "아이디 혹은 비밀번호가 잘못되었습니다!", Toast.LENGTH_SHORT).show();
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
        });
    }


    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
