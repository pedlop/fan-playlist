package br.com.inf.ufg.fanplaylist.repositorio;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 
 * @author  gabriel
 * @version 1.0.0
 * 
 * Classe base para teste das interfaces de repositorio.
 */
@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public abstract class RepositorioTeste {
    
    /**GERENCIADOR DE ENTIDADE.*/
    @Autowired
    protected TestEntityManager testEntityManager;
    
}
