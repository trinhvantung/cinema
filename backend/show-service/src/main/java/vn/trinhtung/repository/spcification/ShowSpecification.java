package vn.trinhtung.repository.spcification;

import org.springframework.data.jpa.domain.Specification;
import vn.trinhtung.dto.ShowFilterDTO;
import vn.trinhtung.entity.Show;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShowSpecification {
    public static Specification<Show> filterShowSpecification(ShowFilterDTO showFilter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(Objects.nonNull(showFilter)) {

                if (Objects.nonNull(showFilter.getCinemaId())) {
                    predicates.add(cb.equal(root.get("cinemaId"), showFilter.getCinemaId()));
                }
                if (Objects.nonNull(showFilter.getHallId())) {
                    predicates.add(cb.equal(root.get("hallId"), showFilter.getHallId()));
                }
                if (Objects.nonNull(showFilter.getMovieId())) {
                    predicates.add(cb.equal(root.get("movieId"), showFilter.getMovieId()));
                }
                if (Objects.nonNull(showFilter.getStart()) && Objects.nonNull(showFilter.getEnd())) {
                    predicates.add(cb.between(root.get("start"), showFilter.getStart(), showFilter.getEnd()));
                }
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
