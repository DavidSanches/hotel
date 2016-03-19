package sample.data.rest.rana;

import me.david.modules.hotel.domain.Stuff;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Repository for domain {@link Stuff}
 */
@RepositoryRestResource(collectionResourceRel = "stuff", path = "stuff")
interface StuffRepository extends PagingAndSortingRepository<Stuff, Long> {


}