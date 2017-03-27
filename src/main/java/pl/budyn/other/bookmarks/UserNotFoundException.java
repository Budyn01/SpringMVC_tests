package pl.budyn.other.bookmarks;

/**
 * Created by hlibe on 21.12.2016.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userId){
        super("could not find user '" + userId + "'.");
    }
}
