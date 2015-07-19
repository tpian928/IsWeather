package com.ctc.isweather.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ctc.isweather.R;
import com.ctc.isweather.control.DBTools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by saty on 2015/7/17.
 */
public class MyAdapter extends BaseAdapter implements View.OnClickListener {
    private List<Map<String, Object>> city_data;
    private Context mContext;//context, get convertView
    private int mScreentWidth;//screen width
    private boolean isOperating;

    /**
     * constructor
     *
     * @param context
     * @param screenWidth
     */
    public MyAdapter(Context context, int screenWidth) {
        //initialize all private variables
        mContext = context;
        mScreentWidth = screenWidth;
        isOperating = false;

        //get city list
        city_data = getData();

    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return city_data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        //initialize convertView
        if (convertView == null) {
            //view
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_city, parent, false);

            //init holder
            holder = new ViewHolder();

            //all view
            holder.itemHorizontalScrollView = (HorizontalScrollView) convertView.findViewById(R.id.hsv);

            //action
            holder.city_action_view = convertView.findViewById(R.id.city_action);
            holder.delete_action = (ImageView) convertView.findViewById(R.id.delete);

            //remember position
            holder.delete_action.setTag(position);

            holder.img = (ImageView) convertView.findViewById(R.id.img);
            holder.city_weather = (TextView) convertView.findViewById(R.id.city_weather);
            holder.date = (TextView) convertView.findViewById(R.id.date);

            //set width of view_content
            holder.city_content_view = convertView.findViewById(R.id.city_content);
            ViewGroup.LayoutParams lp = holder.city_content_view.getLayoutParams();
            lp.width = mScreentWidth;

            convertView.setTag(holder);
            Log.d("debug", "if clause,init holder");
        } else//ViewHolder
        {
            holder = (ViewHolder) convertView.getTag();
            Log.d("debug", "else clause,init holder");
        }

        //touch listener
        convertView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:

                        //ViewHolder
                        ViewHolder viewHolder = (ViewHolder) v.getTag();

                        //HorizontalScrollView
                        int scrollX = viewHolder.itemHorizontalScrollView.getScrollX();

                        //operating length
                        int actionW = viewHolder.city_action_view.getWidth();

                        if (scrollX < actionW / 2) {
                            viewHolder.itemHorizontalScrollView.smoothScrollTo(0, 0);
                        } else {
                            viewHolder.itemHorizontalScrollView.smoothScrollTo(actionW, 0);
                        }
                        return true;
                }
                return false;
            }
        });

        //
        if (holder.itemHorizontalScrollView.getScrollX() != 0) {
            holder.itemHorizontalScrollView.scrollTo(0, 0);
        }

        //write data to screen
        Object o = city_data.get(position).get("img");
        int i = Integer.parseInt(o.toString());
        Log.d("testo", "" + i);
//        holder.img.setImageResource(o);

        holder.img.setImageResource((Integer) city_data.get(position).get("img"));
        holder.city_weather.setText("" + city_data.get(position).get("city_weather"));
        holder.date.setText("" + city_data.get(position).get("date"));

        //listener
        holder.delete_action.setOnClickListener(this);


        holder.city_content_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.itemHorizontalScrollView.scrollTo(0, 0);
                notifyDataSetChanged();
            }
        });


        return convertView;
    }

    /**
     * delete record
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        city_data.remove(position);
        DBTools.deleteInConcity((String)city_data.get(position).get("city_weather"));
        notifyDataSetChanged();
    }


    /**
     * helper,hold the view
     */
    class ViewHolder {
        //
        public HorizontalScrollView itemHorizontalScrollView;

        //content view
        public View city_content_view;//LinearLayout
        public ImageView img;//img
        public TextView city_weather;//weather
        public TextView date;//date

        //action
        public View city_action_view;//LinearLayout
        public ImageView delete_action;//delete
    }


    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        ArrayList<String> citylist = DBTools.QueryInConcity();
        for (String city : citylist) {
            Log.i("chris", city);
        }


        String date = getDate();
        date = date + " " + getWeekDay(date);

        if (citylist.size() > 0) {
            for (int i = 0; i < citylist.size(); i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("date", date);
                map.put("city_weather", citylist.get(i));
                // need some method to indicate which picture will be used.
                map.put("img", R.drawable.shower);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * Get the current date
     * @return Date
     */
    public static String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return formatter.format(curDate);
    }

    /**
     * get the day of the week
     * @param date Date
     * @return week day
     */
    public String getWeekDay(String date) {
        String[] dates = date.split("/");
        Calendar calendar = Calendar.getInstance();//获得一个日历
        calendar.set(Integer.valueOf(dates[0]), Integer.valueOf(dates[1]) - 1, Integer.valueOf(dates[2]));//设置当前时间,月份是从0月开始计算
        int number = calendar.get(Calendar.DAY_OF_WEEK);//星期表示1-7，是从星期日开始，
        String[] str = {"", "Sun", "Mon", "Thus", "Wens", "Thur", "Fri", "Sat"};
        //System.out.println(str[number]);
        return str[number];
    }
}
