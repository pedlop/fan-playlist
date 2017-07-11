package br.com.inf.ufg.fanplaylist.dominio;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author gabriel
 * @version 1.0.0
 * 
 * Classe para representar todas as entidades do sistema.
 * Sendo que nesta colocamos o Id que é comum a todas as entidades
 * do sistema.
 * */
@MappedSuperclass
public abstract class Entidade {
    
    @Id
    @GeneratedValue
    private Long id;
    
    /**
     * Construtor padrão para entidades mapeadas
     * com o HiberNate.
     */
    public Entidade() { }
    
    /**
     * 
     * @return Id único da entidade.
     */
    public Long getId() {
        return id;
    }
}
