package com.example.thancv.notepaid.ui.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.thancv.notepaid.R;
import com.example.thancv.notepaid.adapter.PaidListAdapter;
import com.example.thancv.notepaid.enums.DialogType;
import com.example.thancv.notepaid.model.PaidModel;
import com.example.thancv.notepaid.utils.TextUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        MainView , PaidListAdapter.OnLongClickListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private TextView tvTotalPaid;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private NavigationView navigationView;

    private PaidListAdapter paidListAdapter;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        setEventListener();

        initVariable();
    }


    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        tvTotalPaid = (TextView) findViewById(R.id.tv_total);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        recyclerView = (RecyclerView) findViewById(R.id.rcl_main);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
    }

    private void initVariable() {
        paidListAdapter = new PaidListAdapter();
        mainPresenter = new MainPresenter(this);
        mainPresenter.onCreate(this);
        paidListAdapter.setOnLongClickListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(paidListAdapter);
    }

    private void setEventListener() {
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.showDialogNotify();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.onStart();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.nav_reset:
                mainPresenter.showDialogConfirm(DialogType.DIALOG_RESET, null);
                break;
            default:
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void loadData(List<PaidModel> list) {
        paidListAdapter.loadData(list);
    }

    @Override
    public void insert(PaidModel model) {
        paidListAdapter.addNewItem(model);
    }

    @Override
    public void delete(PaidModel model) {
        paidListAdapter.deleteItem(model);
    }

    @Override
    public void setTotalPaid(String totalPaid) {
        tvTotalPaid.setText(totalPaid);
    }

    @Override
    public void onLongClick(PaidModel paidModel) {
        mainPresenter.showDialogConfirm(DialogType.DIALOG_DELETE, paidModel);
    }
}
