package br.com.inf.ufg.fanplaylist.configuracao;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author gabriel
 * @version 1.0.0
 *
 *          Classe para guardar as variaveis que buscam o aruivo de excecoes de
 *          acordo com a internacionalizacao.
 */
public final class Internacionalizacao {

    /**
     * Variaveis de internacionalizacao.
     */

    /** Lingua padrao do arquivo. */
    public static final String LINGUA = "pt";

    /** Pais de origem da lingua. */
    public static final String PAIS = "br";

    /** Loca com base na ligua e pais de origem. */
    public static final Locale LOCAL_SELECIONADO = new Locale(LINGUA, PAIS);

    /**
     * Locale para orietacao de fuso horario util nas classe classe UtilDate, e
     * para configurar as Anotacoes de serializacao do Jackson.
     */
    /**
     * Guarda o timezone padrao da aplicacao usado para serializar e deseralizar
     * datas.
     */
    public static final String TIME_ZONE_PADRAO = "America/Sao_Paulo";

    /** Objeto que carrega e busca excecoes do arquivo de execoes. */
    private static ResourceBundle buscadorExcecao;

    /**
     * @return isntancia de {@link ResourceBundle} com o arquivo de configurcao
     *         carregado na memoria.
     */
    public static synchronized ResourceBundle getBuscadorExcecao() {
        if (buscadorExcecao == null) {
            /**
             * Busca o arquivo de excecoes seguindo o padrao do java com base no
             * Locale, por exemplo para LINGUA = "pt"; PAIS = "br" o sistema ira
             * buscar pelo arquivo excecoes_pt_BR.properties dentro da pasta
             * resouces.
             */
            buscadorExcecao = ResourceBundle.getBundle("excecoes",
                    LOCAL_SELECIONADO);
        }
        return buscadorExcecao;
    }

    /** Construtor padrao de classe ultilitaria. */
    private Internacionalizacao() {
    }
}
