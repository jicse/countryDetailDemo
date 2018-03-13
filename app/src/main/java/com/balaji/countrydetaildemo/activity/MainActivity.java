package com.balaji.countrydetaildemo.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
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

public class MainActivity extends BaseActivity implements CountryDetailMVP, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycler_view)
    RecyclerView countryDataList;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.error_layout)
    LinearLayout errorLayout;

    @BindView(R.id.error_text)
    TextView errorText;

    @Inject
    CountryDetailPresenter presenter;

    private Country countryData;
    private CountryDetailAdapter countryDetailAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getComponent().inject(MainActivity.this);
        ButterKnife.bind(this);

        presenter.setView(this);
        presenter.getCountryDataList();

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
        errorLayout.setVisibility(View.VISIBLE);
        errorText.setText(AppUtils.formNetworkErrorText(this,appErrorMessage));
    }


    @Override
    public void setCountryData(Country countryData) {
        countryDataList.setVisibility(View.VISIBLE);
        errorLayout.setVisibility(View.GONE);
        this.countryData = countryData;
        if (countryDetailAdapter == null) {
            initCountryDataListView();
        } else {
            countryDetailAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Method to initialize the country data adapter.
     */
    public void initCountryDataListView() {
        countryDetailAdapter = new CountryDetailAdapter(getApplicationContext(), countryData, new CountryDetailAdapter.OnItemClickListener() {
            @Override
            public void onClick(Row Item) {
                Toast.makeText(MainActivity.this, "item click", Toast.LENGTH_SHORT).show();
            }
        });
        countryDataList.setLayoutManager(new LinearLayoutManager(this));
        countryDataList.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        this.countryDataList.setAdapter(countryDetailAdapter);
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
    @Override
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
