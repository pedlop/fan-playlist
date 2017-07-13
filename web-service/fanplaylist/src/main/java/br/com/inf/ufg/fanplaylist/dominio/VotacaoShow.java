package br.com.inf.ufg.fanplaylist.dominio;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * 
 * @author gabriel
 * @version 1.0.0
 * 
 * Entidae para representar a votação
 * associada aos shows.
 */
@Entity
public class VotacaoShow extends Entidade {
    
    /***
     * Show em questão.
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_votocaoshow_show",
            foreignKey = @ForeignKey(name = "fk_votocaoshow_show"))
    private Show show;
    
    /**
     * Coleção de votações associadas
     * ao Show onde cada artista participante
     * no show tem sua votação em separado.
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_votocaoshow_votacoes",
            foreignKey = @ForeignKey(name = "fk_votocaoshow_votacoes"))
    private Collection<VotacaoArtista> votacoes;

    /**
     * Construtor padrão HiberNate.
     */
    public VotacaoShow() { }



    /**
     * 
     * @param show - Show.
     * @param votacoes - Votações de artistas associadas.
     */
    public VotacaoShow(final Show show, final Collection<VotacaoArtista> votacoes) {
        this.show = show;
        this.votacoes = votacoes;
    }
    
    /**
     * 
     * @return show - Show.
     */
    public Show getShow() {
        return show;
    }

    /**
     * 
     * @return votacoes - Votações de artistas associadas.
     */
    public Collection<VotacaoArtista> getVotacoes() {
        return votacoes;
    }
}
