package iset.com.app;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class GateawayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GateawayServiceApplication.class, args);
	}
	
	@Bean
	DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient rdc,DiscoveryLocatorProperties dlp) {
	return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
	}
	/*@Bean
	RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
	return builder.routes()
	.route(r->r.path("/customers/**").uri("lb://CUSTOMER-SERVICE")).
	route(r->r.path("/products/**").uri("lb://INVENTORY-SERVICE")).build();
	}*/
	/*@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
	    return builder.routes()
	            .route(r -> r.path("/restcountries/**")
	                    .filters(f -> f
	                            .addRequestHeader("x-rapidapi-host", "restcountries-v1.p.rapidapi.com")
	                            .addRequestHeader("x-rapidapi-key", "da075c36bamshc2ecc29d168ab6dp1675c2jsn6da8c53f2d97")
	                            .rewritePath("/restcountries/(?<segment>.*)", "/${segment}")
	                    )
	                    .uri("https://restcountries-v1.p.rapidapi.com")
	            )
	            .route(r -> r.path("/muslimsalat/**")
	                    .filters(f -> f
	                            .addRequestHeader("x-rapidapi-host", "muslimsalat.p.rapidapi.com")
	                            .addRequestHeader("x-rapidapi-key", "da075c36bamshc2ecc29d168ab6dp1675c2jsn6da8c53f2d97")
	                            .rewritePath("/muslimsalat/(?<segment>.*)", "/${segment}")
	                    )
	                    .uri("https://muslimsalat.p.rapidapi.com")
	            )
	            .build();
	}*/


	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
	    return builder.routes()
	            .route(r -> r.path("/restcountries/**")
	                    .filters(f -> f
	                            .addRequestHeader("x-rapidapi-host", "restcountries-v1.p.rapidapi.com")
	                            .addRequestHeader("x-rapidapi-key", "da075c36bamshc2ecc29d168ab6dp1675c2jsn6da8c53f2d97")
	                            .rewritePath("/restcountries/(?<segment>.*)", "/${segment}")
	                            .circuitBreaker(c -> c.setName("rest-countries")
	                                    .setFallbackUri("forward:/restCountriesFallback"))
	                    )
	                    .uri("https://restcountries-v1.p.rapidapi.com")
	            )
	            .route(r -> r.path("/muslimsalat/**")
	                    .filters(f -> f
	                            .addRequestHeader("x-rapidapi-host", "muslimsalat.p.rapidapi.com")
	                            .addRequestHeader("x-rapidapi-key", "da075c36bamshc2ecc29d168ab6dp1675c2jsn6da8c53f2d97")
	                            .circuitBreaker(c -> c.setName("muslimsalat")
	                                    .setFallbackUri("forward:/muslimsalatFallback"))
	                    )
	                    .uri("https://muslimsalat.p.rapidapi.com")
	            )
	            .build();
	}

	@RestController
	public class FallBackRestController {

	    @GetMapping("/restCountriesFallback")
	    public Map<String, String> restCountriesFallback() {
	        Map<String, String> map = new HashMap<>();
	        map.put("message", "Default Rest Countries Fallback service");
	        map.put("countries", "Algeria, Tunisia");
	        return map;
	    }

	    @GetMapping("/muslimsalatFallback")
	    public Map<String, String> muslimsalatFallback() {
	        Map<String, String> map = new HashMap<>();
	        map.put("message", "Default Muslim Fallback service");
	        map.put("Fajr", "07:00");
	        map.put("DOHR", "14:00");
	        return map;
	    }
	}


}
