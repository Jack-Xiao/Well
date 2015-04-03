package com.tianye.mobile.well.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Created by lenovo on 2015/3/20.
 */
public class SlidingTabLayout extends HorizontalScrollView {


    public interface TabColorizer{
        /**
         * @return return the color of the indicator used when{@code position} is selected.
         */
        int getIndicatorColor(int position);

        int getDividerColor(int position);
    }

    private static final int TITLE_OFFSET_DIPS = 24;
    private static final int TAB_VIEW_PADDING_DIPS = 16;
    private static final int TAB_VIEW_TEXT_SIZE_SP = 12;

    private int mTitleOffset;
    private int mTabViewLayoutId;
    private int mTabViewTextViewId;

    private ViewPager mViewPager;
    //private ViewPager.onPageChangeListener mViewPagerPageChangeListener;

    //private final SlidingTabLayout mTabStrip;


    public SlidingTabLayout(Context context) {
        this(context,null);
    }

    public SlidingTabLayout(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public SlidingTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setHorizontalScrollBarEnabled(false);//取消水平滚动显示
        setFillViewport(true);

        mTitleOffset = (int)(TITLE_OFFSET_DIPS * getResources().getDisplayMetrics().density);
        //mTabStrip = new TabColorizer()
    }

}
