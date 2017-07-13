package br.com.inf.ufg.fanplaylist.dominio;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.inf.ufg.fanplaylist.excecao.NumeroErro;
import br.com.inf.ufg.fanplaylist.util.ValidadorString;

/**
 * 
 * @author gabriel
 * @version 1.0.0
 * 
 *          Classe para reprensar o show, mais os artistas associados que por
 *          sua vez detém os albúns associados ao mesmo, de onde podrão vir as
 *          músicas votdas que tocaram no show.
 */
@Entity
public class Show extends Entidade {

    /**
     * Nome do show.
     */
    @ValidadorString(
            aceitaNulo = false,
            codigoErro = NumeroErro.ERRO_10,
            tituloMensagem = "Show10.titulo",
            descricaoMensagem = "Show10.mensagem",
            regex = ConstanteString.SEM_REGEX,
            nomeClasse = "Show",
            tamanho = ConstanteString.TEXTO50)
    @Column(length = ConstanteString.TEXTO50)
    private String nomeShow;

    /**
     * Endereço do show.
     */
    @ValidadorString(
            aceitaNulo = false,
            codigoErro = NumeroErro.ERRO_20,
            tituloMensagem = "Show20.titulo",
            descricaoMensagem = "Show20.mensagem",
            regex = ConstanteString.SEM_REGEX,
            nomeClasse = "Show",
            tamanho = ConstanteString.TEXTO50)
    @Column(length = ConstanteString.TEXTO50)
    private String local;

    /**
     * Data de início do show.
     */
    @Column
    @Temporal(TemporalType.DATE)
    private Date data;
    
    /**
     * Horário do show.
     */
    @ValidadorString(
            aceitaNulo = false,
            codigoErro = NumeroErro.ERRO_40,
            tituloMensagem = "Show40.titulo",
            descricaoMensagem = "Show40.mensagem",
            regex = ConstanteString.SEM_REGEX,
            nomeClasse = "Show",
            tamanho = ConstanteString.TEXTO50)
    @Column(length = ConstanteString.TEXTO50)
    private String horario;
 
    /**
     * Coleção de aristas associados ao Show.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_show_artista",
            foreignKey = @ForeignKey(name = "fk_show_artista"))
    private Collection<Artista> artistas;

    /**
     * Construtor padrão do HiberNate.
     */
    public Show() { }

    /**
     * 
     * @param nomeShow - Nome do show.
     * @param local - Endereço do show.
     * @param data - Data de início do show.
     * @param horario - Horário do show.
     * @param artistas - Coleção de aristas associados ao Show.
     */
    public Show(final String nomeShow, final String local, final Date data,
            final String horario, final Collection<Artista> artistas) {
        this.nomeShow = nomeShow;
        this.local = local;
        this.data = data;
        this.horario = horario;
        this.artistas = artistas;
    }
    
    /**
     * 
     * @param regShow - Instância de show que
     * será atualizada.
     * @param nomeShow - Nome do show.
     * @param local - Endereço do show.
     * @param data - Data de início do show.
     * @param horario - Horário do show.
     * @param artistas - Coleção de aristas associados ao Show.
     * @return Instância de show atualizada.
     */
    public Show atualizaShow(final Show regShow,final String nomeShow,
            final String local, final Date data,
            final String horario, final Collection<Artista> artistas) {
        Show novoShow = new Show(
                nomeShow == null ? regShow.nomeShow : nomeShow,
                local == null ? regShow.local : local,
                data == null ? regShow.data : data,
                horario == null ? regShow.horario : horario,
                artistas == null ? regShow.getArtistas() : artistas);
        novoShow.id = regShow.id;
        return novoShow;
    }
    
    /**
     * 
     * @return nomeShow - Nome do show.
     */
    public String getNomeShow() {
        return nomeShow;
    }

    /**
     * 
     * @return local - Endereço do show.
     */
    public String getLocal() {
        return local;
    }

    /**
     * 
     * @return data - Data de início do show.
     */
    public Date getData() {
        return data;
    }

    /**
     * 
     * @return horario - Horário do show.
     */
    public String getHorario() {
        return horario;
    }

    /**
     * 
     * @return artistas - Coleção de aristas associados ao Show.
     */
    public Collection<Artista> getArtistas() {
        return artistas;
    }
}
