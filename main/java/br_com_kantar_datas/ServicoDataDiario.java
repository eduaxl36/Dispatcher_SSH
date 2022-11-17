/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_kantar_datas;


import br_com_util.UtilPeriodos;
import static br_com_util.UtilPeriodos.ajustarCalculoData;
import java.text.ParseException;
import java.util.Date;

/**
 *
 * @author Eduardo.Fernando
 */
public class ServicoDataDiario implements ServicoDataExtensoes {


  
    @Override
    public String obterPadrao(String Data, String Padrao,String Comportamento,int CalculoData) throws Exception {
        
        Data = ajustarCalculoData(Data,CalculoData);
        
        Date DataTransformada =UtilPeriodos.converteDataStringParaDate(Data);

        return UtilPeriodos.configuraAnoConformePadrao(Padrao, DataTransformada);
    
    }  
 


    public static void main(String[] args) throws ParseException, Exception {
        
//      System.out.println( new ServicoDataDiario().obterPadrao("20221110","APDAMMDD.R98"));
//        
//        
    }

    
    
}
