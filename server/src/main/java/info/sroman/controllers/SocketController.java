package info.sroman.controllers;

import info.sroman.model.SocketMessage;
import info.sroman.entities.Editor;
import info.sroman.repositories.EditorRepository;
import info.sroman.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Controller
public class SocketController {

    @Autowired
    PostRepository postsRepo;

    @Autowired
    EditorRepository editorsRepo;

    private Map<Long, Editor> editorsCache = new HashMap<>();
    // todo: periodically check editors to see if anyone has used it in a while, if not remove from editorsCache -- lastTouched property?
    // todo: EditorCache class
    // todo: ChatCache class
    // todo: SocketMessageCache abstract class

    private Logger log = Logger.getLogger("SocketController");

    /**
     * Accepts incoming socket messages sent to /api/message and broadcasts responses to /topic/editor.
     * After the an editor is requested for the first time it is cached to prevent subsequent database queries
     *
     * @param incomingMessage   SocketMessage that contains information about which editor to update/retrieve
     * @return                  A response SocketMessage with the requested editor information (text, etc.)
     */
    // INCOMING
    // "request" endpoint for incoming WebSocket messages to this controller
    // ("app/message" due to application destination prefixes in config")
    @MessageMapping("/message")
    //  OUTGOING
    // "response" endpoint to push outgoing WebSocket messages to. Clients subscribe to this endpoint to see messages
    @SendTo("/topic/editor")
    public SocketMessage sendMessage(SocketMessage incomingMessage) {
        Long id = incomingMessage.getEditorId();

        // check 'editorsCache' map before pulling editor text from DB
        Editor requestedEditor = editorsCache.get(id);

        if (requestedEditor == null) {     // editor could not be found in editorsCache
            requestedEditor = editorsRepo.findOne(id);
            editorsCache.put(requestedEditor.getAttachmentId(), requestedEditor);
        }

        // update editor text only if this is not a new user connecting (prevents setting text to blank)
        if (!incomingMessage.isFreshConnect())
            requestedEditor.setText(incomingMessage.getText());

        return new SocketMessage(requestedEditor);
    }
}
