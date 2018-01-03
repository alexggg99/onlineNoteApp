package agashchuk.SystemSpecificPackage.repo;

import agashchuk.LogicPackage.model.Note;
import agashchuk.SystemSpecificPackage.model.User;
import org.springframework.data.repository.CrudRepository;


public interface NoteRepository extends CrudRepository<Note, Long> {
    Iterable<Note> findByUserOrderByTimestamp(User user);
    Note findById(Long id);
}