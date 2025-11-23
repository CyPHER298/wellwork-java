package br.com.wellwork.gs.configuration;

import br.com.wellwork.gs.service.ConcatIdUsuarioService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioConfiguration {

    @Bean
    public ConcatIdUsuarioService concatIdUsuarioService() {
        return new ConcatIdUsuarioService();
    }
}
