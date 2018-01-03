package agashchuk.LogicPackage.controller;

import agashchuk.LogicPackage.model.Note;
import agashchuk.SystemSpecificPackage.service.NoteService;
import agashchuk.SystemSpecificPackage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Note> getAllNotes(){
        return noteService.findAllByUser(userService.getCurrentlyLoggedUser());
    }

    @PostMapping
    public long createNote() {
        Note note = new Note(userService.getCurrentlyLoggedUser());
        return noteService.createNote(note).getId();
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Object> updateNote(@PathVariable(value = "id") long id, @RequestBody String body) {
        Note note = noteService.findNote(id);
        note.setNote(body);
        noteService.saveNote(note);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteNote(@PathVariable(value = "id") long id) {
        noteService.deleteNode(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
