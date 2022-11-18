/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_kantar_datas;

import br_com_util.UtilPeriodos;
import static br_com_util.UtilPeriodos.ajustarCalculoData;
import static br_com_util.UtilPeriodos.converteDateEmJodaDateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Eduardo.Fernando
 */
public class ServicoDataSemanal implements ServicoDataExtensoes {

    public static String configuraAnoDaSemanaConformePadrao(org.joda.time.LocalDateTime DataConvertidaMiami, String Padrao) throws ParseException {

        int SemanaDoAno = DataConvertidaMiami.weekOfWeekyear().get();

        String PosicaoDoDia = "" + DataConvertidaMiami.dayOfWeek().get();

        String Ano = new SimpleDateFormat("yyyy").format(new SimpleDateFormat("yyyy").parse("" + DataConvertidaMiami.getWeekyear()));

        String Mes = new SimpleDateFormat("MM").format(new SimpleDateFormat("MM").parse("" + DataConvertidaMiami.getMonthOfYear()));

        String MesExtenso = new SimpleDateFormat("MMMM").format(new SimpleDateFormat("MM").parse("" + DataConvertidaMiami.getMonthOfYear()));

        String Ano2Digits = new SimpleDateFormat("yy").format(new SimpleDateFormat("yy").parse("" + DataConvertidaMiami.getWeekyear()));

//        long OcorrenciasAno = Padrao.chars().filter(ch -> ch == 'Y').count();
        Padrao = Padrao
                
                .replaceAll("‰", Ano)
                .replaceAll("Œ", Ano2Digits)
                .replaceAll("\\*", "" + SemanaDoAno)
                .replaceAll("%", PosicaoDoDia)
                .replaceAll("Š", Mes)
                .replaceAll("#", MesExtenso);

        return Padrao;

    }

    @Override
    public String obterPadrao(String Data, String Padrao, String Comportamento, int CalculoData) throws ParseException {

        Data = ajustarCalculoData(Data, CalculoData);

        Date DataConvertida = UtilPeriodos.converteDataStringParaDate(Data);

        org.joda.time.LocalDateTime DataConvertidaMiami = converteDateEmJodaDateTime(DataConvertida);

        Padrao = configuraAnoDaSemanaConformePadrao(DataConvertidaMiami, Padrao);

        return Padrao;
    }

}
