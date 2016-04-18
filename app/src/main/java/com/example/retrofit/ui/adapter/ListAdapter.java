package com.example.retrofit.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.retrofit.R;
import com.example.retrofit.model.Questions;
import com.example.retrofit.model.Questions.Question;

import java.util.List;

/**
 * Created by NAGARAJ on 4/18/2016.
 */
public class ListAdapter extends ArrayAdapter<Question> {
    private final LayoutInflater inflater;

    public ListAdapter(Context context, List<Question> questions) {
        super(context, 0, questions);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private static class ViewHolder {
        private TextView title, link;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.adapter_question_list_item, parent, false);
            holder.link = (TextView) convertView.findViewById(R.id.link);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Question question = getItem(position);
        holder.link.setText(question.link);
        holder.title.setText(question.title);

        return convertView;
    }
}
