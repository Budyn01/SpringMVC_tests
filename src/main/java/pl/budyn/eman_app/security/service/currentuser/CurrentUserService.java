package pl.budyn.eman_app.security.service.currentuser;

import pl.budyn.eman_app.security.domain.CurrentUser;

/**
 * Created by hlibe on 06.02.2017.
 */
public interface CurrentUserService {

    boolean canAccessUser(CurrentUser currentUser, Long userId);

}