package com.agoodwater.system.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.agoodwater.system.R;
import com.agoodwater.system.utils.CheckPhone;
import com.agoodwater.system.utils.CommonUtils;
import com.agoodwater.system.utils.MyToast;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by shiqiang on 2017/4/19.
 */

public class CommonEditViewDialog extends Dialog {

    static Dialog dialog;
    static TextView tv_header;
    static TextView tv_content;
    static Button btn_submit;
    private static EditText etUserName;
    private static EditText etUserPhone;
    private static String addUserName;
    private static String addUserPhone;

    public CommonEditViewDialog(Context context) {
        super(context);
    }

    public CommonEditViewDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Activity context;
        private String title;
        private int titleColor ;
        private String message;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;

        private TextView tv_title;
        private TextView tv_message;
        private Button btn_cancel;
        private Button btn_confirm;
        private CommonEditViewDialog dialog;


        public Builder(Activity context, String title,int titleColor , String message, String negativeButtonText, String positiveButtonText, OnClickListener negativeButtonClickListener, OnClickListener positiveButtonClickListener) {
            this.context = context;
            this.title=title;
            this.titleColor=titleColor;
            this.message=message;
            this.positiveButtonText=positiveButtonText;
            this.negativeButtonText=negativeButtonText;
            this.negativeButtonClickListener=negativeButtonClickListener;
            this.positiveButtonClickListener=positiveButtonClickListener;
            create().show();
        }

        public void hintDialog(){

            if (dialog!= null){
                dialog.dismiss();
            }

        }

        public Builder(Activity context) {
            this.context = context;
        }


        public String getAddUserName(){

            return addUserName;
        }
        public String getAddUserPhone(){

            return addUserPhone;
        }


        public CommonEditViewDialog.Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public CommonEditViewDialog.Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        public CommonEditViewDialog.Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        public CommonEditViewDialog.Builder setTitleColor(int titleColor) {
            this.titleColor = titleColor;
            return this;
        }

        public CommonEditViewDialog.Builder setTitle(String title) {
            this.title = title;
            return this;
        }
        public CommonEditViewDialog.Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }


        public CommonEditViewDialog.Builder setPositiveButton(int positiveButtonText, OnClickListener listener) {
            this.positiveButtonText = (String) context.getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public CommonEditViewDialog.Builder setPositiveButton(String positiveButtonText, OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }
        public CommonEditViewDialog.Builder setNegativeButton(int negativeButtonText, OnClickListener listener) {
            this.negativeButtonText = (String) context.getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public CommonEditViewDialog.Builder setNegativeButton(String negativeButtonText, OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        @SuppressLint("InflateParams")
        public CommonEditViewDialog create() {

            dialog = new CommonEditViewDialog(context, R.style.Dialog_FS);
            dialog.setCancelable(true);// 返回键是否可用
            dialog.setCanceledOnTouchOutside(false);

            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.common_edit_dialog, null);// 得到加载view
            dialog.setContentView(view,
                    new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.FILL_PARENT, android.view.ViewGroup.LayoutParams.FILL_PARENT));// 设置布局

            FrameLayout framedialog = (FrameLayout)view.findViewById(R.id.framedialog);
            framedialog.setLayoutParams(new FrameLayout.LayoutParams(CommonUtils.getWidthPixels(context) * 4 / 5, android.view.ViewGroup.LayoutParams.FILL_PARENT));


            tv_title = (TextView) view.findViewById(R.id.tv_title);
            etUserName = (EditText) view.findViewById(R.id.et_user_name);
            etUserPhone = (EditText) view.findViewById(R.id.et_user_phone);
            tv_title.setText(title);
            tv_title.setTextColor(context.getResources().getColor(titleColor));
            btn_cancel = (Button) view.findViewById(R.id.btn_cancel);

            if (negativeButtonText != null) {
                btn_cancel.setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    btn_cancel.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                            dialog.dismiss();

                        }
                    });
                }else {
                    btn_cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                }
            } else {
                btn_cancel.setVisibility(View.GONE);
            }
            btn_confirm = (Button) view.findViewById(R.id.btn_confirm);
            if ( positiveButtonText!= null) {
                btn_confirm.setText(positiveButtonText);
                if (positiveButtonClickListener != null) {

                    RxView.clicks( btn_confirm )
                            .throttleFirst( 1 , TimeUnit.SECONDS )   //5秒钟之内只取一个点击事件，防抖操作

                            .observeOn(AndroidSchedulers.mainThread())
                            .filter(new Func1<Void, Boolean>() {
                                @Override
                                public Boolean call(Void aVoid) {

                                    addUserName = etUserName.getText().toString().trim();
                                    addUserPhone = etUserPhone.getText().toString().trim();
                                    if (TextUtils.isEmpty(addUserName)){

                                        MyToast.show(context, "请你输入送水工名字");
                                        return false;

                                    }
                                    if (TextUtils.isEmpty(addUserPhone)){

                                        MyToast.show(context, "请你输入送水工电话");
                                        return false;

                                    }

                                    if(!CheckPhone.checkPhoneNumber(addUserPhone)){

                                        MyToast.show(context, "请你输入11位送水工电话");

                                        return false ;
                                    }

                                    return true;
                                }
                            })

                            .observeOn(Schedulers.newThread())
                            .subscribe(new Action1<Void>() {
                                @Override
                                public void call(Void aVoid) {

                                    positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);

                                }
                            }) ;

                }else {
                    btn_confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                }
            } else {
                btn_confirm.setVisibility(View.GONE);
            }

            if (contentView != null) {
                LinearLayout content = (LinearLayout) view.findViewById(R.id.common_dialog_content_layout);
                content.removeAllViews();
                content.addView(contentView, new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            // dialog.setContentView(view);
            return dialog;
        }
    }




}
