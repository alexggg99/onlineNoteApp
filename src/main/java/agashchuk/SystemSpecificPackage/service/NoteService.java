package agashchuk.SystemSpecificPackage.service;

import agashchuk.LogicPackage.model.Note;
import agashchuk.SystemSpecificPackage.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface NoteService {

    List<Note> findAllByUser(User user);

    @Query("Select n from Note n order by n.timestamp")
    List<Note> findAll();

    Note findNote(Long id);

    Note createNote(Note note);

    Note saveNote(Note note);

    void deleteNode(Long id);

}
