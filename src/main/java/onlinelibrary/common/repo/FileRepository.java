package onlinelibrary.common.repo;

import onlinelibrary.common.domain.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository <FileInfo, Long> {
}
