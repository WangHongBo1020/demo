package com.example.whb_demo.utils;

import com.example.whb_demo.constant.DateConstants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zpw
 * @date 2021/10/24
 */
public class DateToLongSerializer extends JsonSerializer<Date> {

    /**
     * @param date
     * @param jsonGenerator
     * @param serializerProvider
     * @throws IOException
     */
    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat(DateConstants.UTC_PATTERN);
        String UTCDate = formatter.format(date);
        UTCDate = UTCDate.replace("GMT", "").replace("+0800", "+08:00");
        //返回格式为：2020-05-08T18:26:01+08:00
        jsonGenerator.writeString(UTCDate);
    }
}
