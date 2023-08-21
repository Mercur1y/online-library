package onlinelibrary.user.repo;

import onlinelibrary.user.domain.LocalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface UserRepo extends JpaRepository<LocalUser, Long> {
    LocalUser findByUsername(String username);
}
