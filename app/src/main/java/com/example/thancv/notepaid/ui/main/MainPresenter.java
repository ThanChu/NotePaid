package com.example.thancv.notepaid.ui.main;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thancv.notepaid.R;
import com.example.thancv.notepaid.base.PresenterBase;
import com.example.thancv.notepaid.database.CRUDConfigurationManager;
import com.example.thancv.notepaid.database.DatabaseHandler;
import com.example.thancv.notepaid.enums.DialogType;
import com.example.thancv.notepaid.model.PaidModel;
import com.example.thancv.notepaid.utils.TextUtils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThanCV on 8/22/2017.
 */

public class MainPresenter implements PresenterBase<MainView> {

    private MainView mainView;
    private Context context;
    private CRUDConfigurationManager crudManager;

    public MainPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(MainView mainView) {
        this.mainView = mainView;
        DatabaseHandler databaseHandler = new DatabaseHandler(context);
        crudManager = new CRUDConfigurationManager(databaseHandler);
    }

    @Override
    public void onStart() {
        List<PaidModel> list = crudManager.getAllObjects();
        mainView.loadData(list);
        mainView.setTotalPaid(TextUtils.formatText(calculateTotal(list)));
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    private long calculateTotal(List<PaidModel> list) {
        long sum = 0;
        for (PaidModel model : list) {
            sum += Long.parseLong(model.getMoneyPaid());
        }
        return sum;
    }

    public void showDialogNotify() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_notify);

        Button dialogButtonOk = dialog.findViewById(R.id.btn_ok);
        Button dialogButtonCancel = dialog.findViewById(R.id.btn_cancel);
        final EditText edtTitleName = dialog.findViewById(R.id.edt_title_name);
        final EditText edtMoneyPaid = dialog.findViewById(R.id.edt_money_paid);


        dialogButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleName = edtTitleName.getText().toString().trim();
                String moneyPaid = edtMoneyPaid.getText().toString().trim();
                PaidModel model = new PaidModel(titleName, moneyPaid);
                if (StringUtils.isEmpty(titleName) || StringUtils.isEmpty(moneyPaid)) {
                    Toast.makeText(context, "Please input all field", Toast.LENGTH_SHORT).show();
                    return;
                }
                crudManager.insertNewObject(model);
                mainView.insert(model);

                List<PaidModel> list = crudManager.getAllObjects();
                mainView.loadData(list);
                mainView.setTotalPaid(TextUtils.formatText(calculateTotal(list)));

                dialog.dismiss();
            }
        });
        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void showDialogConfirm(final DialogType dialogType, final PaidModel paidModel) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_confirm);

        TextView tvMessage = dialog.findViewById(R.id.tv_message);
        Button dialogButtonOk = dialog.findViewById(R.id.btn_ok);
        Button dialogButtonCancel = dialog.findViewById(R.id.btn_cancel);

        if (DialogType.DIALOG_RESET == dialogType) {
            tvMessage.setText("Do you want to reset data?");
        } else {
            tvMessage.setText("Do you want to delete this row?\n"
                    + paidModel.getTitleName() + ": " + paidModel.getMoneyPaid());
        }

        dialogButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DialogType.DIALOG_RESET == dialogType) {
                    crudManager.deleteAll();
                    mainView.loadData(new ArrayList<PaidModel>());
                    mainView.setTotalPaid("0");
                } else {
                    crudManager.deleteObject(paidModel);
                    mainView.delete(paidModel);

                    List<PaidModel> list = crudManager.getAllObjects();
                    mainView.setTotalPaid(TextUtils.formatText(calculateTotal(list)));
                }
                dialog.dismiss();
            }
        });
        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
