package com.tasfia.orphanhouse;

import android.content.Context;

public class PreferenceManager {

    private Context context;
    private final String SIGN_IN_SP_NAME = "signInData";
    public static final String USER_EMAIL_KEY = "userEmail_Key";
    public static final String USER_ID_KEY = "userId_Key";
    public static final String USER_NAME_KEY = "userName_Key";
    public static final String USER_PASSWORD_KEY = "userPassword_Key";
    private final String LOGGED_IN_SP_NAME = "isLoggedIn";
    private final String LOGGED_IN_SP_KEY = "loggedInKey";

    public PreferenceManager(Context context) {
        this.context = context;
    }

    public void setSignInData(String key, String value){
        context.getSharedPreferences(SIGN_IN_SP_NAME, Context.MODE_PRIVATE).edit().putString(key, value).apply();
    }

    public String getSignInData(String key){
        return context.getSharedPreferences(SIGN_IN_SP_NAME, Context.MODE_PRIVATE).getString(key,null);
    }

    public void setUserLoggedIn(boolean logged_In){
        context.getSharedPreferences(LOGGED_IN_SP_NAME, Context.MODE_PRIVATE).edit().putBoolean(LOGGED_IN_SP_KEY, logged_In).apply();
    }

    public boolean isUserLoggedIn(){
        return context.getSharedPreferences(LOGGED_IN_SP_NAME, Context.MODE_PRIVATE).getBoolean(LOGGED_IN_SP_KEY,false);
    }
}
