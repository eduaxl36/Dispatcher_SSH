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
public class ServicoDataDiario implements ServicoDataExtensoes {

    private UtilPeriodos FerramentaPeriodos=UtilPeriodos.getInstance();

    @Override
    public String obterPadrao(String Data, String Padrao, String Comportamento, int CalculoData) {

        try {
            Data = FerramentaPeriodos.ajustarCalculoData(Data, CalculoData);
            
            Date DataTransformada = FerramentaPeriodos.converteDataStringParaDate(Data);
            
            return FerramentaPeriodos.configuraAnoConformePadrao(Padrao, DataTransformada);
        } catch (ParseException ex) {
            Logger.getLogger(ServicoDataDiario.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        return "";
        
    }

}
