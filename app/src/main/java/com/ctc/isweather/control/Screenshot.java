package com.ctc.isweather.control;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by TPIAN on 15/7/24.
 */
public class Screenshot {

    public static Bitmap getBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width , height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);
        v.draw(c);
        return b;
    }

    public static Bitmap takeScreenShot(Activity activity)
    {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap b1 = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = activity.getWindowManager().getDefaultDisplay().getHeight();

        Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height  - statusBarHeight);
        view.destroyDrawingCache();
        return b;
    }
    public static void savePic(Bitmap b, String strFileName)
    {
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(strFileName);
            if (null != fos)
            {
                b.compress(Bitmap.CompressFormat.PNG, 90, fos);
                fos.flush();
                fos.close();
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
