package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Snack;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SnackRepository extends CrudRepository<Snack, Integer> {

    @Query("SELECT s FROM Snack s WHERE " +
            "(:maxPrice IS NULL OR s.price <= :maxPrice) AND " +
            "(:vegan IS NULL OR s.vegan=:vegan) ")
    List<Snack> findByFilterSnack(@Param("maxPrice") Double maxPrice,
                             @Param("vegan") Boolean vegan);

    @Query("SELECT s FROM Snack s WHERE " +
            "(:maxPrice IS NULL OR s.price+s.priceSideDish<= :maxPrice) AND " +
            "(:sideDishPossible IS NULL OR s.sideDishPossible=:sideDishPossible) AND" +
            "(:vegan IS NULL OR s.vegan=:vegan) ")
    List<Snack> findByFilterSnackAndSide(@Param("maxPrice") Double maxPrice,
                                         @Param("sideDishPossible") Boolean sideDishPossible,
                                  @Param("vegan") Boolean vegan);

    Optional<Snack> findFirstByIdLessThanOrderByIdDesc(Integer id);

    Optional<Snack> findFirstByOrderByIdDesc();

    Optional<Snack> findFirstByIdGreaterThanOrderByIdAsc(Integer id);

    Optional<Snack> findFirstByOrderByIdAsc();
}
