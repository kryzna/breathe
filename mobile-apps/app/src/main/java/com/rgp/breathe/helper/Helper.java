package com.rgp.breathe.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by krish on 4/5/2016.
 */


public class Helper {

    public static String getFormattedDate(String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(new Date());
    }
}
