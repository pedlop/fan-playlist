package br.com.inf.ufg.fanplaylist.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.com.inf.ufg.fanplaylist.excecao.NumeroErro;
import br.com.inf.ufg.fanplaylist.util.ValidadorString;

/**
 * 
 * @author gabriel
 * @version 1.0.0
 * 
 * Classe que representa uma musica que deverá
 * estar associada a um albúm.
 */
@Entity
public class Musica extends Entidade {
    
    /**
     * Nome da música.
     */
    @ValidadorString(
            aceitaNulo = false,
            codigoErro = NumeroErro.ERRO_10,
            tituloMensagem = "Musica10.titulo",
            descricaoMensagem = "Musica10.mensagem",
            regex = ConstanteString.SEM_REGEX,
            nomeClasse = "Musica",
            tamanho = ConstanteString.TEXTO50)
    @Column(length = ConstanteString.TEXTO50)
    private String nome;
    
    /**
     * Construtor padrão hibernate.
     */
    public Musica() { }
    
    /**
     * 
     * @param nome - Nome da música.
     */
    public Musica(final String nome) {
        this.nome = nome;
    }
    
   /**
    * 
    * @param regMusica - Registo da base de dados
    * que deseja se atualizar.
    * @param novoNomeMusica - Novo no nome de música.
    * @return Música com id do registro e nome atualizado.
    */
   public Musica atualiza(final Musica regMusica, final String novoNomeMusica) {
       Musica novaMusica = new Musica(novoNomeMusica);
       novaMusica.id = regMusica.id;
       return novaMusica;
   }

   /**
    * 
    * @return nome - Nome da música.
    */
   public String getNome() {
        return nome;
   }   
}
