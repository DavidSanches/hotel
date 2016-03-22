package sample.data.rest.rana;

import me.david.modules.hotel.domain.Person;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Repository for domain {@link Person}
 */
@RepositoryRestResource(collectionResourceRel = "persons", path = "persons")
interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

    @RestResource(path="byName")
    public List findByPersonName(@Param("name") String personName);

}