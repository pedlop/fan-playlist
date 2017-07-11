package br.com.inf.ufg.fanplaylist.dto;

import br.com.inf.ufg.fanplaylist.excecao.ExcecaoNegocio;

/**@author gabriel
 * @version 1.0.0
 * 
 * Interface que ira definir metodos que serao
 * obrigatorios para todos DTOs dos de serializacao
 * deserializacao definidos em {@link ObjetoDTO}.
 *
 * @param <T> - Classe do tipo dominio do pacote
 * {@link br.com.pontalsistemas.model.dominio}.
 * 
 * Classe abstrata tambem implmenta interface de padrão
 * de retorno de execoes de maneira que cada excecao
 * pode ser convertida em DTO.
 * */
public interface PadraoRetorno<T> {
    
    /**
     * @param dominio
     *            - Objeto da camada de dominio que devera ser convertido em seu
     *            respectivo DTO.
     *
     * @return - DTO a partir do dominio.
     */
    static public <T>PadraoRetorno<T> converteDominioDto(T dominio) {
        return null;
    }

    /**
     * @return DTO construtor POJO. Metodo que garante que todos os DTO(s)
     *         continuem cumprindo com o padrao POJO, para serializacao e
     *         desserializacao de objetos.
     */
    abstract PadraoRetorno<T> criaDTO();

    /**
     * @param excecao
     *            - Excecao a ser convertida em DTO.
     * @param controllerP
     *            - Controller que esta convertendo a execacao em DTO. Metodo
     *            para a conversao de de excecoes em DTO(s), o que de maneira
     *            obrigatoria faz com que as classes que implementam essa
     *            interface tenham um construtor que receba
     *            {@link ExcecaoNegocio} e chame o super da mesma ja estas
     *            herdam de excecao negocio.
     * @return DTO da excecao.
     */
    PadraoRetorno<T> converteExcecaoNegocio(ExcecaoNegocio excecao,
            String controllerP);

}
