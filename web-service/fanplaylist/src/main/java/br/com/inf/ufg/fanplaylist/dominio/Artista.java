package br.com.inf.ufg.fanplaylist.dominio;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import br.com.inf.ufg.fanplaylist.excecao.NumeroErro;
import br.com.inf.ufg.fanplaylist.util.ValidadorString;

/**
 * 
 * @author  gabriel
 * @version 1.0.0
 * Entidade que representa o artitas dententor do albúm,
 * este que pode ser visto como artista solo, dubla ou mesmo
 * banda.
 */
@Entity
public class Artista extends Entidade {
    
    /**
     * Nome do artista.
     */
    @ValidadorString(
            aceitaNulo = false,
            codigoErro = NumeroErro.ERRO_10,
            tituloMensagem = "Artista10.titulo",
            descricaoMensagem = "Artista10.mensagem",
            regex = ConstanteString.SEM_REGEX,
            nomeClasse = "Artista",
            tamanho = ConstanteString.TEXTO50)
    @Column(length = ConstanteString.TEXTO50)
    private String nome;
    
    /**
     * Caminho relativo da foto associada
     * ao artista.
     */
    @ValidadorString(
            aceitaNulo = false,
            codigoErro = NumeroErro.ERRO_20,
            tituloMensagem = "Artista20.titulo",
            descricaoMensagem = "Artista20.mensagem",
            regex = ConstanteString.SEM_REGEX,
            nomeClasse = "Artista",
            tamanho = ConstanteString.TEXTO50)
    @Column(length = ConstanteString.TEXTO50)
    private String foto;

    /**
     * Coleção albúns associados ao artista.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_artista_album",
            foreignKey = @ForeignKey(name = "fk_artista_album"))
    private Collection<Album> albuns;

    /**
     * Construtor padrão hibernate.
     */
    public Artista() { }

    /**
     * 
     * @param nome - Nome do artista.
     * @param foto - Caminho relativo da foto associada
     * ao artista.
     * @param albuns - Coleção albúns associados ao artista.
     */
    public Artista(String nome, String foto, Collection<Album> albuns) {
        this.nome = nome;
        this.foto = foto;
        this.albuns = albuns;
    }
    
    /**
     * @param regArtista - Instância da entidade que
     * deseja-se atualizar.
     * @param nome - Nome do artista.
     * @param foto - Caminho relativo da foto associada
     * ao artista.
     * @param albuns - Coleção albúns associados ao artista.
     * @return Instância atualiza.
     */
    public Artista atualizaArtista(Artista regArtista, String nome, String foto, Collection<Album> albuns) {
        Artista novoArtista = new Artista(
                nome == null ? regArtista.nome : nome,
                foto == null ? regArtista.foto : foto,
                albuns == null ? regArtista.getAlbuns() : albuns);
        novoArtista.id = regArtista.id;
        return novoArtista;
    }
    
    /**
     * 
     * @return Nome do artista.
     */
    public String getNome() {
        return nome;
    }

    /**
     * 
     * @return Caminho relativo da foto associada
     * ao artista.
     */
    public String getFoto() {
        return foto;
    }

    /**
     * 
     * @return Coleção albúns associados ao artista.
     */
    public Collection<Album> getAlbuns() {
        return albuns;
    }
}
