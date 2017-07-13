package br.com.inf.ufg.fanplaylist.dominio;

/**
 * 
 * @author gabriel
 * @version 1.0.0
 * 
 *          Classe com objetivo de padronizar tamanho de nomes campos e etc.
 *          Estas constantes deverão ser usadas nas rotinas de validação dos
 *          dados e definição de colunas de entidade.
 */
public final class ConstanteString {

    /**
     * Tamanho máximo de email.
     */
    public static final int EMAIL = 50;
    
    /**Tamanho 2 caracteres para campos banco.*/
    public static final int SIGLA2 = 2;

    /**Tamanho 3 caracteres para campos banco.*/
    public static final int SIGLA3 = 3;

    /**Tamanho de 3 caracteres para campo banco.*/
    public static final int TEXTO3 = 3;

    /**Tamanho 10 caracteres para campos banco.*/
    public static final int TEXTO10 = 10;

    /**Tamanho 20 caracteres para campos banco.*/
    public static final int TEXTO20 = 20;
    
    /**Tamanho 30 caracteres para campos banco.*/
    public static final int TEXTO30 = 30;

    /**
     * Constante para ser usado nas notações de validação de dados. De modo que
     * o código reflexivo não valide o tamanho de String de tamanho ilimitado.
     */
    public static final int TEXTOILIMITADO = -1;


    /**
     * Regex para validação de email, a ser usada
     * pela {@link ValidadorString}, código espera lanca execeção caso
     * REGEX false.
     * */
    public static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    /**Constate a ser uasada como atributo da anotacao
     *{@link ValidadorString}, para quando o campo que
     *se esta validando nao possui regex.*/
    public static final String SEM_REGEX = "";
    
    /**
     * Constante que captura somente caracteres que nao sao numeros(digitos), a
     * ser usada pela {@link ValidadorString} o codigo que havalia as expressoes
     * lanca execao caso o matchers() retorne true.
     */
    public static final String REGEX_SO_NUMEROS = ".*\\D.*";

    /**
     * Constante que captra somente caracter que sao letras nao admitindo o nome
     * comecar com numeros ou espacos em branco. Usada pela
     * {@link ValidadorString}
     *
     */
    public static final String REGEX_NOME_NAO_VAZIO = "^[0-9\\(\\)\\/\\+ \\-]+$";

}
