package onlinelibrary.books.repo;

import onlinelibrary.books.domain.Estimate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstimateRepository extends JpaRepository<Estimate, Long> {
}
