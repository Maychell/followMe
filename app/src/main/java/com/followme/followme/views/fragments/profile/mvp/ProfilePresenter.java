package com.followme.followme.views.fragments.profile.mvp;

import android.content.Intent;

import com.followme.followme.app.FollowMeApp;
import com.followme.followme.models.User;
import com.followme.followme.utils.Constants;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.JsonObject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by maychellfernandesdeoliveira on 04/12/2017.
 */

public class ProfilePresenter {

    private static final int RC_SIGN_IN = 1000;
    private static final String TOKEN = "auth_token";

    private final ProfileModel mModel;
    private final ProfileView mView;
    private final User mUser;
    private GoogleSignInClient mGoogleSignInClient;
    private final CompositeSubscription compositeSubscription = new CompositeSubscription();

    public ProfilePresenter(ProfileModel model, ProfileView view, User user, GoogleSignInOptions gso) {
        mModel = model;
        mView = view;
        mUser = user;
        mGoogleSignInClient = GoogleSignIn.getClient(mView.getContext(), gso);
    }

    public void create() {
        attachView();
        setUpView();
    }

    private void attachView() {
        compositeSubscription.add(observeBtnLogin());
    }

    public void detachView() {
        compositeSubscription.clear();
    }

    private Subscription observeBtnLogin() {
        return mView.observeLoginButton().subscribe(__ -> openSignInActivity());
    }

    private void setUpView() {
        if (mUser == null) return;
        mView.hideSignInButton();
        mView.displayLoggedUser(mUser);
    }

    private void openSignInActivity() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        mView.openActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void onActivityResult(int requestCode, Intent data) {
        if (requestCode != RC_SIGN_IN) return;
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        handleSignInResult(task);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            if (account == null) return;
            displayLoggedUser(account);
            loadTokenService(account.getIdToken());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private void loadTokenService(String idToken) {
        mModel.signInAPI(idToken)
                .doOnNext(__ -> mView.showProgress())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEach(__ -> mView.hideProgress())
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

    private void displayLoggedUser(GoogleSignInAccount account) {
        User user = new User(account.getDisplayName(), account.getEmail());
        if (account.getPhotoUrl() != null) user.setImagePath(account.getPhotoUrl().getPath());

        mView.hideSignInButton();
        mView.displayLoggedUser(user);
    }
}
