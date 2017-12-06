package com.followme.followme.views.fragments.profile;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.followme.followme.models.User;
import com.followme.followme.views.fragments.profile.mvp.ProfileModel;
import com.followme.followme.views.fragments.profile.mvp.ProfilePresenter;
import com.followme.followme.views.fragments.profile.mvp.ProfileView;
import com.followme.followme.helper.RxSchedulersOverrideRule;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import rx.Observable;

/**
 * Created by MaychellFernandes on 04/12/17.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ TextUtils.class, GoogleSignIn.class })
public class ProfilePresenterTest {

    private static final int RC_SIGN_IN = 1000;

    @Rule RxSchedulersOverrideRule schedulersOverrideRule = new RxSchedulersOverrideRule();

    private ProfilePresenter mPresenter;
    @Mock private ProfileModel mModel;
    @Mock private ProfileView mView;
    @Mock private User mUser;
    @Mock private GoogleSignInClient mGoogleSignInClient;
    @Mock private Context mContext;
    @Mock private Intent intent;
    private GoogleSignInOptions mGoogleSignInOptions;

    @Before
    public void setUp() {
        mockTextUtils();
        mGoogleSignInOptions = Mockito.mock(GoogleSignInOptions.class);
        Mockito.when(mView.getContext()).thenReturn(mContext);
        mockGoogleSignIn();

        mPresenter = new ProfilePresenter(mModel, mView, mUser, mGoogleSignInOptions);
        Mockito.when(mView.observeLoginButton()).thenReturn(Observable.never());
    }

    private void mockTextUtils() {
        PowerMockito.mockStatic(TextUtils.class);
        PowerMockito.when(TextUtils.isEmpty(Matchers.any(CharSequence.class)))
                .thenAnswer(invocation -> {
                    CharSequence a = (CharSequence) invocation.getArguments()[0];
                    return !(a != null && a.length() > 0);
                });
    }

    private void mockGoogleSignIn() {
        PowerMockito.mockStatic(GoogleSignIn.class);
        PowerMockito.when(GoogleSignIn.getClient(mContext, mGoogleSignInOptions))
                .thenReturn(mGoogleSignInClient);
    }

    @Test
    public void observeBtnLoginPressed() {
        Mockito.when(mView.observeLoginButton()).thenReturn(Observable.just(null));
        Mockito.when(mGoogleSignInClient.getSignInIntent()).thenReturn(intent);

        mPresenter.create();
        Mockito.verify(mView, Mockito.times(1)).openActivityForResult(intent, RC_SIGN_IN);
    }

    @Test
    public void setUpViewUserLogged() {
        mPresenter.create();
        Mockito.verify(mView, Mockito.times(1)).hideSignInButton();
        Mockito.verify(mView, Mockito.times(1)).displayLoggedUser(mUser);
    }

    @Test
    public void setUpViewUserNotLogged() {
        mPresenter = new ProfilePresenter(mModel, mView, null, mGoogleSignInOptions);

        mPresenter.create();
        Mockito.verify(mView, Mockito.never()).hideSignInButton();
        Mockito.verify(mView, Mockito.never()).displayLoggedUser(Matchers.any());
    }
}
