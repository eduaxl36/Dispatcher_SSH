/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_kantar_datas;

/**
 *
 * @author Eduardo.Fernando
 */
public class FactoryPadrao {
    
       
    public ServicoDataExtensoes getPadrao(String Data,String Comportamento) throws Exception{
    
    ServicoDataExtensoes Engine=null;
        
        
        switch (Comportamento) {
            case "Diario" ->                 {
                    Engine = new ServicoDataDiario();
                    return Engine;
                }
            case "Semanal" ->                 {
                    Engine = new ServicoDataSemanal();
                    return Engine;
                }
            case "HexaDecimal" ->                 {
                     Engine = new ServicoDataDiarioHexadecimal();
                     return Engine;
                }
            default -> {
            }
        }
        
        
    return Engine;
    
    
    }
    
    
    
    
}
