package com.parallaxstudios.caregiver.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parallaxstudios.caregiver.R;
import com.parallaxstudios.caregiver.model.SettingsAnda;
import com.parallaxstudios.caregiver.utill.utill.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SettingsAndaAdapter extends RecyclerView.Adapter<SettingsAndaAdapter.ViewHolder>{

    private ArrayList<SettingsAnda> berandas;
    Context context;

    // Adapter's Constructor
    public SettingsAndaAdapter(Context context, ArrayList<SettingsAnda> designs) {
        this.berandas = designs;
        this.context = context;
    }

    // Create new views. This is invoked by the layout manager.
    @Override
    public SettingsAndaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view by inflating the row item xml.
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile, parent, false);
        
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressWarnings("deprecation")
	@Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        // Set strings to the views
        final TextView textViewTitle = (TextView) holder.view.findViewById(R.id.textViewItemTitle);
        final TextView textViewContent = (TextView) holder.view.findViewById(R.id.textViewItemContent);
        final TextView textViewGender = (TextView) holder.view.findViewById(R.id.textViewItemGender);
        final TextView textViewPhone = (TextView) holder.view.findViewById(R.id.textViewItemPhoneMain);
        final TextView textViewPassword = (TextView) holder.view.findViewById(R.id.textViewItemPasswordMain);
        final TextView textViewAddress = (TextView) holder.view.findViewById(R.id.textViewItemAddressMain);
        final TextView textViewBirthday = (TextView) holder.view.findViewById(R.id.textViewItemBirtdayMain);
        final ImageView imageViewImage = (ImageView) holder.view.findViewById(R.id.imageViewImage);
        textViewTitle.setText(berandas.get(position).getTitle());
        textViewContent.setText(berandas.get(position).getTitle());
        textViewGender.setText(berandas.get(position).getTitle());
        textViewPhone.setText(berandas.get(position).getTitle());
        textViewPassword.setText(berandas.get(position).getTitle());
        textViewAddress.setText(berandas.get(position).getTitle());
        textViewBirthday.setText(berandas.get(position).getTitle());
        Picasso.with(context).load(berandas.get(position).getImage())
                .placeholder(holder.view.getResources()
                        .getDrawable(R.drawable.ic_contact_icon)).transform(new CircleTransform()).into(imageViewImage);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return berandas.size();
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;

        public ViewHolder(View v) {
            super(v);
            view = v;
        }
    }
}
