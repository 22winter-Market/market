package winterproject.market.Repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import winterproject.market.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public List<Member> findById(String id) {
        return em.createQuery("select m from Member m where m.id = :id",
                Member.class)
                .setParameter("id", id)
                .getResultList();
    }

    public Member findOneById(String id) {
        return em.find(Member.class, id);
    }

}
