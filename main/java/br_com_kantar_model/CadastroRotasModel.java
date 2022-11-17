/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_kantar_model;

import java.io.File;

/**
 *
 * @author Eduardo.Fernando
 */
public class CadastroRotasModel {

    private int Id;
    private String Instancia;
    private String Praca;
    private String Processo;
    private int Tipo;
    private String Origem;
    private String PadraoOrigem;
    private String ComportamentoDataOrigem;
    private int  CalculoDataOrigem;
    private String Destino;
    private String PadraoDestino;
    private String ComportamentoDataDestino;
    private int  CalculoDataDestino;
    private String Mode;
    private String DataOwner;
    private String InternalFolder;


    public CadastroRotasModel() {
    }

    public CadastroRotasModel(int Id, String Instancia, String Praca, String Processo, int Tipo, String Origem, String PadraoOrigem, String ComportamentoDataOrigem, int CalculoDataOrigem, String Destino, String PadraoDestino, String ComportamentoDataDestino, int CalculoDataDestino, String Mode, String DataOwner, String InternalFolder) {
        this.Id = Id;
        this.Instancia = Instancia;
        this.Praca = Praca;
        this.Processo = Processo;
        this.Tipo = Tipo;
        this.Origem = Origem;
        this.PadraoOrigem = PadraoOrigem;
        this.ComportamentoDataOrigem = ComportamentoDataOrigem;
        this.CalculoDataOrigem = CalculoDataOrigem;
        this.Destino = Destino;
        this.PadraoDestino = PadraoDestino;
        this.ComportamentoDataDestino = ComportamentoDataDestino;
        this.CalculoDataDestino = CalculoDataDestino;
        this.Mode = Mode;
        this.DataOwner = DataOwner;
        this.InternalFolder = InternalFolder;
    }

    public CadastroRotasModel(String Instancia, String Praca, String Processo, int Tipo, String Origem, String PadraoOrigem, String ComportamentoDataOrigem, int CalculoDataOrigem, String Destino, String PadraoDestino, String ComportamentoDataDestino, int CalculoDataDestino, String Mode, String DataOwner, String InternalFolder) {
        this.Instancia = Instancia;
        this.Praca = Praca;
        this.Processo = Processo;
        this.Tipo = Tipo;
        this.Origem = Origem;
        this.PadraoOrigem = PadraoOrigem;
        this.ComportamentoDataOrigem = ComportamentoDataOrigem;
        this.CalculoDataOrigem = CalculoDataOrigem;
        this.Destino = Destino;
        this.PadraoDestino = PadraoDestino;
        this.ComportamentoDataDestino = ComportamentoDataDestino;
        this.CalculoDataDestino = CalculoDataDestino;
        this.Mode = Mode;
        this.DataOwner = DataOwner;
        this.InternalFolder = InternalFolder;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getInstancia() {
        return Instancia;
    }

    public void setInstancia(String Instancia) {
        this.Instancia = Instancia;
    }

    public String getPraca() {
        return Praca;
    }

    public void setPraca(String Praca) {
        this.Praca = Praca;
    }

    public String getProcesso() {
        return Processo;
    }

    public void setProcesso(String Processo) {
        this.Processo = Processo;
    }

    public int getTipo() {
        return Tipo;
    }

    public void setTipo(int Tipo) {
        this.Tipo = Tipo;
    }

    public String getOrigem() {
        return Origem;
    }

    public void setOrigem(String Origem) {
        this.Origem = Origem;
    }

    public String getPadraoOrigem() {
        return PadraoOrigem;
    }

    public void setPadraoOrigem(String PadraoOrigem) {
        this.PadraoOrigem = PadraoOrigem;
    }

    public String getComportamentoDataOrigem() {
        return ComportamentoDataOrigem;
    }

    public void setComportamentoDataOrigem(String ComportamentoDataOrigem) {
        this.ComportamentoDataOrigem = ComportamentoDataOrigem;
    }

    public int getCalculoDataOrigem() {
        return CalculoDataOrigem;
    }

    public void setCalculoDataOrigem(int CalculoDataOrigem) {
        this.CalculoDataOrigem = CalculoDataOrigem;
    }

    public String getDestino() {
        return Destino;
    }

    public void setDestino(String Destino) {
        this.Destino = Destino;
    }

    public String getPadraoDestino() {
        return PadraoDestino;
    }

    public void setPadraoDestino(String PadraoDestino) {
        this.PadraoDestino = PadraoDestino;
    }

    public String getComportamentoDataDestino() {
        return ComportamentoDataDestino;
    }

    public void setComportamentoDataDestino(String ComportamentoDataDestino) {
        this.ComportamentoDataDestino = ComportamentoDataDestino;
    }

    public int getCalculoDataDestino() {
        return CalculoDataDestino;
    }

    public void setCalculoDataDestino(int CalculoDataDestino) {
        this.CalculoDataDestino = CalculoDataDestino;
    }

    public String getMode() {
        return Mode;
    }

    public void setMode(String Mode) {
        this.Mode = Mode;
    }

    public String getDataOwner() {
        return DataOwner;
    }

    public void setDataOwner(String DataOwner) {
        this.DataOwner = DataOwner;
    }

    public String getInternalFolder() {
        return InternalFolder;
    }

    public void setInternalFolder(String InternalFolder) {
        this.InternalFolder = InternalFolder;
    }

    @Override
    public String toString() {
        return "CadastroRotasModel{" + "Id=" + Id + ", Instancia=" + Instancia + ", Praca=" + Praca + ", Processo=" + Processo + ", Tipo=" + Tipo + ", Origem=" + Origem + ", PadraoOrigem=" + PadraoOrigem + ", ComportamentoDataOrigem=" + ComportamentoDataOrigem + ", CalculoDataOrigem=" + CalculoDataOrigem + ", Destino=" + Destino + ", PadraoDestino=" + PadraoDestino + ", ComportamentoDataDestino=" + ComportamentoDataDestino + ", CalculoDataDestino=" + CalculoDataDestino + ", Mode=" + Mode + ", DataOwner=" + DataOwner + ", InternalFolder=" + InternalFolder + '}';
    }

    
    
    

}
