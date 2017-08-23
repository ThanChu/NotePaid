package com.example.thancv.notepaid.ui.main;

import com.example.thancv.notepaid.base.ViewBase;
import com.example.thancv.notepaid.model.PaidModel;

import java.util.List;

/**
 * Created by ThanCV on 8/22/2017.
 */

public interface MainView extends ViewBase {
    void loadData(List<PaidModel> list);

    void insert(PaidModel model);

    void delete(PaidModel model);

    void setTotalPaid(String totalPaid);
}
