package br.com.inf.ufg.fanplaylist.dominio;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/***
 * 
 * @author  gabriel
 * @version 1.0.0
 * 
 * Votacao associada a determinado artista,
 * onde o usuario pode votar em qualquer uma das
 * musicas associadas ao albúm do artista em questão.
 */
@Entity
public class VotacaoArtista extends Entidade { 
    
    /**
     * Artista associado a votação.
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_votocaoartista_artista",
            foreignKey = @ForeignKey(name = "fk_votocaoartista_artista"))
    public Artista artista;
    
    /**
     * Coleção de votos da própia votação.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_votocaoartista_voto",
            foreignKey = @ForeignKey(name = "fk_votocaoartista_voto"))
    public Collection<Voto> votos;

    /**
     * Construtor padrão HiberNate.
     */
    public VotacaoArtista() { }

    
    /**
     * 
     * @param artista - Artista associado a votação.
     * @param votos - Coleção de votos da própia votação.
     */
    public VotacaoArtista(final Artista artista, final Collection<Voto> votos) {
        this.artista = artista;
        this.votos = votos;
    }    
    
    /**
     * 
     * @return artista - Artista associado a votação.
     */
    public Artista getArtista() {
        return artista;
    }

    /**
     * 
     * @return votos - Coleção de votos da própia votação.
     */
    public Collection<Voto> getVotos() {
        return votos;
    } 
}
