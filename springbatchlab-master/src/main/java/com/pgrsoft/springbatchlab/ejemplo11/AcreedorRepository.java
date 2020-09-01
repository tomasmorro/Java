package com.pgrsoft.springbatchlab.ejemplo11;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcreedorRepository extends JpaRepository<Acreedor,Long>{

}
