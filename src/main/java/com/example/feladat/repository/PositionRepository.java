package com.example.feladat.repository;

import com.example.feladat.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
   @Query("select p from Position p where lower(p.name) like lower(concat('%', :keyword, '%')) and lower(p.location) like lower(concat('%', :location, '%'))")
   List<Position> searchPositions(@Param("keyword") String keyword,@Param("location") String location);
}
