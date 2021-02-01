package com.example.rememberme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerView> {

    private ArrayList<Player> players;
int i=0;

    public PlayerAdapter(ArrayList<Player> players) {
        this.players = players;

    }
    public class PlayerView extends RecyclerView.ViewHolder {

        TextView numberTv;
        TextView nameTv;
        TextView scoreTv;

        public PlayerView(View view) {
            super(view);
             numberTv = view.findViewById(R.id.number);
             nameTv = view.findViewById(R.id.player_name);
             scoreTv = view.findViewById(R.id.player_score);

        }
    }
    @Override
    public PlayerView onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_cell,parent,false);
        PlayerView holder  = new PlayerView(view);
i++;
        return holder;
    }

    @Override
    public void onBindViewHolder(PlayerView holder, int position) {
        Player player = players.get(position);
        holder.numberTv.setText(i+"");
        holder.nameTv.setText(player.getName());
        holder.scoreTv.setText(player.getScore()+"");
    }






    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 3;

    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}
