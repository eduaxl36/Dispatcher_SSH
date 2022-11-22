/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_kantar_datas;

import br_com_util.UtilPeriodos;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eduardo.Fernando
 */
public class ServicoDataSemanal implements ServicoDataExtensoes {

    UtilPeriodos FerramentaPeriodos;

    public ServicoDataSemanal() {
        this.FerramentaPeriodos = FerramentaPeriodos.getInstance();
    }


    @Override
    public String obterPadrao(String Data, String Padrao, String Comportamento, int CalculoData) {

        try {
            Data = FerramentaPeriodos.ajustarCalculoData(Data, CalculoData);
            
            Date DataConvertida = FerramentaPeriodos.converteDataStringParaDate(Data);
            
            org.joda.time.LocalDateTime DataConvertidaMiami = FerramentaPeriodos.converteDateEmJodaDateTime(DataConvertida);
            
            Padrao = FerramentaPeriodos.configuraAnoDaSemanaConformePadrao(DataConvertidaMiami, Padrao);
            
            return Padrao;
        } catch (ParseException ex) {
            Logger.getLogger(ServicoDataSemanal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

}
