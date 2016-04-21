package com.example.retrofit.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.retrofit.R;
import com.example.retrofit.api.StackOverflowAPI;
import com.example.retrofit.model.Questions;
import com.example.retrofit.ui.adapter.ListAdapter;
import com.example.retrofit.utils.ServiceGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A placeholder fragment containing a simple view.
 */
public class QuestionListFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

    private ListView listView;
    private ProgressBar progressBar;
    private StackOverflowAPI api;
    private List<Questions.Question> questionList = new ArrayList<>();
    private Call<Questions> questionsCall;

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
        api = ServiceGenerator.createService(StackOverflowAPI.class);
        listView.setAdapter(new ListAdapter(getActivity(), questionList));
        RadioGroup radioGroup = (RadioGroup) getView().findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(this);
        RadioButton simpleRequest = (RadioButton) radioGroup.findViewById(R.id.request);
        simpleRequest.setChecked(true);

    }

    private void downloadQuestions(Call<Questions> questionsCall) {
        progressBar.setVisibility(View.VISIBLE);
        questionsCall.enqueue(new Callback<Questions>() {
            @Override
            public void onResponse(Call<Questions> call, Response<Questions> response) {
                if (getActivity() == null)
                    return;
                if (response.isSuccessful()) {
                    questionList.addAll(response.body().items);
                    ((ListAdapter) listView.getAdapter()).notifyDataSetChanged();
                } else {
                    // error response, no access to resource?
                }
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

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        questionList.clear();
        ((ListAdapter) listView.getAdapter()).notifyDataSetChanged();
        cancelRequest();
        switch (group.getCheckedRadioButtonId()) {
            case R.id.request:
                questionsCall = api.downloadQuestions();
                break;
            case R.id.dynamic_url:
                questionsCall = api.downloadQuestions("https://api.stackexchange.com/2.2/questions?order=desc&sort=creation&site=stackoverflow");
                break;
            case R.id.url_manipulation:
                questionsCall = api.downloadQuestions("questions", "stackoverflow");
                break;
            case R.id.query_map:
                Map<String, String> map = new HashMap<>();
                map.put("site", "stackoverflow");
                map.put("sort", "creation");
                map.put("order", "desc");
                questionsCall = api.downloadQuestions(map);
                break;
        }
        downloadQuestions(questionsCall);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        cancelRequest();

    }

    private void cancelRequest() {
        if (questionsCall != null)
            questionsCall.cancel();

    }
}
