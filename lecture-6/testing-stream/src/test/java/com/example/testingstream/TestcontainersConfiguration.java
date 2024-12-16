package com.example.testingstream;

import com.rabbitmq.stream.Address;
import com.rabbitmq.stream.Environment;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.boot.autoconfigure.amqp.EnvironmentBuilderCustomizer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.rabbit.stream.support.StreamAdmin;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

    @Bean
    @ServiceConnection
    RabbitMQContainer rabbitContainer() {
        return new RabbitMQContainer(DockerImageName.parse("rabbitmq:management-alpine"))
                .withExposedPorts(5672, 15672, 5552)
                .withEnv("RABBITMQ_SERVER_ADDITIONAL_ERL_ARGS", "-rabbitmq_stream advertised_host localhost")
                .withPluginsEnabled("rabbitmq_stream");
    }

    @Bean
    DynamicPropertyRegistrar dynamicPropertyRegistrar(RabbitMQContainer rabbitMQContainer) {
        return registry -> {
            registry.add("spring.rabbitmq.stream.username", rabbitMQContainer::getAdminUsername);
            registry.add("spring.rabbitmq.stream.password", rabbitMQContainer::getAdminPassword);
            registry.add("spring.rabbitmq.stream.port", () -> rabbitMQContainer.getMappedPort(5552));
            registry.add("spring.rabbitmq.stream.virtual_host", () -> "/");
        };
    }

    @Bean
    EnvironmentBuilderCustomizer environmentBuilderCustomizer(RabbitMQContainer rabbitMQContainer) {
        return builder -> builder.addressResolver(add -> new Address("localhost", rabbitMQContainer.getMappedPort(5552)));
    }

    @Bean
    StreamAdmin streamAdmin(Environment environment) {
        return new StreamAdmin(environment, streamCreator -> streamCreator.stream("foo").create());
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout");
    }

    @Bean
    Binding binding(FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(new Queue("foo")).to(fanoutExchange);
    }

}
