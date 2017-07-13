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
 * 
 * Classe album que detem uma coleção de musicas
 * mais as demais informações inerentes ao albúm artistico.
 */

@Entity
public class Album extends Entidade {
    
    /**
     * Titulo do albúm.
     */
    @ValidadorString(
            aceitaNulo = false,
            codigoErro = NumeroErro.ERRO_10,
            tituloMensagem = "Album10.titulo",
            descricaoMensagem = "Album10.mensagem",
            regex = ConstanteString.SEM_REGEX,
            nomeClasse = "Album",
            tamanho = ConstanteString.TEXTO50)
    @Column(length = ConstanteString.TEXTO50)
    private String titulo;
    
    /**
     * Ano de criação do albúm.
     */
    @ValidadorString(
            aceitaNulo = false,
            codigoErro = NumeroErro.ERRO_20,
            tituloMensagem = "Album20.titulo",
            descricaoMensagem = "Album20.mensagem",
            regex = ConstanteString.SEM_REGEX,
            nomeClasse = "Album",
            tamanho = 0)
    @Column
    private Integer ano;

    /**
     * Caminho relativo no 
     * disco para a foto relacionada ao albúm.
     */
    @ValidadorString(
            aceitaNulo = false,
            codigoErro = NumeroErro.ERRO_30,
            tituloMensagem = "Album30.titulo",
            descricaoMensagem = "Album30.mensagem",
            regex = ConstanteString.SEM_REGEX,
            nomeClasse = "Album",
            tamanho = ConstanteString.TEXTO50)
    @Column(length = ConstanteString.TEXTO50)
    private String fotoAlbum;
    
    /**
     * Relacionamento de muitos para um com a entidade
     * música.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_album_musica",
            foreignKey = @ForeignKey(name = "fk_album_musica"))
    private Collection<Musica> musicas;
    
    /**
     * Construtor padrão HiberNate.
     */
    public Album() { }

    /**
     * 
     * @param titulo - Título do albúm.
     * @param ano - Ano de criação do albúm.
     * @param fotoAlbum - Caminho relativo da imagem no disco.
     * @param musicas - Coleção de músicas associadas ao albúm.
     */
    public Album(final String titulo, final Integer ano, final String fotoAlbum,
            final Collection<Musica> musicas) {
        this.titulo = titulo;
        this.ano = ano;
        this.fotoAlbum = fotoAlbum;
        this.musicas = musicas;
    }
    
    /***
     * 
     * @param regAlbum - Instância da entidade que deseja-se
     * atualizar.
     * @param titulo - Título do albúm.
     * @param ano - Ano de criação do albúm.
     * @param fotoAlbum - Caminho relativo da imagem no disco.
     * @param musicas - Coleção de músicas associadas ao albúm.
     * @return Instância de de albúm com o registro atualizado. 
     */
    public Album atualizaAlbum(final Album regAlbum, String titulo,
            final Integer ano, final String fotoAlbum,
            final Collection<Musica> musicas) {
        Album novoAlbum  = new Album(
                titulo == null ? regAlbum.titulo : titulo,
                ano == null ? regAlbum.ano: ano,
                fotoAlbum == null ? regAlbum.fotoAlbum : fotoAlbum,
                musicas == null ? regAlbum.getMusicas() : musicas);
        novoAlbum.id = regAlbum.id;
        return novoAlbum;
    }
    
    /**
     * 
     * @return Título do albúm
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * 
     * @return Ano de criação do albúm.
     */
    public Integer getAno() {
        return ano;
    }

    /**
     * 
     * @return Caminho relativo da imagem.
     */
    public String getFotoAlbum() {
        return fotoAlbum;
    }

    /**
     * 
     * @return Coleção de músicas associadas ao albúm.
     */
    public Collection<Musica> getMusicas() {
        return musicas;
    }
}
