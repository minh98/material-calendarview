package com.prolificinteractive.materialcalendarview.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateLongClickListener;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.decorator.SubjectDecorator;
import com.prolificinteractive.materialcalendarview.sample.decorators.OneDayDecorator;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

/**
 * Shows off the most basic usage
 */
public class BasicActivity extends AppCompatActivity
    implements OnDateSelectedListener, OnMonthChangedListener, OnDateLongClickListener {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("EEE, d MMM yyyy");

  @BindView(R.id.calendarView)
  MaterialCalendarView widget;

  @BindView(R.id.textView)
  TextView textView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_basic);
    ButterKnife.bind(this);

    widget.setOnDateChangedListener(this);
    widget.setOnDateLongClickListener(this);
    widget.setOnMonthChangedListener(this);
    widget.setTileWidth(LinearLayout.LayoutParams.MATCH_PARENT);
    widget.setSelectedDate(LocalDate.now());
    //Setup initial text
    textView.setText("No Selection");
    widget.addDecorator(new SubjectDecorator(CalendarDay.from(2019,6,8),new int[]{Color.RED,Color.RED,Color.BLUE,Color.BLUE,Color.BLUE,Color.MAGENTA,Color.MAGENTA/**/},this));
  }

  @Override
  public void onDateSelected(
      @NonNull MaterialCalendarView widget,
      @NonNull CalendarDay date,
      boolean selected) {
    textView.setText(selected ? FORMATTER.format(date.getDate()) : "No Selection");
  }

  @Override
  public void onDateLongClick(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date) {
    final String text = String.format("%s is available", FORMATTER.format(date.getDate()));
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
    //noinspection ConstantConditions
    getSupportActionBar().setTitle(FORMATTER.format(date.getDate()));
  }
}
