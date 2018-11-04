package com.bwie.zhuanpan;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

/**
 * Created by 择木 on 2018/11/4.
 * yangzhong
 */

public class Choujiang extends View implements View.OnClickListener {


    private Paint mPaint;
    private int[]  Colors;
    private int mPwidth;
    private int mPheight;
    private int mSY;
    private int mSX;

    private boolean isRoct;

    private  String[] text=new String[]{"自行车一辆","iphoneX","指甲刀","谢谢惠顾","再来一次","宝马汽车"};
    private RotateAnimation mRotateAnimation;
    private RotateAnimation mRotateAnimation1;

    public Choujiang(Context context) {
        this(context,null);
    }

    public Choujiang(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public Choujiang(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        //获取屏幕的宽高
        DisplayMetrics metrics=context.getResources().getDisplayMetrics();
        mPwidth = metrics.widthPixels;
        mPheight = metrics.heightPixels;

        //将控件定位到屏幕的中心
        mSX = mPwidth/2;
        mSY = mPheight/2;

        //初始化画笔
        initPaint();
        initAinimor();
        Colors=new int[]{Color.BLACK,Color.BLUE,Color.RED,Color.YELLOW,Color.GREEN,Color.LTGRAY};

        //给自己设置一个点击事件
        this.setOnClickListener(this);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //测量View的大小
        setMeasuredDimension(100,100);
    }

    //绘制


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //移动画布的坐标原点
        canvas.translate(mSX,mSY);

        //绘制6个圆弧
        RectF rectF=new RectF(-300,-300,300,300);

        //设置一个开始的位置
        float start=60;
        for (int i = 0; i <6 ; i++) {

            //设置6个圆盘的颜色
            mPaint.setColor(Colors[i]);
            //开始画
            canvas.drawArc(rectF,start*i,60,true,mPaint);


        }

        //绘制中心的圆
        mPaint.setColor(Color.RED);
        canvas.drawCircle(0,0,100,mPaint);



        //设置字体的颜色
        mPaint.setColor(Color.WHITE);
        //字体大小
        mPaint.setTextSize(40);

        //获取文字的宽高
        Rect rectText=new Rect();
        //设置中间圆的内容
        mPaint.getTextBounds("start",0,5,rectText);

        //获取宽高
       int width= rectText.width();
        int height=rectText.height();

        canvas.drawText("start",-width/2,height/2,mPaint);

        //绘制描述信息

        RectF rectF1=new RectF(-200,-200,200,200);
        for (int i = 0; i <6 ; i++) {
            mPaint.setColor(Color.WHITE);
            Path path=new Path();
            path.addArc(rectF1,start*i+15,60);

            canvas.drawTextOnPath(text[i],path,0,0,mPaint);

        }






    }

    private void initAinimor() {
    }


    private  void StartAnimor(){

        //判断是否在旋转状态
        isRoct=true;



        double random=Math.random();

        mRotateAnimation = new RotateAnimation(0,(float)(720*random),mSX,mSY);

        mRotateAnimation.setFillAfter(true);
        mRotateAnimation.setDuration(800);

        //设置重复次数
        mRotateAnimation.setInterpolator(new LinearInterpolator());

        mRotateAnimation.setRepeatMode(Animation.RESTART);

        //给动画添加一个监听事件
        mRotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                          isRoct=false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        startAnimation(mRotateAnimation);





    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    public void onClick(View v) {
        if (!isRoct){
            StartAnimor();
        }

    }


    //给一个随机的结果答案
    private void setRoundDom(){
        double random=Math.random();

        mRotateAnimation1 = new RotateAnimation(0,(float)(360*random),mSX,mSY);

        mRotateAnimation1.setFillAfter(true);
        mRotateAnimation1.setDuration(100);

        startAnimation(mRotateAnimation1);

    }
}
