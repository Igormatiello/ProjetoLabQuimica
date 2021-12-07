package br.edu.utfpr.pb.labquimica.backend.repository;

import br.edu.utfpr.pb.labquimica.backend.model.Papel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PapelRepository extends JpaRepository<Papel, Long> {
	Optional<Papel> findByNome(String nome);
}
