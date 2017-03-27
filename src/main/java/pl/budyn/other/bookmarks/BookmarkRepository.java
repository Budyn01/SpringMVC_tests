package pl.budyn.other.bookmarks;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
/**
 * Created by hlibe on 21.12.2016.
 */
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Collection<Bookmark> findByAccountUsername(String username);
}
