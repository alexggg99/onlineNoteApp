package agashchuk.SystemSpecificPackage.service;

import agashchuk.LogicPackage.model.Note;
import agashchuk.SystemSpecificPackage.model.User;
import agashchuk.SystemSpecificPackage.repo.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public List<Note> findAllByUser(User user) {
        return (List<Note>) noteRepository.findByUserOrderByTimestampDesc(user);
    }

    @Override
    public List<Note> findAll() {
        return (List<Note>) noteRepository.findAll();
    }

    @Override
    public Note findNote(Long id) {
        return noteRepository.findById(id);
    }

    @Override
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public void deleteNode(Long id) {
        noteRepository.delete(id);
    }
}
