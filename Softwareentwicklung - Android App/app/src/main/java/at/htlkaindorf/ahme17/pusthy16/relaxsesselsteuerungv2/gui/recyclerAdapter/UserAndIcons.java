package at.htlkaindorf.ahme17.pusthy16.relaxsesselsteuerungv2.gui.recyclerAdapter;

import android.graphics.drawable.Drawable;

public class UserAndIcons {

    //CharSequence --> Abfolge von Zeichen
    private final CharSequence text;
    private final Drawable image, image2;

    public UserAndIcons(CharSequence text, Drawable image, Drawable image2) {
        this.text = text;
        this.image = image;
        this.image2 = image2;
    }

    public CharSequence getText() {
        return text;
    }

    public Drawable getImage() {
        return image;
    }

    public Drawable getImage2() {
        return image2;
    }
}
