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
 * Classe que representa uma ciade, afim de agrupar shows
 * de acordo com a cidade.
 */
@Entity
public class Cidade extends Entidade {
    
    
    @ValidadorString(
            aceitaNulo = false,
            codigoErro = NumeroErro.ERRO_10,
            tituloMensagem = "Cidade10.titulo",
            descricaoMensagem = "Cidade10.mensagem",
            nomeClasse = "Usuario",
            logicaRegex = true,
            regex = ConstanteString.SEM_REGEX,
            tamanho = ConstanteString.TEXTO50)
    @Column(length = ConstanteString.TEXTO50)
    private String nome;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_cidade_show",
            foreignKey = @ForeignKey(name = "fk_cidade_show"))
    private Collection<Show> shows;

    /**
     * Construtor padrão HiberNate.
     */
    public Cidade() { }

    /**
     * 
     * @param nome - Nome da cidade.
     * @param shows - Coleção de shows associados a cidade.
     */
    public Cidade(String nome, Collection<Show> shows) {
        this.nome = nome;
        this.shows = shows;
    }

    /**
     * 
     * @return nome - Nome da cidade.
     */
    public String getNome() {
        return nome;
    }

    /**
     * 
     * @return shows - Coleção de shows associados a cidade.
     */
    public Collection<Show> getShows() {
        return shows;
    }
}
