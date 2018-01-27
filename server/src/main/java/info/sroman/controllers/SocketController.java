package info.sroman.controllers;

import info.sroman.model.SocketMessage;
import info.sroman.entities.Editor;
import info.sroman.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {

    private Editor editor = new Editor();


    // INCOMING
    // "request" endpoint for incoming WebSocket messages to this controller
    // ("app/message" due to application destination prefixes in config")
    @MessageMapping("/message")
    //  OUTGOING
    // "response" endpoint to push outgoing WebSocket messages to. Clients subscribe to this endpoint to see messages
    @SendTo("/topic/editor")
    public SocketMessage sendMessage(SocketMessage incomingSocketMessage) throws Exception {
        // if the user just joined their in-browser editor will be empty,
        // therefore don't update editor to be blank!
        if (incomingSocketMessage.isFreshConnect()) {
            System.out.println("New connection detected");
            System.out.println("editor text is " + editor.getText());
            return new SocketMessage(editor.getText());
        } else {
            editor.setText(incomingSocketMessage.getText());
            System.out.println("editor text set: " + editor.getText());
            return new SocketMessage(editor.getText());
        }
    }
}
