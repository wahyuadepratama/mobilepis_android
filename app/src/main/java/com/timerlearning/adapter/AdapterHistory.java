package com.timerlearning.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.timerlearning.R;
import com.timerlearning.model.History;

import java.util.ArrayList;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.ListHistory> implements Filterable {

    // ----------- Declaration Variable
    private ArrayList<History> history;
    private ArrayList<History> historyFiltered;
    private OnHistoryItemClicked clickHandler;
    // -------------------------------------------

    public AdapterHistory(ArrayList<History> history) {
        this.history = history;
        this.historyFiltered = history;
    }

    public void setHistory(ArrayList<History> history) {
        this.history = history;
        this.historyFiltered = history;
        notifyDataSetChanged();
    }

    @Override
    public ListHistory onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_history, parent, false);
        return new ListHistory(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListHistory myViewHolder, int i) {

        History historyItem = historyFiltered.get(i);
        myViewHolder.title.setText(historyItem.getTitle());
        myViewHolder.date.setText(historyItem.getDate());
        myViewHolder.body.setText(historyItem.getBody());

        if (historyItem.getStatus().equals("on")){
            myViewHolder.photo.setImageResource(R.drawable.on);
//            String url = Constants.BASE_API + "images/on.png";
//            Glide.with(myViewHolder.itemView)
//                    .load(url)
//                    .apply(new RequestOptions().override(60, 60))
//                    .into(myViewHolder.photo);
        }

        if (historyItem.getStatus().equals("off")){
            myViewHolder.photo.setImageResource(R.drawable.off);
        }
    }


    @Override
    public int getItemCount() {
        return historyFiltered.size();
    }

    class ListHistory extends RecyclerView.ViewHolder{

        TextView title;
        TextView date;
        TextView body;
        ImageView photo;

        ListHistory(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_item_title);
            date = itemView.findViewById(R.id.tv_item_date);
            photo = itemView.findViewById(R.id.img_item_photo);
            body = itemView.findViewById(R.id.tv_item_body);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    History m = historyFiltered.get(position);
                    clickHandler.historyItemClicked(m);
                }
            });
        }
    }

    public void setClickHandler(OnHistoryItemClicked clickHandler) {
        this.clickHandler = clickHandler;
    }

    public interface OnHistoryItemClicked{
        void historyItemClicked(History m);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    historyFiltered = history;
                } else {
                    ArrayList<History> filteredList = new ArrayList<>();
                    for (History row : history) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase()) || row.getDate().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    historyFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = historyFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                historyFiltered = (ArrayList<History>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
