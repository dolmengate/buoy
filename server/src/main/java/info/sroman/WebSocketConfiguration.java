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
        // INCOMING
        // prefix for all @MessageMapping controller endpoints which are to be handled by this message broker
        // (messages will be sent to: "app/message" or "app/hello", etc.)
        config.setApplicationDestinationPrefixes("/app");

        // OUTGOING
        // "SimpleBroker" means in-memory broker
        // prefix(es) for outgoing message destinations. Many destinations may be handled by a single message broker.
        // (subscribe to "topic/data" or "topic/prices", defined by @SentTo annotation in WebSocket controller)
        config.enableSimpleBroker("/topic");

    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // endpoint for socket connection requests to point to for handshaking
        registry.addEndpoint("/buoy").withSockJS();
    }
}
