package com.provys.testws;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
@OpenAPIDefinition(
        info = @Info(
                title = "Simple test",
                version = "1.0",
                description = "Simple test of JAX-RS without any dependencies"
        ),
        servers = {@Server(url = "/testws/api")})
public class TestWsApplication extends Application {
}
