package at.htlkaindorf.ahme17.pusthy16.relaxsesselsteuerungv2;

import android.app.Application;

public class RssApp extends Application
{
    private static RssApp theInstance = null;
    public static RssApp getInstance()
    {
        return theInstance;
    }

    public RssApp()
    {
        theInstance = this;
    }
}
