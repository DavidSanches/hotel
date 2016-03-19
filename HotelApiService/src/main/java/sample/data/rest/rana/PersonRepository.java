package sample.data.rest.rana;

import me.david.modules.hotel.domain.Person;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Repository for domain {@link Person}
 */
@RepositoryRestResource(collectionResourceRel = "persons", path = "persons")
interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

//    Page<City> findByNameContainingAndCountryContainingAllIgnoringCase(
//            @Param("name") String name, @Param("country") String country,
//            Pageable pageable);
//
//    City findByNameAndCountryAllIgnoringCase(@Param("name") String name,
//                                             @Param("country") String country);

}