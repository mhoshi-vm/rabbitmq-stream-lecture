package com.example.supplier;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties("scs.demo")
record SupplierApplicationProperties(
        @DefaultValue("Hello")
        String message
) {
}
