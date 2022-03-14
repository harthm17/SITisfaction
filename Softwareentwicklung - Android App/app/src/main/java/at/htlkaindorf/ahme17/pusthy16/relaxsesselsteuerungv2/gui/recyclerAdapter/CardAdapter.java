package at.htlkaindorf.ahme17.pusthy16.relaxsesselsteuerungv2.gui.recyclerAdapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import at.htlkaindorf.ahme17.pusthy16.relaxsesselsteuerungv2.R;
import at.htlkaindorf.ahme17.pusthy16.relaxsesselsteuerungv2.data.User;
import at.htlkaindorf.ahme17.pusthy16.relaxsesselsteuerungv2.data.Users;


public class CardAdapter extends RecyclerView.Adapter<CardViewHolder>{


    private final List<UserAndIcons> usersandicons = new ArrayList<>();


    public void add(CharSequence text, Drawable image, Drawable image2){
        usersandicons.add(new UserAndIcons(text, image, image2));
    }

    public void add(CharSequence text){
        add(text,null, null);
    }

    public void add(CharSequence text, Drawable image){
        add(text, image, null);
    }

    public void remove(int position){
        usersandicons.remove(position);
    }

    public void getUsername(int position){
        usersandicons.get(position).toString();
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);
        return new CardViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        final UserAndIcons userAndIcons = usersandicons.get(position);
        holder.bindText(userAndIcons.getText());
        holder.bindImage1(userAndIcons.getImage(), position, this, usersandicons);
        holder.bindImage2(userAndIcons.getImage2());
    }

    @Override
    public int getItemCount() {
        return usersandicons.size();
    }

    public UserAndIcons getUser(int position){
        return usersandicons.get(position);
    }


    public void move(int fromPos, int toPos){
        final UserAndIcons uaiFrom = usersandicons.get(fromPos);
        final UserAndIcons uaiTo = usersandicons.get(toPos);

        usersandicons.set(fromPos, uaiTo);
        usersandicons.set(toPos, uaiFrom);
    }

    public void save()
    {
        final List<User> users = new ArrayList<>();
        for(UserAndIcons userAndIcons : usersandicons)
             users.add(new User(userAndIcons.getText().toString()));
        Users.getInstance().setUsers(users);
        Users.getInstance().save();
    }

}
