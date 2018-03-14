package info.sroman;

import info.sroman.entities.Editor;
import info.sroman.model.SocketMessage;
import info.sroman.repositories.EditorRepository;
import info.sroman.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class EditorCacheService {

    public EditorCacheService(PostRepository postsRepo, EditorRepository editorsRepo) { }

    private Map<Long, Editor> editors = new HashMap<>();

    private Logger log = Logger.getLogger("EditorCacheService");

//    public Editor findEditor(SocketMessage message) {
//    }

    private void addEditor(Editor e) {
        editors.put(e.getAttachmentId(), e);
    }

    /**
     * Attempts to get an Editor from the cache using the the editorId.
     * @param request   SocketMessage containing the editorId of the requested
     * @return          The requested Editor or null if none was found.
     */
    private Editor checkForActiveEditor(SocketMessage request) {
        try {
            return editors.get(request.getEditorId());
        } catch (NullPointerException | NumberFormatException ex) {
            log.warning("SocketMessage: " + request);
            ex.printStackTrace();
        }
        return null;
    }
}
