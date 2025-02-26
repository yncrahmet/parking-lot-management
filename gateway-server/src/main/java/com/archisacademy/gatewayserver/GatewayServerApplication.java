package com.archisacademy.gatewayserver;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

@SpringBootApplication
public class GatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p -> p.path("/microservice/api/v1/parking/reservation/**")
						.filters(f -> f.rewritePath("/microservice/api/v1/parking/reservation(?<segment>.*)", "/api/v1/parking/reservation${segment}"))
						.uri("lb://RESERVATION-SERVICE"))
				.route(p->p.path("/microservice/api/vehicles/**")
						.filters(f->f.rewritePath("/microservice/api/vehicles(?<segment>.*)","/api/vehicles${segment}"))
						.uri("lb://PARKING"))
				.route(p->p.path("/microservice/api/v1/booking/**")
						.filters(f->f.rewritePath("/microservice/api/v1/booking(?<segment>.*)","/api/v1/booking${segment}"))
						.uri("lb://PARKING"))
				.route(p->p.path("/microservice/api/v1/parkingspots/**")
						.filters(f->f.rewritePath("/microservice/api/v1/parkingspots/(?<segment>.*)","/api/v1/parkingspots/${segment}"))
						.uri("lb://PARKING"))
				.route(p->p.path("/microservice/api/parking/**")
						.filters(f->f.rewritePath("/microservice/api/parking(?<segment>.*)","/api/parking${segment}"))
						.uri("lb://PARKING"))
				.route(p->p.path("/microservice/api/users**")
						.filters(f->f.rewritePath("/microservice/api/users(?<segment>.*)","/api/users${segment}"))
						.uri("lb://PARKING"))
				.build();
	}
}
