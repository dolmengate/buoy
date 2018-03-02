package info.sroman.controllers;

import info.sroman.model.SocketMessage;
import info.sroman.entities.Editor;
import info.sroman.repositories.EditorRepository;
import info.sroman.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import javax.validation.constraints.Null;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Logger;

@Controller
public class SocketController {

    @Autowired
    PostRepository postsRepo;

    @Autowired
    EditorRepository editorsRepo;

    private HashSet<Editor> editorsCache = new HashSet<>(); // todo: change to map using editorId as key
    // todo: periodically check editors to see if anyone has used it in a while, if not remove from editors -- lastTouched property?

    private Logger log = Logger.getLogger("SocketController");

    /**
     * Accepts incoming socket messages sent to /api/message and broadcasts responses to /topic/editor.
     * Filters requests based on incoming postId or editorId, preferring the latter if it is present.
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

        // check 'editorsCache' set before pulling editor text from DB
        Editor requestedEditor = checkForActiveEditor(incomingMessage);

        if (incomingMessage.isFreshConnect()) {
            if (requestedEditor == null) {      // editor could not be found in editorsCache
                requestedEditor = retrieveEditorFromRepo(incomingMessage);
                editorsCache.add(requestedEditor);
            }
        } else {    // at least one user is actively modifying editor text
            try {
                requestedEditor.setText(incomingMessage.getText()); // update editor text
            } catch (NullPointerException ex) {
                log.warning("Editor not found.");
                return new SocketMessage("Could not find editor, please refresh.");
            }
        }
        return new SocketMessage(requestedEditor);
    }

    /**
     * Attempts to get an Editor from the cache using the either the editorId or postId, preferring the former.
     * @param request   SocketMessage containing the editorId of the requested
     * @return          The requested Editor or null if none was found.
     */
    private Editor checkForActiveEditor(SocketMessage request) {
        try {
            if (request.getEditorId() == null)  // editorId will have to be found with postId
                return getEditorFromSet(editorsRepo.findOneByPostId(request.getPostId()).getAttachmentId());
            return getEditorFromSet(request.getEditorId());
        } catch (NullPointerException | NumberFormatException ex) {
            log.warning("SocketMessage: " + request);
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Pulls an Editor from the database either by editorId or postId, preferring the former.
     * @param request   SocketMessage containing the identifiers required for the DB query
     * @return          The requested Editor.
     */
    private Editor retrieveEditorFromRepo(SocketMessage request) {
        // prefer editorId (no table joins required)
        if (request.getEditorId() != null) {
            return editorsRepo.findOne(request.getEditorId());
        }
        return editorsRepo.findOneByPostId(request.getPostId());
    }

    /**
     * Checks if the requested editor is in the cache. Utility method for checkForActiveEditor.
     * @param editorId  Unique ID of the editor to be retrieved.
     * @return          The requested Editor or null if none was found.
     */
    private Editor getEditorFromSet(Long editorId) {
        Iterator<Editor> iter = this.editorsCache.iterator();
        Editor e;
        while (iter.hasNext()) {
            e = iter.next();
            if (e.getAttachmentId().equals(editorId)) {
                return e;
            }
        }
        return null;
    }
}
