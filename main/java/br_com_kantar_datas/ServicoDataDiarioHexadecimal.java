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
public class ServicoDataDiarioHexadecimal implements ServicoDataExtensoes {

    UtilPeriodos FerramentaPeriodos;

    public ServicoDataDiarioHexadecimal() {
        this.FerramentaPeriodos = FerramentaPeriodos.getInstance();
    }

    @Override
    public String obterPadrao(String Data, String Padrao, String Comportamento, int CalculoData) {

        try {
            Data = FerramentaPeriodos.ajustarCalculoData(Data, CalculoData);
            
            Date DataInput = FerramentaPeriodos.converteDataStringParaDate(Data);
            
            String[] DatasDesmembradas = FerramentaPeriodos.geradorFormatos(DataInput);
            
            String Mes = FerramentaPeriodos.retornaMesHexadecimal(DatasDesmembradas[2]);
            
            return FerramentaPeriodos.configuraPadraoHexadecimal
                (
                        Padrao,
                        DatasDesmembradas[3],
                        Mes,
                        DatasDesmembradas[4]
                );
        } catch (ParseException ex) {
            Logger.getLogger(ServicoDataDiarioHexadecimal.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "";
    }

}
