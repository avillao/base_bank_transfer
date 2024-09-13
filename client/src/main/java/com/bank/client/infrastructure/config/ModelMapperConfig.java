package com.bank.client.infrastructure.config;

import com.bank.client.domain.entity.Estado;
import com.bank.client.domain.entity.Genero;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        Converter<String, Estado> estadoConverter = new AbstractConverter<>() {
            @Override
            protected Estado convert(String source) {
                return source == null ? null : Estado.valueOfString(source);
            }
        };
        Converter<String, Genero> generoConverter = new AbstractConverter<>() {
            @Override
            protected Genero convert(String source) {
                return source == null ? null : Genero.valueOfString(source);
            }
        };
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.addConverter(estadoConverter);
        modelMapper.addConverter(generoConverter);
        return modelMapper;
    }
}
