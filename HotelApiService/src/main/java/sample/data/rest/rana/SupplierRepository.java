package sample.data.rest.rana;

import me.david.modules.hotel.domain.Supplier;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Repository for domain {@link Supplier}
 */
@RepositoryRestResource(collectionResourceRel = "suppliers", path = "suppliers")
interface SupplierRepository extends PagingAndSortingRepository<Supplier, Long> {


}