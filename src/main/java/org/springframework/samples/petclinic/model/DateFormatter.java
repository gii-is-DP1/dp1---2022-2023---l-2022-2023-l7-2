package org.springframework.samples.petclinic.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class DateFormatter implements Formatter<LocalDateTime>{
    private DateTimeFormatter format =  DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss");
    @Override
    public String print(LocalDateTime object, Locale locale) {

        LocalDateTime fechaHora = LocalDateTime.of(2000, 04, 04, 04, 04,0);
        Date date = Date.from(fechaHora.atZone(ZoneId.systemDefault()).toInstant());
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        String dateFormateada = formateador.format(date);
        return  dateFormateada;

        //return object.format(format).toString();
    }

    @Override
    public LocalDateTime parse(String text, Locale locale) throws ParseException {
        return LocalDateTime.parse(text, format);
    }

   
    
}
