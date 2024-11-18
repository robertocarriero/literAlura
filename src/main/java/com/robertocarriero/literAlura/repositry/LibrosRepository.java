package com.robertocarriero.literAlura.repositry;

import com.robertocarriero.literAlura.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibrosRepository extends JpaRepository<Libros,Long > {

}

