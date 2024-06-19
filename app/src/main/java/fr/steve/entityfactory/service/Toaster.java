package fr.steve.entityfactory.service;

import android.app.Activity;
import android.widget.Toast;

public class Toaster {

    private final Activity activity;

    public Toaster(Activity activity) {
        this.activity = activity;
    }

    //ERROR

    //LOGIN
    public Toast ERROR_LOGIN() {
        return Toast.makeText(this.activity, "Une erreur est survenue...", Toast.LENGTH_LONG);
    }


    //SUCCESS

    //LOGIN
    public Toast SUCCESS_LOGIN() {
        return Toast.makeText(this.activity, "Vous êtes connecté !", Toast.LENGTH_SHORT);
    }
}
