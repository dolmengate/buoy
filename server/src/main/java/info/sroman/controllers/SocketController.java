package info.sroman.controllers;

import info.sroman.model.SocketMessage;
import info.sroman.entities.Editor;
import info.sroman.repositories.EditorRepository;
import info.sroman.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.HashSet;
import java.util.Iterator;

@Controller
public class SocketController {

    @Autowired
    PostRepository postsRepo;

    @Autowired
    EditorRepository editorsRepo;

    private HashSet<Editor> editorsCache = new HashSet<>();
    // todo: periodically check editors to see if anyone has used it in a while, if not remove from editors -- lastTouched property?
    // fixme: all active editors are being updated when any other editor is opened for use
    // INCOMING
    // "request" endpoint for incoming WebSocket messages to this controller
    // ("app/message" due to application destination prefixes in config")
    @MessageMapping("/message")
    //  OUTGOING
    // "response" endpoint to push outgoing WebSocket messages to. Clients subscribe to this endpoint to see messages
    @SendTo("/topic/editor")
    public SocketMessage sendMessage(SocketMessage incomingMessage) {
        // ---- incoming messages will always have postId ----

        // check 'editorsCache' set before pulling editor text from DB
        Editor requestedEditor = checkForActiveEditor(incomingMessage);

        // if the editor wasn't in the set check the repo
        if (incomingMessage.isFreshConnect()) {
            if (requestedEditor != null) {
                return new SocketMessage(requestedEditor); // editor is currently being used, text from editorsCache was gotten
            } else {
                requestedEditor = retrieveEditorFromRepo(incomingMessage);
                editorsCache.add(requestedEditor);
                return new SocketMessage(requestedEditor); // editor was not being used, it has been retrieved an added to the set
            }
        } else {    // user is actively modifying editor text
            try {
                requestedEditor.setText(incomingMessage.getText()); // update editor text
            } catch (NullPointerException ex) {
                return new SocketMessage("Could not find editor, please refresh.");
            }
            return new SocketMessage(requestedEditor); // sends text and editorId
            // todo: send back postId with outgoing message
        }
    }

    private Editor checkForActiveEditor(SocketMessage request) {
        if (request.getEditorId() == null) {
            return getEditorFromSet(editorsRepo.findOneByPostId(request.getPostId()).getAttachmentId());
        } else {
            return getEditorFromSet(request.getEditorId());
        }
    }

    private Editor getEditorFromSet(Long editorId) {
        Iterator<Editor> iter = this.editorsCache.iterator();
        Editor e;
        while (iter.hasNext()) {
            e = iter.next();
            if (e.getAttachmentId().equals(editorId)) { // request message has no editorId yet
                return e;
            }
        }
        return null;
    }

    private Editor retrieveEditorFromRepo(SocketMessage request) {
        // prefer editorId (no table joins required)
        if (request.getEditorId() != null) {
            return editorsRepo.findOne(request.getEditorId());
        }
        return editorsRepo.findOneByPostId(request.getPostId());
    }
}
