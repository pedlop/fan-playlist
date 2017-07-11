package br.com.inf.ufg.fanplaylist.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.inf.ufg.fanplaylist.dominio.ConstanteString;
import br.com.inf.ufg.fanplaylist.excecao.ExcecaoNegocio;
import br.com.inf.ufg.fanplaylist.excecao.NumeroErro;


/**
 * @author gabriel
 * @version 1.0.0
 * Classe de validacao para dados em String, email
 *  etc.*/
public final class UtilValidador {

    /**
     * @param conteudo
     *            String qualquer.
     * @return false se for nulo ou vazio, true senao.
     */
    public static boolean eNuloOuVazio(final String conteudo) {
        if (conteudo == null || conteudo.length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * @param o
     *            Objeto qualquer.
     * @return true caso o objeto nulo, false senao.
     */
    public static boolean eNulo(final Object o) {
        if (o == null) {
            return true;
        }
        return false;
    }

    /**
     * @param o
     *            String qualquer.
     * @return Propia String ou string vazia.
     */
    public static String casoNuloRetornaStringVazia(final String o) {
        if (eNulo(o)) {
            return "";
        }
        return o;
    }

    /**
     * @param o
     *            - Objeto qualquer que se deseja pegar o conteudo.
     * @param T
     *            - Tipo do objeto que esta sendo avaliado.
     * @param <T>
     *            Tipo considerado pela funcao.
     * @return Null se o Objeto for nulo, ou o propio objeto caso o mesmo nao
     *         seja nulo.
     */
    public static <T> T casoNuloRetornaNull(final Class<?> T, final T o) {
        if (eNulo(o)) {
            return null;
        }
        return o;
    }

    /**
     * @param o
     *            - Objeto qualquer que se deseja pegar o conteudo.
     * @return Null se o Objeto for nulo, ou o propio objeto caso o mesmo nao
     *         seja nulo.
     */
    public static Long casoNuloRetornaNull(final Long o) {
        return casoNuloRetornaNull(Long.class, o);
    }

    /**
     * @param o
     *            - Objeto qualquer que se deseja pegar o conteudo.
     * @return Null se o Objeto for nulo, ou o propio objeto caso o mesmo nao
     *         seja nulo.
     */
    public static String casoNuloRetornaNull(final String o) {
        return casoNuloRetornaNull(String.class, o);
    }

    /**
     * @param o
     *            - Objeto qualquer que se deseja pegar o conteudo.
     * @return Null se o Objeto for nulo, ou o propio objeto caso o mesmo nao
     *         seja nulo.
     */
    public static Integer casoNuloRetornaNull(final Integer o) {
        return casoNuloRetornaNull(Integer.class, o);
    }

    /**
     * @param o
     *            - Objeto qualquer que se deseja pegar o conteudo.
     * @return Null se o Objeto for nulo, ou o propio objeto caso o mesmo nao
     *         seja nulo.
     */
    public static BigDecimal casoNuloRetornaNull(final BigDecimal o) {
        return casoNuloRetornaNull(BigDecimal.class, o);
    }

    /**
     * @param o
     *            - Objeto qualquer que se deseja pegar o conteudo.
     * @return Null se o Objeto for nulo, ou o propio objeto caso o mesmo nao
     *         seja nulo.
     */
    public static Date casoNuloRetornaNull(final Date o) {
        return casoNuloRetornaNull(Date.class, o);
    }

    /**
     * @param o
     *            - Objeto qualquer que se deseja pegar o conteudo.
     * @return Null se o Objeto for nulo, ou o propio objeto caso o mesmo nao
     *         seja nulo.
     */
    public static Boolean casoNuloRetornaNull(final Boolean o) {
        return casoNuloRetornaNull(Boolean.class, o);
    }

    /**
     * @param o
     *            - Objeto qualquer que se deseja pegar o conteudo.
     * @return Null se o Objeto for nulo, ou o propio objeto caso o mesmo nao
     *         seja nulo.
     */
    public static List<String> casoNuloRetornaNull(final List<String> o) {
        return casoNuloRetornaNull(o.getClass(), o);
    }

    /**
     * Verifica se o campo e vaizio e nao for verifica se a quantidade de
     * caracteres esta correta.
     *
     * @param nomeCampo
     *            - Nome do campo que esta sendo validado.
     * @param campo
     *            - Conteudo do campo.
     * @param qtdCaracter
     *            - Quantidade de caracteres permitida no campo.
     * @throws ExcecaoNegocio
     *             - Excecao indicando que o campo e invalido.
     */
    public static void verificaVazioQtdCarater(final String nomeCampo,
            final String campo, final int qtdCaracter) throws ExcecaoNegocio {
        int tamCampo = UtilValidador.casoNuloRetornaStringVazia(campo).length();
        if (tamCampo == 0 || tamCampo > qtdCaracter) {
            throw new ExcecaoNegocio(NumeroErro.ERRO_60, "UtilValidador",
                    "UtilValidador60.titulo", "UtilValidador60.mensagem",
                    nomeCampo);
        }
    }

    /**
     * Programacao reflexiva da validacao da interface de anotacao
     * {@link ValidadorString}, neste metodo pode ser passado qualquer numero de
     * objetos onde passado o objeto e buscada a interface da anotacao em todos
     * os atributos da classe e das eventuais super classes do objeto.
     *
     * Seguindo os parametros da anotacao a validacao devera ser fetia antes dos
     * objetos serem persistidos na base de dados afim de garantir que o dado
     * sera persistido com todos seguindo as restricoes do banco de dados e
     * eventuais registricoes que tenham relacao com regras de negocio.
     *
     * @param objetos
     *            - Array de objetos onde sera verificada a presenca da da
     *            anotacao {@link ValidadorString}.
     * @throws ExcecaoNegocio
     *             - Excecao que pode ser lancada em caso do campo ser invalido.
     */
    @SuppressWarnings({ "rawtypes" })
    public static void validaObjetos(final Object... objetos)
            throws ExcecaoNegocio {
        /**
         * Passa o codigo reflexivo por todos os objetos do array.
         */
        for (int i = 0; i < objetos.length; i++) {
            /**
             * Verifica se de fato foi passado um objeto ou se o parametro foi
             * null.
             */
            if (!eNulo(objetos[i])) {
                /**
                 * Pega a classe do objeto e passa por todos os
                 * campos(atributos) do objeto, visto que a anotacao que estamos
                 * tratando com a nossa anotacao e tratada pelo nosso codigo
                 * reflexivo.
                 */
                Class clazz = objetos[i].getClass();
                List<Field> campos = new ArrayList<>();
                campos = UtilValidador.recuperaAtributos(campos, clazz);
                for (Field campo : campos) {
                    /**
                     * Seta que o acesso do campo sobreecrevera as restricoes do
                     * java, por exmplo se a variavel e privada a mesma podera
                     * ser acessada diretamente pelo codigo reflexivo.
                     */
                    campo.setAccessible(true);
                    if (campo.isAnnotationPresent(ValidadorString.class)) {
                        try {
                            /**
                             * Pega o conteudo dos atributos da anotacao caso a
                             * mesma seja encontrada no campo que esta sendo
                             * avaliado.
                             */
                            int tamanho = campo
                                    .getAnnotation(ValidadorString.class)
                                    .tamanho();
                            String regex = campo
                                    .getAnnotation(ValidadorString.class)
                                    .regex();
                            boolean aceitaNulo = campo
                                    .getAnnotation(ValidadorString.class)
                                    .aceitaNulo();
                            boolean logicaRegex = campo
                                    .getAnnotation(ValidadorString.class)
                                    .logicaRegex();
                            int codigoErro = campo
                                    .getAnnotation(ValidadorString.class)
                                    .codigoErro();
                            String nomeClasse = campo
                                    .getAnnotation(ValidadorString.class)
                                    .nomeClasse();
                            String tituloMensagem = campo
                                    .getAnnotation(ValidadorString.class)
                                    .tituloMensagem();
                            String descricaoMensagem = campo
                                    .getAnnotation(ValidadorString.class)
                                    .descricaoMensagem();

                            try {
                                Object conteudoObjeto = campo.get(objetos[i]);
                                /**
                                 * Verifica se o objeto realmente e do tipo
                                 * String continua as validacoes.
                                 */
                                if (String.class.isInstance(conteudoObjeto)) {
                                    String conteudoString = (String) conteudoObjeto;
                                    /**
                                     * Verifica se o campo passado e maior que o
                                     * tamanho permitido.
                                     */
                                    if (tamanho != ConstanteString.TEXTOILIMITADO
                                            && conteudoString
                                                    .length() > tamanho) {
                                        throw new ExcecaoNegocio(codigoErro,
                                                nomeClasse, tituloMensagem,
                                                descricaoMensagem,
                                                conteudoString);
                                    }
                                    /**
                                     * Verifica se a string possui uma regex de
                                     * validacao.
                                     */
                                    if (regex.length() > 0) {
                                        Pattern pattern = Pattern.compile(regex,
                                                Pattern.CASE_INSENSITIVE);
                                        Matcher matcher = pattern
                                                .matcher(conteudoString);
                                        /**
                                         * Caso o objeto nao passe na expressao.
                                         */
                                        if (!(matcher
                                                .matches() == logicaRegex)) {
                                            throw new ExcecaoNegocio(codigoErro,
                                                    nomeClasse, tituloMensagem,
                                                    descricaoMensagem,
                                                    conteudoString);
                                        }
                                    }
                                } else {
                                    /**
                                     * CASO O OBJETO NAO SEJA UMA INSTANCIA DE
                                     * STRING OU ESTEJA NULO.
                                     */
                                    if (UtilValidador.eNulo(conteudoObjeto)
                                            && !(aceitaNulo)) {
                                        throw new ExcecaoNegocio(codigoErro,
                                                nomeClasse, tituloMensagem,
                                                descricaoMensagem);
                                    }
                                }
                                /**
                                 * O metodo get lanca uma execao quando o objeto
                                 * que se procura e null.
                                 */
                            } catch (NullPointerException e) {
                                /**
                                 * Caso seja nula a instacia vefica se o objeto
                                 * aceita valores nulos se nao aceitar lanca a
                                 * excecao da anotacao.
                                 */
                                if (!(aceitaNulo)) {
                                    throw new ExcecaoNegocio(codigoErro,
                                            nomeClasse, tituloMensagem,
                                            descricaoMensagem);
                                }
                            }

                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    } // FIM DO PRIMEIRO IF.
                } // FIM DO FOR DOS CAMPOS.
            } // FIM DO FOR DOS OBJETOS.
        } // FIM DO IF QUE VERIFICA SE O ITEM DO VETOR DE OBJETOS E NULO.
    }

    /**
     * Busca recursivamente a lista de todos os atributos de um determinado
     * objeto. Metodo privado pois so usamos por equanto reflexao nessa parte do
     * sistema, caso precisar usar reflexao em outra parte do sistema procurar
     * uma lib de ultilitarios de reflexao em Java ou criar a UtilReflexao.
     *
     * @param listaAtributos
     *            - Lista de Atributos que sera passada para preenche os campos.
     *
     * @param objeto
     *            - Objeto qualquer de onde sera rercusivamente buscado todos
     *            atributos declarado na sua classe e na superclasse que
     *            eventualmete ele herde.
     *
     * @return List<Field> - Lista com todos os atributos declaradados na
     *         classe, sendo que o mesmo busca atributos publicos e privado.
     */
    private static List<Field> recuperaAtributos(List<Field> listaAtributos,
            Class<?> objeto) {
        listaAtributos.addAll(Arrays.asList(objeto.getDeclaredFields()));

        if (objeto.getSuperclass() != null) {
            listaAtributos = recuperaAtributos(listaAtributos,
                    objeto.getSuperclass());
        }
        return listaAtributos;
    }

   /**Classe Util nao pode ser instancida.*/
   private UtilValidador() { }
}
