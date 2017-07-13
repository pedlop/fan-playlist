package br.com.inf.ufg.fanplaylist.dominio;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * 
 * @author  gabriel
 * @version 1.0.0
 * 
 * Classe que reprenseta o voto do usúario
 * em determinado artista.
 */
@Entity
public class Voto extends Entidade {
    
    /**
     * Usuario associado ao voto.
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_voto_musica",
            foreignKey = @ForeignKey(name = "fk_voto_musica"))
    private Usuario usuario;
    
    /**
     * Musica associada ao voto de usuario.
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_voto_musica",
            foreignKey = @ForeignKey(name = "fk_voto_musica"))
    private Musica musica;

    /**
     * Construtor padrao HiberNate.
     */
    public Voto() { }

    /**
     * 
     * @param usuario - Usuario associado ao voto.
     * @param musica - Musica em que o usuario voutou.
     */
    public Voto(Usuario usuario, Musica musica) {
        this.usuario = usuario;
        this.musica = musica;
    }

    /**
     * 
     * @return Usuario associado ao voto.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * 
     * @return Musica em que o usuario voutou.
     */
    public Musica getMusica() {
        return musica;
    }
}
