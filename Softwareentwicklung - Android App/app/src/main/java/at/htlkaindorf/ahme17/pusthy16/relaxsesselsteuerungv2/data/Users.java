package at.htlkaindorf.ahme17.pusthy16.relaxsesselsteuerungv2.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import at.htlkaindorf.ahme17.pusthy16.relaxsesselsteuerungv2.RssApp;

public class Users
{
    // design pattern "singleton"
    private static Users theInstance = null;
    public static Users getInstance()
    {
        if (theInstance==null)
            theInstance = new Users();
        return theInstance;
    }


    private final List<User> users = new ArrayList<>();

    private Context getContext()
    {
        return RssApp.getInstance();
    }

    private Users()
    {
        load();
    }

    public void setUsers(final List<User> userList)
    {
        users.clear();
        for (final User user : userList)
            users.add(user);
    }

    public void load()
    {
        users.clear();
        final SharedPreferences sharedPreferences =
                getContext().getSharedPreferences("User Preferences", Context.MODE_PRIVATE);
        final int cnt = sharedPreferences.getInt("users.count", 0);
        for (int i=0;i<cnt;i++)
        {
            final String name = sharedPreferences.getString("users.name."+i, "");
            if (!name.isEmpty())
                users.add(new User(name));
        }
    }

    public void save()
    {
      final SharedPreferences sharedPreferences =
          getContext().getSharedPreferences("Users", Context.MODE_PRIVATE);
      final SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putInt("users.count", users.size());
      int cnt = 0;
      for (User user : users)
        editor.putString("users.name."+(cnt++), user.getName());
      editor.apply();
    }

    public List<User> getUsers() {
        return users;
    }
}
