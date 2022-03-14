package at.htlkaindorf.ahme17.pusthy16.relaxsesselsteuerungv2.gui.recyclerAdapter;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import at.htlkaindorf.ahme17.pusthy16.relaxsesselsteuerungv2.R;


public class CardViewHolder extends RecyclerView.ViewHolder {

    private final CardView cardView;
    private final TextView textView;
    private final ImageView edit;
    private final ImageView imageView2;
    private CardAdapter adapter;
    private Dialog dialog;

    public CardViewHolder(@NonNull CardView cardView) {
        super(cardView);
        this.cardView = cardView;
        textView = cardView.findViewById(R.id.cardText);
        edit = cardView.findViewById(R.id.edit);
        imageView2 = cardView.findViewById(R.id.delete);
    }


    public void bindText(CharSequence text){
        textView.setText(text);
    }

    public void bindImage1(
            Drawable drawable, final int position,
            final CardAdapter adapter, final List<UserAndIcons> usersandicons){
        edit.setImageDrawable(drawable);
        edit.setOnClickListener((View view) ->
            {
                final UserAndIcons uai = usersandicons.get(position);

                AlertDialog.Builder editBuilder = new AlertDialog.Builder(edit.getContext());
                LayoutInflater inflater = editBuilder.create().getLayoutInflater();
                final View dialogView;
                editBuilder.setView(dialogView=inflater.inflate(R.layout.add_dialog,null));

                EditText username = dialogView.findViewById(R.id.user_name);
                username.setText(uai.getText());

                editBuilder.setPositiveButton(R.string.positive_button, (DialogInterface dialogInterface, int i) ->
                {
                    final UserAndIcons newUserAndIcons =
                      new UserAndIcons(username.getText().toString(),
                        uai.getImage(), uai.getImage2());
                    usersandicons.set(position, newUserAndIcons);
                    adapter.notifyItemChanged(position);
                    dialog.cancel();
                });

                editBuilder.setNegativeButton(R.string.negative_button, (DialogInterface dialogInterface, int i) ->
                {
                    dialog.cancel();
                });

                dialog = editBuilder.show();
        });
    }

    public void bindImage2(Drawable drawable){
        imageView2.setImageDrawable(drawable);
    }

    public CardView getCardView() {
        return cardView;
    }


}
