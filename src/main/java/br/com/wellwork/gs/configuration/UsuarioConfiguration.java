package br.com.wellwork.gs.configuration;

import br.com.wellwork.gs.service.ConcatIdUsuarioService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

public class UsuarioConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "service.usuario", name = "concat-id-usuario", havingValue = "!enabled")
    public ConcatIdUsuarioService concatIdUsuarioService() {
        return new ConcatIdUsuarioService();
    }
}
