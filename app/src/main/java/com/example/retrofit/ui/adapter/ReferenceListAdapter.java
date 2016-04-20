package com.example.retrofit.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.retrofit.R;
import com.example.retrofit.ui.ReferenceListFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * @author NAGARAJ
 */
public class ReferenceListAdapter extends RecyclerView.Adapter<ReferenceListAdapter.ViewHolder> {

    private final List<String> values;
    private final OnListFragmentInteractionListener listener;

    public ReferenceListAdapter(List<String> items, OnListFragmentInteractionListener listener) {
        values = items;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_reference_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.link.setText(values.get(position));

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    // listener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView link;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            link = (TextView) view.findViewById(R.id.link);
        }


    }
}
