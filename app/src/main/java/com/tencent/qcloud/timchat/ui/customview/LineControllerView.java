package com.tencent.qcloud.timchat.ui.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.tencent.qcloud.timchat.R;

/**
 * 设置等页面条状控制或显示信息的控件
 */
public class LineControllerView extends LinearLayout {

    private String name;
    private boolean isBottom;
    private String content;
    private boolean canNav;
    private boolean isSwitch;

    public LineControllerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_line_controller, this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LineControllerView, 0, 0);
        try {
            name = ta.getString(R.styleable.LineControllerView_name);
            content = ta.getString(R.styleable.LineControllerView_content);
            isBottom = ta.getBoolean(R.styleable.LineControllerView_isBottom, false);
            canNav = ta.getBoolean(R.styleable.LineControllerView_canNav,false);
            isSwitch = ta.getBoolean(R.styleable.LineControllerView_isSwitch,false);
            setUpView();
        } finally {
            ta.recycle();
        }
    }


    private void setUpView(){
        TextView tvName = (TextView) findViewById(R.id.name);
        tvName.setText(name);
        TextView tvContent = (TextView) findViewById(R.id.content);
        tvContent.setText(content);
        View bottomLine = findViewById(R.id.bottomLine);
        bottomLine.setVisibility(isBottom ? VISIBLE : GONE);
        ImageView navArrow = (ImageView) findViewById(R.id.rightArrow);
        navArrow.setVisibility(canNav?VISIBLE:GONE);
        LinearLayout contentPanel = (LinearLayout) findViewById(R.id.contentText);
        contentPanel.setVisibility(isSwitch?GONE:VISIBLE);
        Switch switchPanel = (Switch) findViewById(R.id.btnSwitch);
        switchPanel.setVisibility(isSwitch?VISIBLE:GONE);

    }


    /**
     * 设置文字内容
     *
     * @param content 内容
     */
    public void setContent(String content){
        this.content = content;
        TextView tvContent = (TextView) findViewById(R.id.content);
        tvContent.setText(content);
    }
}