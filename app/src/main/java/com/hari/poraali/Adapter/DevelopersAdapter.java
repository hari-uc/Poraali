package com.hari.poraali.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
//import com.example.myapplication.R;
import com.hari.poraali.Model.DeveloperModel;
import com.hari.poraali.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class DevelopersAdapter extends FirebaseRecyclerAdapter<DeveloperModel,DevelopersAdapter.myViewHolder> {



    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public DevelopersAdapter(@NonNull FirebaseRecyclerOptions<DeveloperModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull DeveloperModel model) {
        holder.titletxt.setText(model.getName());
        holder.roletxt.setText(model.getRole());

        Glide.with(holder.img.getContext())
                .load(model.getImg())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);



    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.developers_row_template,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView titletxt,roletxt;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.imgfire);
            titletxt = (TextView) itemView.findViewById(R.id.titlefire);
            roletxt = (TextView)itemView.findViewById(R.id.roleinfo);


        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }



}


