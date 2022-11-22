/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_kantar_services;

import br_com_kantar_constraints.CadastroRotasConstraints;
import br_com_kantar_dao.CadastroRotasDao;
import br_com_kantar_model.CadastroRotasModel;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Eduardo.Fernando
 */
public class CadastroRotasServices {

    private CadastroRotasDao Servico;
    private CadastroRotasConstraints Constraints;

    public CadastroRotasServices() throws IOException {

        Servico = new CadastroRotasDao();

    }

    public CadastroRotasServices(int Id, JTable Tabela, String Instancia, String Praça, String Processo, int Tipo, String Origem, String PadraoOrigem, String ComportamentoOrigem, String Destino, String PadraoDestino, String ComportamentoDestino, String Modo, String Owner, String ZipInterno, int CalcOrigem, int CalcDestino) {
        try {

            if (!(Id > 0)) {

//                Constraints = CadastroRotasConstraints.getInstance();
//
//                Constraints.validaInstancias(Instancia);
//                Constraints.validaProcesso(Processo);
//                Constraints.validaPreenchimentoOrigem(Origem);
//                Constraints.validaPreenchimentoDestino(Destino);
            }

            Servico = new CadastroRotasDao(Id, Tabela, Instancia, Praça, Processo, Tipo, Origem, PadraoOrigem, ComportamentoOrigem, Destino, PadraoDestino, ComportamentoDestino, Modo, Owner, ZipInterno, CalcOrigem, CalcDestino);
        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, "Erro ao tentar cadastrar o dado " + ex, "Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void carregarTabela() {
        this.Servico.carregarTabelaRotas();

    }

    public void gravarTxt() {

        try {

            this.Servico.gravarTxt();

        } catch (FileNotFoundException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao tentar gravar o arquivo de rotas " + ex, "Erro arquivo Rotas", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void alterarRegistro() {
        try {

            this.Servico.alterarRegistro();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, "Erro ao tentar alterar o dado " + ex, "Erro ao alterar", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void excluirRegistro() {

        try {

            this.Servico.excluirRegistro();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, "Erro ao tentar excluir o dado " + ex, "Erro ao excluir", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void addRegistro() {

        try {

            this.Servico.addRegistro();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, "Erro ao tentar adicionar o dado " + ex, "Erro ao adicionar", JOptionPane.ERROR_MESSAGE);

        }

    }

    public Set<String> obterListaFTPS() throws IOException {
        try {

            return this.Servico.obterListaNomesSFTP();

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao tentar recuperar os nomes dos FTPS " + ex, "Lista de nomes FTPS", JOptionPane.ERROR_MESSAGE);

        }
        return null;

    }

    public Set<String> obterInstancias() throws IOException {
        try {

            return this.Servico.obterInstancias();

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao tentar recuperar o nome das Instancias " + ex, "Lista de Instancias", JOptionPane.ERROR_MESSAGE);

        }
        return null;

    }

    public List<CadastroRotasModel> getConfiguracoes() {
        return this.Servico.getConfiguracoes();
    }

}
