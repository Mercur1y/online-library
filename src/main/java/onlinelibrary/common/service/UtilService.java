package onlinelibrary.common.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UtilService {

    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    public long countRows(Class entityClass) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Long> query = builder.createQuery(Long.class);

        query.select(builder.count(query.from(entityClass)));

        return entityManager.createQuery(query).getSingleResult();
    }
}
