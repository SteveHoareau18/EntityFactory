package fr.steve.entityfactory;

import android.app.Activity;
import android.content.SharedPreferences;

import java.util.Map;

public class SharedPreferenceActivity extends Activity {

    private static SharedPreferenceActivity globalActivity;
    private static String name;
    private static int mode;

    public SharedPreferenceActivity(String _name, int _mode){
        globalActivity = this;
        name = _name;
        mode = _mode;
    }

    public static String getString(String k, String v) {
        return globalActivity.getSharedPreferences(name,mode).getString(k, v);
    }

    public static SharedPreferences.Editor edit() {
        return globalActivity.getSharedPreferences(name,mode).edit();
    }

    public static Map<String, ?> getAll() {
        return globalActivity.getSharedPreferences(name,mode).getAll();
    }
}
