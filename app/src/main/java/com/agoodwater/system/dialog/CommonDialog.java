package com.agoodwater.system.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.agoodwater.system.R;
import com.agoodwater.system.utils.CommonUtils;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


/**
 * Created by jason on 2016/8/18.
 * des:公共的对话框
 */
public class CommonDialog extends Dialog {

    static TextView tv_header;
    static TextView tv_content;
    static Button btn_submit;
    private static CommonDialog dialog;

    public CommonDialog(Context context) {
        super(context);
    }

    public CommonDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Activity context;
        private String title;
        private int titleColor ;
        private int messageColor = 0;
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


        public Builder(Activity context, String title,int titleColor , String message, int messageColor ,String negativeButtonText, String positiveButtonText, OnClickListener negativeButtonClickListener, OnClickListener positiveButtonClickListener) {
            this.context = context;
            this.title=title;
            this.titleColor=titleColor;
            this.message=message;
            this.messageColor=messageColor;
            this.positiveButtonText=positiveButtonText;
            this.negativeButtonText=negativeButtonText;
            this.negativeButtonClickListener=negativeButtonClickListener;
            this.positiveButtonClickListener=positiveButtonClickListener;
            create().show();
        }
        public Builder(Activity context) {
            this.context = context;
        }

        public void hidden() {

            if (dialog != null){
                dialog.dismiss();
            }

        }


        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }
        public Builder setMessageColor(int messageColor) {
            this.messageColor = messageColor;
            return this;
        }

        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        public Builder setTitleColor(int titleColor) {
            this.titleColor = titleColor;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }
        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }


        public Builder setPositiveButton(int positiveButtonText, OnClickListener listener) {
            this.positiveButtonText = (String) context.getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText, OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }
        public Builder setNegativeButton(int negativeButtonText, OnClickListener listener) {
            this.negativeButtonText = (String) context.getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText, OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        @SuppressLint("InflateParams")
        public CommonDialog create() {

            dialog = new CommonDialog(context, R.style.Dialog_FS);
            dialog.setCancelable(true);// 返回键是否可用
            dialog.setCanceledOnTouchOutside(false);

            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.common_dialog, null);// 得到加载view
            dialog.setContentView(view,
                    new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.FILL_PARENT, android.view.ViewGroup.LayoutParams.FILL_PARENT));// 设置布局

            FrameLayout framedialog = (FrameLayout)view.findViewById(R.id.framedialog);
            framedialog.setLayoutParams(new FrameLayout.LayoutParams(CommonUtils.getWidthPixels(context) * 4 / 5, android.view.ViewGroup.LayoutParams.FILL_PARENT));


            tv_title = (TextView) view.findViewById(R.id.tv_title);
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

                    //使用RxBinding设置五秒不可点击,用于提交
                    RxView.clicks(btn_confirm)
                            .throttleFirst( 5 , TimeUnit.SECONDS )
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Action1<Void>() {
                                @Override
                                public void call(Void aVoid) {
						    positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);

                                }
                            });

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

            if (message != null) {
                tv_message = (TextView) view.findViewById(R.id.tv_message);
                tv_message.setGravity(Gravity.LEFT);
                tv_message.setText(Html.fromHtml(message));
                if (messageColor != 0){
                    tv_message.setTextColor(context.getResources().getColor(messageColor));
                }
            } else if (contentView != null) {
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
