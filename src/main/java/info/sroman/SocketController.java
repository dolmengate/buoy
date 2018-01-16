package info.sroman;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {

    // "request" endpoint for incoming WebSocket messages to this controller
    @MessageMapping("/test")
    // "response" endpoint to push outgoing WebSocket messages to. Clients subscribe to this endpoint to see messages
    @SendTo("/topic/messages")
    public Message sendMessage(Message incomingMessage) throws Exception {
        return new Message(incomingMessage.getText());
    }
}
