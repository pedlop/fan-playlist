package br.com.inf.ufg.fanplaylist.configuracao;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Configuracao de CROS geral da aplicacao. Esta configuracao e a que sera
 * levada em consideracao, pelo SpringSecurity, ao levantar o WebService
 * juntamente com os filtros de autenticacao.
 */
@Configuration
@EnableWebMvc
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class ConfiguracaoCROS extends WebMvcConfigurerAdapter {

    /**
     * Tempo maximo em milissegundo que o cliente pode esperar pelo resultado de
     * uma requisicao.
     */
    private static final int TEMPO_MAX_ESPERA_REQUISICAO = 3600;

    /*** Numero de dias em cache. */
    private static final int NUM_DIA_CACHE = 7;

    /** Pastas de livre acesso da aplicacao. */
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/public/" };

    /**
     * @param registry
     *            - Configuracao de cros da aplicacao.
     */
    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("POST", "GET").allowCredentials(true)
                .maxAge(TEMPO_MAX_ESPERA_REQUISICAO);
    }
}
