package br.com.os.repositories;

import br.com.os.model.Cliente;
import br.com.os.model.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

//    @Query("SELECT obj FROM Cliente obj WHERE obj.cpf:=cpf")
//    Cliente findByCPF(@Param("cpf") String cpf);
}
