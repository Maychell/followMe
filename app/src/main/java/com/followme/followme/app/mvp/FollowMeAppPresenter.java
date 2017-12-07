package com.followme.followme.app.mvp;

import android.text.TextUtils;

import com.followme.followme.utils.Constants;
import com.followme.followme.app.FollowMeApp;
import com.followme.followme.models.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.gson.JsonObject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by maychellfernandesdeoliveira on 01/12/2017.
 */

public class FollowMeAppPresenter {

    private final String TOKEN = "auth_token";

    private FollowMeApp mView;
    private FollowMeAppModel mModel;

    public FollowMeAppPresenter(FollowMeApp view, FollowMeAppModel model) {
        mView = view;
        mModel = model;
    }

    public void create() {
        checkLoggedUser();
    }

    private void checkLoggedUser() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(mView.getContext());
        if (account == null) return;

        setGoogleAccount(account);
        String token = FollowMeApp.prefs.getString(Constants.SharedPreferences.HEADER_TOKEN);
        if (token == null || TextUtils.isEmpty(token)) loadTokenService(account.getIdToken());
    }

    private void setGoogleAccount(GoogleSignInAccount account) {
        User user = new User(account.getDisplayName(), account.getEmail());
        if (account.getPhotoUrl() != null) user.setImagePath(account.getPhotoUrl().getPath());
        mView.setUser(user);
    }

    private void loadTokenService(String idToken) {
        mModel.signInAPI(idToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JsonObject>() {
                    @Override
                    public void onCompleted() { }

                    @Override
                    public void onError(Throwable e) {
                        mView.showMessage("Não foi possível fazer login com o servidor");
                    }

                    @Override
                    public void onNext(JsonObject response) {
                        if (response != null && response.has(TOKEN)) {
                            String token = response.get(TOKEN).getAsString();
                            FollowMeApp.prefs.setValue(Constants.SharedPreferences.HEADER_TOKEN, token);
                            mView.showMessage("Usuário logado com o servidor");
                        }
                    }
                });
    }
}
