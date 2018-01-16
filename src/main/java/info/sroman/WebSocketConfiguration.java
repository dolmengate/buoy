package info.sroman;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // prefix all URL endpoints that clients subscribe to to view server-pushed messages with "/topic"
        config.enableSimpleBroker("/topic");

        // prefix all URL endpoints for controllers that accept WebSocket messages with "/app"
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // enable SockJS fallback options should the WebSocket connection fail (due to browser incompatibility, etc.)
        registry.addEndpoint("/gs-guide-websocket").withSockJS();
    }
}
