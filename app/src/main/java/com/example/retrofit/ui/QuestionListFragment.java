package com.example.retrofit.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.retrofit.R;
import com.example.retrofit.api.StackOverflowAPI;
import com.example.retrofit.model.Questions;
import com.example.retrofit.ui.adapter.ListAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A placeholder fragment containing a simple view.
 */
public class QuestionListFragment extends Fragment {

    private ListView listView;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        listView = (ListView) view.findViewById(R.id.list_view);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.stackexchange.com").addConverterFactory(GsonConverterFactory.create()).build();
        StackOverflowAPI api = retrofit.create(StackOverflowAPI.class);
        Call<Questions> questionsCall = api.downloadQuestions();
        questionsCall.enqueue(new Callback<Questions>() {
            @Override
            public void onResponse(Call<Questions> call, Response<Questions> response) {
                if (getActivity() == null)
                    return;
                listView.setAdapter(new ListAdapter(getActivity(),response.body().items));
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<Questions> call, Throwable t) {
                if (getActivity() == null)
                    return;
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
