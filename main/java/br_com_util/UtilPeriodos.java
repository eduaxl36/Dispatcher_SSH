/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

/**
 *
 * @author Eduardo.Fernando
 */
public class UtilPeriodos {

    public static String configuraAnoConformePadrao(String PadraoRecebido, Date Data) {

        long OcorrenciasAno = PadraoRecebido.chars().filter(ch -> ch == 'Y').count();
        String RetornaDataDoPadrao = "";

        if (OcorrenciasAno == 4) {

            String Ano = new SimpleDateFormat("yyyy").format(Data.getTime());
            String Mes = new SimpleDateFormat("MM").format(Data.getTime());
            String Dia = new SimpleDateFormat("dd").format(Data.getTime());

            RetornaDataDoPadrao
                    = PadraoRecebido.replaceAll("DD", Dia)
                            .replaceAll("MM", Mes).replaceAll("YYYY", Ano);

        } else {

            String Ano = new SimpleDateFormat("yy").format(Data.getTime());
            String Mes = new SimpleDateFormat("MM").format(Data.getTime());
            String Dia = new SimpleDateFormat("dd").format(Data.getTime());

            RetornaDataDoPadrao
                    = PadraoRecebido.replaceAll("DD", Dia)
                            .replaceAll("MM", Mes).replaceAll("YY", Ano);

        }

        return RetornaDataDoPadrao;
    }

    public static org.joda.time.LocalDateTime converteDateEmJodaDateTime(Date Data) {

        org.joda.time.format.DateTimeFormatter Formatador = DateTimeFormat.forPattern("yyyyMMdd");
        org.joda.time.LocalDateTime DataJoda = Formatador.parseLocalDateTime(new SimpleDateFormat("yyyyMMdd").format(Data));
        org.joda.time.LocalDateTime DataConvertidaMiami = new org.joda.time.LocalDateTime(DataJoda, DateTimeZone.forTimeZone(TimeZone.getTimeZone("America/Miami")));

        return DataConvertidaMiami;
    }

    public static Date converteDataStringParaDate(String DataInput) throws ParseException {

        return new SimpleDateFormat("yyyyMMdd").parse(DataInput);
    }

    public static String ajustarCalculoData(String Data, int Calculo) throws ParseException {

        Date DataVal = new SimpleDateFormat("yyyyMMdd").parse(Data);

        Calendar Cal = Calendar.getInstance();
        Cal.setTime(DataVal);
        Cal.add(Calendar.DATE, Calculo);

        return new SimpleDateFormat("yyyyMMdd").format(Cal.getTime());

    }

}
