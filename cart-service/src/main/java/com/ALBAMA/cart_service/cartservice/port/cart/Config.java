package com.ALBAMA.cart_service.cartservice.port.cart;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("productservice.checkstock");
    }

    @Bean
    public DirectExchange userExchange() {
        return new DirectExchange("userservice.authuser");
    }

    @Bean
    public MessageConverter jackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
