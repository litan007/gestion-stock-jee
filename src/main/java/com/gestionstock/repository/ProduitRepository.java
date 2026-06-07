package com.gestionstock.repository;

import com.gestionstock.model.Produit;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository CRUD + filtres pour les produits.
 */
public interface ProduitRepository extends JpaRepository<Produit, Long> {

    Optional<Produit> findByReference(String reference);

    boolean existsByReference(String reference);

    @Query("""
        select p from Produit p
        where p.actif = true
          and (:terme is null or lower(p.nom) like lower(concat('%', :terme, '%'))
               or lower(p.reference) like lower(concat('%', :terme, '%')))
          and (:categorie is null or p.categorie = :categorie)
          and (
               :etat is null
               or (:etat = 'RUPTURE' and p.quantite <= 0)
               or (:etat = 'FAIBLE' and p.quantite > 0 and p.quantite <= p.quantiteMinimale)
               or (:etat = 'DISPONIBLE' and p.quantite > p.quantiteMinimale)
          )
        """)
    Page<Produit> rechercher(@Param("terme") String terme,
                             @Param("categorie") String categorie,
                             @Param("etat") String etat,
                             Pageable pageable);

    List<Produit> findByActifTrue();

    List<Produit> findByActifTrueAndQuantiteEquals(Integer quantite);

    @Query("select p from Produit p where p.actif = true and p.quantite > 0 and p.quantite <= p.quantiteMinimale")
    List<Produit> findStockFaible();

    @Query("select distinct p.categorie from Produit p where p.actif = true order by p.categorie")
    List<String> findDistinctCategories();
}
