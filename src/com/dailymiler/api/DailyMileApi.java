package com.dailymiler.api;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.model.Token;

import java.util.*;

public class DailyMileApi extends DefaultApi10a {
    private static final String AUTHORIZE_URL = "https://api.dailymile.com/oauth/authorize?response_type=code&oauth_token=%s";
  private static final String REQUEST_TOKEN_URL = "https://api.dailymile.com/oauth/token";

  private final Set<String> scopes;

  public DailyMileApi(){
    scopes = Collections.emptySet();
  }

  public DailyMileApi(Set<String> scopes){
    this.scopes = Collections.unmodifiableSet(scopes);
  }

  @Override
  public String getAccessTokenEndpoint(){
    return "https://api.linkedin.com/uas/oauth/accessToken";
  }

  @Override
  public String getRequestTokenEndpoint(){
    return scopes.isEmpty() ? REQUEST_TOKEN_URL : REQUEST_TOKEN_URL + "?scope=" + scopesAsString();
  }

  private String scopesAsString(){
    StringBuilder builder = new StringBuilder();
    for(String scope : scopes){
      builder.append("+" + scope);
    }
    return builder.substring(1);
  }

  @Override
  public String getAuthorizationUrl(Token requestToken){
    return String.format(AUTHORIZE_URL, requestToken.getToken());
  }

  public static LinkedInApi withScopes(String... scopes){
    Set<String> scopeSet = new HashSet<String>(Arrays.asList(scopes));
    return new LinkedInApi(scopeSet);
  }

}
