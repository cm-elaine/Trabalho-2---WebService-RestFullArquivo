package br.edu.ifgoias.academico.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifgoias.academico.entities.Disciplina;
import br.edu.ifgoias.academico.services.DisciplinaService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/disciplinas")
public class DisciplinaResource {

	@Autowired
	private DisciplinaService disciplinaService;

	@GetMapping
	public ResponseEntity<List<Disciplina>> obterTodas() {

		List<Disciplina> listaDisciplinas = disciplinaService.findAll();

		return ResponseEntity.ok().body(listaDisciplinas);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Disciplina> obterPorId(@PathVariable Integer id) {

		Disciplina disciplina = disciplinaService.findById(id);

		return ResponseEntity.ok().body(disciplina);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Disciplina> inserirDisciplina(@RequestBody Disciplina disciplina) {

		disciplina = disciplinaService.insert(disciplina);

		return ResponseEntity.ok().body(disciplina);

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerDisciplina(@PathVariable Integer id) {

		disciplinaService.delete(id);

	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Disciplina> atualizarDisciplina(@PathVariable Integer id, @RequestBody Disciplina disciplina) {

		disciplina = disciplinaService.update(id, disciplina);

		return ResponseEntity.ok().body(disciplina);
	}
}
