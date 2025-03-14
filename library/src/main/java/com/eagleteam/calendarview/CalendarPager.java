package com.eagleteam.calendarview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Custom ViewPager that allows swiping to be disabled.
 */
class CalendarPager extends ViewPager {

  private boolean pagingEnabled = true;

  public CalendarPager(@NonNull final Context context) {
    super(context);
  }

  public CalendarPager(@NonNull final Context context, @Nullable final AttributeSet attrs) {
    super(context, attrs);
  }

  /**
   * enable disable viewpager scroll
   *
   * @param pagingEnabled false to disable paging, true for paging (default)
   */
  public void setPagingEnabled(boolean pagingEnabled) {
    this.pagingEnabled = pagingEnabled;
  }

  /**
   * @return is this viewpager allowed to page
   */
  public boolean isPagingEnabled() {
    return pagingEnabled;
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    return pagingEnabled && super.onInterceptTouchEvent(ev);
  }

  @Override
  public boolean onTouchEvent(MotionEvent ev) {
    return pagingEnabled && super.onTouchEvent(ev);
  }

  @Override
  public boolean canScrollVertically(int direction) {
    /**
     * disables scrolling vertically when paging disabled, fixes scrolling
     * for nested {@link android.support.v4.view.ViewPager}
     */
    return pagingEnabled && super.canScrollVertically(direction);
  }

  @Override
  public boolean canScrollHorizontally(int direction) {
    /**
     * disables scrolling horizontally when paging disabled, fixes scrolling
     * for nested {@link android.support.v4.view.ViewPager}
     */
    return pagingEnabled && super.canScrollHorizontally(direction);
  }
}
