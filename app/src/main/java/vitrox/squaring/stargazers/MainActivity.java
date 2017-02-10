package vitrox.squaring.stargazers;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import retrofit.HttpException;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vitrox.squaring.stargazers.Adapters.StargazersAdapter;
import vitrox.squaring.stargazers.Common.Config;
import vitrox.squaring.stargazers.Common.Utils;
import vitrox.squaring.stargazers.Model.GitResponse;
import vitrox.squaring.stargazers.Network.ApiService;

public class MainActivity extends BaseActivity {

    private EditText userEditText;
    private EditText repoEditText;
    private LinearLayoutManager mLayoutManager;
    private ProgressBar progressBar;
    private Boolean mIsLoading;
    private RecyclerView mRecyclerView;
    @Inject
    ApiService apiService;
    @Inject
    StargazersAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getComponent().inject(this);
        Button mGoButton = (Button) findViewById(R.id.search_button);
         mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        userEditText = (EditText) findViewById(R.id.user_edittext);
        repoEditText = (EditText) findViewById(R.id.repo_edittext);
        progressBar = (ProgressBar) findViewById(R.id.spinner);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setVisibility(View.GONE);
        mGoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!userEditText.getText().toString().isEmpty() && !repoEditText.getText().toString().isEmpty()) {
                    Utils.hideSoftKeyboard(MainActivity.this);
                    mRecyclerView.setVisibility(View.GONE);
                    search(userEditText.getText().toString(), repoEditText.getText().toString());
                } else {
                    sendErrorMessage(Config.EMPTY_ERROR);
                }
            }
        });
    }

    private void search(String user, String repo) {
        loading(true);
        apiService.getJsonStargazers(user, repo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<List<GitResponse>>() {
                    @Override
                    public void onCompleted() {
                        loading(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loading(false);
                        mAdapter.resetData();
                        if (e instanceof retrofit.HttpException) {
                            HttpException exception = (HttpException) e;
                            if (exception.code() == Config.ERROR_CODE) {
                                sendErrorMessage(Config.WRONG_ERROR);
                            } else {
                                sendErrorMessage(exception.getMessage());

                            }
                        }
                    }

                    @Override
                    public void onNext(List<GitResponse> gitResponses) {
                        mAdapter.addData(gitResponses);
                        mRecyclerView.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void loading(Boolean b) {
        mIsLoading = b;
        if (mIsLoading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void sendErrorMessage(final String txt) {
        //Show errors always on main thread
        this.runOnUiThread(new Runnable() {
            public void run() {
                Snackbar.make(findViewById(android.R.id.content), txt, Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
