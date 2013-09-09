package com.dailymiler.infra.oauth;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.exception.*;

import java.io.UnsupportedEncodingException;

public class OauthHelper {
    private OAuthConsumer mConsumer;
    private OAuthProvider mProvider;
    private String mCallbackUrl;

    public OauthHelper(String consumerKey, String consumerSecret, String callbackUrl)throws UnsupportedEncodingException {
        mConsumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
        mProvider = new CommonsHttpOAuthProvider("https://api.dailymile.com/oauth/authorize?response_type=code",
                "https://api.dailymile.com/oauth/token",
                "https://api.dailymile.com/oauth/token");
        mProvider.setOAuth10a(true);
        mCallbackUrl = (callbackUrl == null ? OAuth.OUT_OF_BAND : callbackUrl);
    }

    public String getRequestToken()
    throws OAuthMessageSignerException, OAuthNotAuthorizedException,
            OAuthExpectationFailedException, OAuthCommunicationException {
        return mProvider.retrieveRequestToken(mConsumer,mCallbackUrl);
    }

}
