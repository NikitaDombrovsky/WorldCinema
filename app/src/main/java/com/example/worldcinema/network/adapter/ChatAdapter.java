package com.example.worldcinema.network.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldcinema.R;
import com.example.worldcinema.network.models.Chat.ChatResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder>{

    private ArrayList<ChatResponse> chatResponses;
    private LayoutInflater inflater;
    private Context context;
    public ChatAdapter(ArrayList<ChatResponse> chatResponse, Context context){
        this.chatResponses = chatResponse;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }
    @NonNull
    @Override
    public ChatAdapter.ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        context = parent.getContext();
        View view = inflater.inflate(R.layout.discussion_item, parent, false);
        return new ChatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {
        ChatResponse chatResponse = chatResponses.get(position);
        holder.setTextChat(chatResponse.getName());

    }
    @Override
    public int getItemCount() {
        return chatResponses.size();
    }
    public class ChatHolder extends RecyclerView.ViewHolder{
        final private TextView Discussion_Text_Name;
        final private TextView Discussion_Text;

        private ChatHolder(View view) {
            super(view);
            this.Discussion_Text_Name =  (TextView) view.findViewById(R.id.Profile_Text_Name);
            this.Discussion_Text =  (TextView) view.findViewById(R.id.Discussion_Text);
        }

        public void setTextChat(String text) {
            this.Discussion_Text_Name.setText(text);
            this.Discussion_Text.setText(text);
        }
    }

}
