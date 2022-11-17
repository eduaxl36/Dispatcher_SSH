/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_kantar_datas;


import static br_com_util.UtilPeriodos.ajustarCalculoData;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Eduardo.Fernando
 */
public class ServicoDataDiarioHexadecimal implements ServicoDataExtensoes {


       @Override
       public String obterPadrao(String Data,String Padrao,String Comportamento,int CalculoData) throws ParseException {
        
        Data = ajustarCalculoData(Data,CalculoData);
        
        Date DataInput = new SimpleDateFormat("yyyyMMdd").parse(Data);

        String Ano = new SimpleDateFormat("yyyy").format(DataInput);
        String Mes = new SimpleDateFormat("MM").format(DataInput);
        String Dia = new SimpleDateFormat("dd").format(DataInput);
        
        String AnoComUmDigito = Ano.substring(Ano.length() - 1, Ano.length());

        switch (Mes) {

            case "10":
                Mes = new String("A");
                break;
            case "11":
                Mes = new String("B");
                break;
            case "12":
                Mes = new String("C");
                break;
        }

        String RetornaDataDoPadrao = Padrao.replaceAll("DD", Dia).replaceAll("MM", Mes).replaceAll("Y", AnoComUmDigito);

        return RetornaDataDoPadrao;

    }   
    
    
    
    
    
    
    public static void main(String[] args) throws ParseException {

  
    }

}
