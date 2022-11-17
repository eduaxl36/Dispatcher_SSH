/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_kantar_dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Eduardo.Fernando
 */
public class ConfiguracoesDao {

    FTPConnectionDao ConexaoDaoSFTPConfiguracao=null;

    public ConfiguracoesDao() throws IOException {
        
         ConexaoDaoSFTPConfiguracao=new FTPConnectionDao();
        
    }

    public String obterConfiguracaoPorChave(String Chave) throws FileNotFoundException, IOException {

        BufferedReader Leitor = new BufferedReader(new FileReader(new File("Cf/SFTPConfig.csv")));
        String Linha = Leitor.readLine();
        Map Configuracoes = new LinkedHashMap();

        while (Linha != null) {

            String Dados[] = Linha.split(";");

            Configuracoes.put(Dados[0], Dados[1]);

            Linha = Leitor.readLine();

        }

        if (Configuracoes.get(Chave) == null) {

            throw new IllegalArgumentException("Arquivo CF : houve um erro de chave ou integridade do arquivo CF, verifique o arquivo!");

        }

        return Configuracoes.get(Chave).toString();

    }

    public Map obterConfiguracoesPorSFTP(String SFTP) throws IOException {

        Map Configuracoes = obterConfiguracoesGerais();
        Map Retorno = new LinkedHashMap();

        Configuracoes.forEach((x, y) -> {

            if (SFTP.equals("SFTP-ARG")) {

                if (x.toString().contains("ar")) {

                    Retorno.put(x, y);

                }

            } else {

                if (!(x.toString().contains("ar"))) {

                    Retorno.put(x, y);

                }

            }

        });

        return Retorno;

    }

    public Map obterConfiguracoesGerais() throws FileNotFoundException, IOException {

        BufferedReader Leitor = new BufferedReader(new FileReader(new File("Cf/SFTPConfig.csv")));
        String Linha = Leitor.readLine();
        Map ConfiguracoesGerais = new LinkedHashMap();

        while (Linha != null) {

            String Dados[] = Linha.split(";");

            ConfiguracoesGerais.put(Dados[0], Dados[1]);

            Linha = Leitor.readLine();

        }

        return ConfiguracoesGerais;
    }

    public void gravarArquivoCf(String Regiao, String Host, String User, String Senha) throws FileNotFoundException, IOException {

        Map Configuracoes = obterConfiguracoesGerais();
     

        if (Regiao.equals("SFTP-ARG")) {
   

            try ( PrintWriter Pw = new PrintWriter(new File("Cf/Cf.csv"))) {

                Map ConfiguracoesRet = new LinkedHashMap();

                Configuracoes.forEach((x, y) -> {

                    if (x.equals("user_sftpar")) {

                        ConfiguracoesRet.put("user_sftpar", User);

                    } else if (x.equals("senha_sftpar")) {

                        ConfiguracoesRet.put("senha_sftpar", Senha);

                    } else if (x.equals("host_sftpar")) {

                        ConfiguracoesRet.put("host_sftpar", Host);
                    } else {

                        ConfiguracoesRet.put(x, y);

                    }

                });

                ConfiguracoesRet.forEach((chave, valor) -> {

                    Pw.println(chave + ";" + valor);

                });
            }

        }

        
        if (Regiao.equals("SFTP Realtime")) {

            try ( PrintWriter Pw = new PrintWriter(new File("Cf/Cf.csv"))) {

                Map ConfiguracoesRet = new LinkedHashMap();

                Configuracoes.forEach((x, y) -> {

                    if (x.toString().contains("user_sitcal")) {

                        ConfiguracoesRet.put("user_sitcal", User);

                    } else if (x.toString().contains("senha_sitcal")) {

                        ConfiguracoesRet.put("senha_sitcal", Senha);

                    } else if (x.toString().contains("host_sitcal")) {

                        ConfiguracoesRet.put("host_sitcal", Host);
                    } else {

                        ConfiguracoesRet.put(x, y);

                    }

                });

                ConfiguracoesRet.forEach((chave, valor) -> {

                    Pw.println(chave + ";" + valor);

                });
            }

        }

   
    }
    
    
        public Set<String> obterListaFTPS() throws IOException {
            
       
        
        Set<String> Instancias = new LinkedHashSet();
        
        ConexaoDaoSFTPConfiguracao.ObterConfiguracoes().forEach(x->{
        
        Instancias.add(x.getOwnerFTP());
        
        });
        
        return Instancias;
    }
    
    public static void main(String[] args) throws IOException {
        
        
//        new ConfiguracoesDao().obterListaFTPS();
        
        
    }

}

