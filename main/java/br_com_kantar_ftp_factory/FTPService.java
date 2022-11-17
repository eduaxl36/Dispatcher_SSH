/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_kantar_ftp_factory;

import br_com_kantar_dao.FTPConnectionDao;
import br_com_kantar_model.FTPConnectionModel;
import java.io.IOException;

/**
 *
 * @author Eduardo.Fernando
 */
public abstract class FTPService {

    FTPConnectionModel Model;
    
    public FTPService() {
    }

    public FTPService(FTPConnectionModel Model) {
        this.Model = Model;
    }

 
    public abstract void downloadArquivo(String Remoto, String Local) throws Exception ;

    public abstract void uploadArquivo(String Local, String Remoto) throws Exception ;

    public abstract boolean validaDestinoRemoto(String Remoto) throws Exception ;
    

    public FTPConnectionModel getModel() {
        return Model;
    }

    public void setModel(FTPConnectionModel Model) {
        this.Model = Model;
    }
    
    
    
    

}
