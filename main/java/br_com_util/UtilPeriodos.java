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

    public static UtilPeriodos getInstance() {
        
       return new  UtilPeriodos();
    }

    public String retornaMesHexadecimal(String Mes) {

        switch (Mes) {

            case "10" ->
                Mes = "A";
            case "11" ->
                Mes = "B";
            case "12" ->
                Mes = "C";
        }

        return Mes;
    }

    /**
     * Metodo Recebe data Joda e desmembra em Semana do Ano,Posicao do dia na
     * semana, Ano,Mes,Mes por extenso,Ano com 2 digitos
     *
     * @param Data
     * @return String[]
     * @throws ParseException
     */
    public String[] geradorFormatos(org.joda.time.LocalDateTime Data) throws ParseException {

        int SemanaDoAno = Data.weekOfWeekyear().get();
        String PosicaoDoDia = String.valueOf(Data.dayOfWeek().get());
        String Ano = new SimpleDateFormat("yyyy").format(new SimpleDateFormat("yyyy").parse("" + Data.getWeekyear()));
        String Mes = new SimpleDateFormat("MM").format(new SimpleDateFormat("MM").parse("" + Data.getMonthOfYear()));
        String MesExtenso = new SimpleDateFormat("MMMM").format(new SimpleDateFormat("MM").parse("" + Data.getMonthOfYear()));
        String Ano2Digits = new SimpleDateFormat("yy").format(new SimpleDateFormat("yy").parse("" + Data.getWeekyear()));

        return new String[]{
            String.valueOf(SemanaDoAno),
            PosicaoDoDia,
            Ano,
            Mes,
            MesExtenso,
            Ano2Digits

        };
    }

    public String[] geradorFormatos(Date Data) {

        String Ano = new SimpleDateFormat("yyyy").format(Data.getTime());
        String Ano2Digits = new SimpleDateFormat("yy").format(Data.getTime());
        String Mes = new SimpleDateFormat("MM").format(Data.getTime());
        String Dia = new SimpleDateFormat("dd").format(Data.getTime());
        String AnoComUmDigito = Ano.substring(Ano.length() - 1, Ano.length());

        return new String[]{Ano, Ano2Digits, Mes, Dia, AnoComUmDigito};
    }

    public String configuraAnoConformePadrao(String PadraoRecebido, Date Data) {

        String RetornaDataDoPadrao = "";

        String[] DatasDesmembradas = geradorFormatos(Data);

        RetornaDataDoPadrao
                = PadraoRecebido.replaceAll("Œ", DatasDesmembradas[1])
                        .replaceAll("œ", DatasDesmembradas[3])
                        .replaceAll("Š", DatasDesmembradas[2])
                        .replaceAll("‰", DatasDesmembradas[0]);

        return RetornaDataDoPadrao;
    }

    public String configuraPadraoHexadecimal(String Padrao,String Dia,String Mes,String AnoUmDigito){
    
    
    return Padrao
                .replaceAll("œ", Dia)
                .replaceAll("Š", Mes)
                .replaceAll("£", AnoUmDigito);
    
    
    }
    
    
    public String configuraAnoDaSemanaConformePadrao(org.joda.time.LocalDateTime DataConvertidaMiami, String Padrao) throws ParseException {

        String[] DataDesmembrada = geradorFormatos(DataConvertidaMiami);

        Padrao = Padrao
                .replaceAll("‰", DataDesmembrada[2])
                .replaceAll("Œ", DataDesmembrada[5])
                .replaceAll("\\*", "" + DataDesmembrada[0])
                .replaceAll("%", DataDesmembrada[1])
                .replaceAll("Š", DataDesmembrada[3])
                .replaceAll("#", DataDesmembrada[4]);

        return Padrao;

    }

    public org.joda.time.LocalDateTime converteDateEmJodaDateTime(Date Data) {

        org.joda.time.format.DateTimeFormatter Formatador = DateTimeFormat.forPattern("yyyyMMdd");
        org.joda.time.LocalDateTime DataJoda = Formatador.parseLocalDateTime(new SimpleDateFormat("yyyyMMdd").format(Data));
        org.joda.time.LocalDateTime DataConvertidaMiami = new org.joda.time.LocalDateTime(DataJoda, DateTimeZone.forTimeZone(TimeZone.getTimeZone("America/Miami")));

        return DataConvertidaMiami;
    }

    public Date converteDataStringParaDate(String DataInput) throws ParseException {

        return new SimpleDateFormat("yyyyMMdd").parse(DataInput);
    }

    public String ajustarCalculoData(String Data, int Calculo) throws ParseException {

        Date DataConvertida = converteDataStringParaDate(Data);
        Calendar Cal = Calendar.getInstance();
        Cal.setTime(DataConvertida);
        Cal.add(Calendar.DATE, Calculo);

        return new SimpleDateFormat("yyyyMMdd").format(Cal.getTime());

    }

}
