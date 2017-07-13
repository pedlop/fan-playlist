package br.com.inf.ufg.fanplaylist.dto;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * 
 * @author  gabriel
 * @version 1.0.0
 * 
 * Caso de teste de serialização e deserialização dos
 * DTO(s).
 */
@RunWith(Suite.class)
@SuiteClasses({
    UsuarioDTOTeste.class
})
public class DtoTeste {
    
    /**String correspondente a serializacao Json da classe de Excecao Negocio,
     * que se deseja omitir para exemplicar os parametros das API(s)*/
    public static String jsonExcecaoNegocio = ",\"codigoErro\":0,\"nomeClasse\":null,\"tituloMensagem\":null,\"descricaoMensagem\":null,\"controller\":null";
    
    /**Testa e demonsta as possiveis formas de serializacao e desserializacao para cada
     * cada objeto que implementa  interface ObjetoDTO do sistema.*/
    public static void serializaDesserializaObjetos(Object o) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String jsonComExcecaoNegocio  = mapper.writeValueAsString(o);
        String jsonSemExcecaoNegocio  = jsonComExcecaoNegocio.replace(jsonExcecaoNegocio, "");
        System.out.println("\t" + o.getClass().getSimpleName() + "\n\t" + jsonComExcecaoNegocio);
        System.out.println("\t" + o.getClass().getSimpleName() + "\n\t" + jsonSemExcecaoNegocio +"\n" );
        o = mapper.readValue(jsonComExcecaoNegocio, o.getClass());
        o = mapper.readValue(jsonSemExcecaoNegocio, o.getClass());
        System.out.println("\t" + DtoTeste.converteDTOAtributo(o.getClass().getSimpleName(), o, false) + "\n");
        System.out.println("\t" + DtoTeste.converteDTOAtributo(o.getClass().getSimpleName(), o, true) + "\n\n\n\n");
    }
    
    /**@param nomeAtributo - Nome do atributo que deseja aplicar para o objeto. Ex.: LoginDTO
     * @param objeto que se deseja serializar e associar aquele nome de atributo, por exemplo uma
     * instancia de loginDTO.
     * @param excecao se o json gerado devera vir com ou sem os atributos da classe excecao de negoico,
     * que e super classe de todos os DTO(s).
     * @return Para excecao igual a true temos o seguinte modelo de retorno:
     * loginDTO:{"login":"LoginTeste","senha":"$2a$10$Q0.R5GU7pA1HRa0XO1D2Oe1rwgB0YR2D0RaOV7ltrogL0e8hgBiQ.","tipoUsuario":"APLICACAO","codigoErro":0,"nomeClasse":null,"tituloMensagem":null,"descricaoMensagem":null,"controller":null}*/
    public static String converteDTOAtributo( String nomeAtributo, Object dto,  boolean excecao ) throws Exception {
         ObjectMapper mapper = new ObjectMapper();
         nomeAtributo = "\"" + nomeAtributo;
         nomeAtributo = nomeAtributo + "\"";
         nomeAtributo +=  ":";
         nomeAtributo += excecao == true ? mapper.writeValueAsString(dto) : mapper.writeValueAsString(dto).replace(jsonExcecaoNegocio, ""); 
         return nomeAtributo;
    }

}
