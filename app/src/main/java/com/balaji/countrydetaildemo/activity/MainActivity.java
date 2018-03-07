package com.balaji.countrydetaildemo.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.balaji.countrydetaildemo.R;
import com.balaji.countrydetaildemo.adapter.CountryDetailAdapter;
import com.balaji.countrydetaildemo.model.Country;
import com.balaji.countrydetaildemo.model.Row;
import com.balaji.countrydetaildemo.mvp.CountryDetailMVP;
import com.balaji.countrydetaildemo.presenter.CountryDetailPresenter;
import com.balaji.countrydetaildemo.utils.AppUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements CountryDetailMVP,SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycler_view)
    RecyclerView countryDataList;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.error_text)
    TextView errorText;

    @Inject
    CountryDetailPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getComponent().inject(MainActivity.this);
        ButterKnife.bind(this);

        presenter.setView(this);
        presenter.getCountryDataList();

        countryDataList.setLayoutManager(new LinearLayoutManager(this));
        countryDataList.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        swipeRefreshLayout.setOnRefreshListener(this);

    }

    @Override
    public void showLoader() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoader() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFailure(String appErrorMessage) {
        hideLoader();
        errorText.setText(AppUtils.formNetworkErrorText(appErrorMessage));
    }

    @Override
    public void setCountryData(Country countryData) {
        hideLoader();
        setTitle(countryData.getTitle());
        countryDataList.setVisibility(View.VISIBLE);
        AppUtils.removeEmptyData(countryData);
        CountryDetailAdapter adapter = new CountryDetailAdapter(getApplicationContext(), countryData, new CountryDetailAdapter.OnItemClickListener() {
            @Override
            public void onClick(Row Item) {

            }
        });

        this.countryDataList.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onStop();
    }

    /**
     * Method to set title for the action bar.
     *
     * @param title
     */
    public void setTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void onRefresh() {
        presenter.getCountryDataList();
    }
}
