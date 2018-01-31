package info.sroman.controllers;

import info.sroman.entities.Content;
import info.sroman.entities.Type;
import info.sroman.model.SocketMessage;
import info.sroman.entities.Editor;
import info.sroman.repositories.ContentRepository;
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
    ContentRepository contentsRepo;

    @Autowired
    EditorRepository editorsRepo;

    private HashSet<Editor> editorsCache = new HashSet<>();
    // todo: periodically check editors to see if anyone has used it in a while, if not remove from editors -- lastTouched property?

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
        }
    }

    private Editor checkForActiveEditor(SocketMessage request) {
        if (request.getEditorId() == null) {
            return getEditorFromSet(findEditorIdByPostId(request.getPostId()));
        } else {
            return getEditorFromSet(request.getEditorId());
        }
    }

    private Long findEditorIdByPostId(Long postId) {
        Content c = contentsRepo.findOne(postsRepo.findOne(postId).getContentId());
        if (c.getType() == Type.CODE)
            return c.getAttachmentId();
        return null;
    }

    private Editor getEditorFromSet(Long editorId) {
        Iterator<Editor> iter = this.editorsCache.iterator();
        Editor e;
        while (iter.hasNext()) {
            e = iter.next();
            if (e.getId().equals(editorId)) { // request message has no editorId yet
                return e;
            }
        }
        return null;
    }

    private Editor retrieveEditorFromRepo(SocketMessage request) {
        // use editorId if possible
        if (request.getEditorId() != null) {
            return editorsRepo.findOne(request.getEditorId());
        }
        // fixme: awful -- at least it only has to do this once before the client has the editorId
        // otherwise use postId
        return editorsRepo.findOne(contentsRepo.findOne(postsRepo.findOne(request.getPostId()).getContentId()).getAttachmentId());
    }
}
