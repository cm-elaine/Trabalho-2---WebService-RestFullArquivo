package br.edu.ifgoias.academico.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Disciplina implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDisciplina;

    @Column(name = "nome_disciplina", nullable = false)
    private String nomeDisciplina;
    
    @Column(name = "carga_horaria", nullable = false)
    private String cargaHoraria;

    @ManyToMany
    @JoinTable(name = "curso_disciplina",
            joinColumns = @JoinColumn(name = "id_disciplina"),
            inverseJoinColumns = @JoinColumn(name = "id_curso"))
    private List<Curso> cursos = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "aluno_disciplina",
            joinColumns = @JoinColumn(name = "id_disciplina"),
            inverseJoinColumns = @JoinColumn(name = "id_aluno"))
    private List<Aluno> alunos = new ArrayList<>();

    public Disciplina() {
    }

    public Disciplina(Integer idDisciplina, String nomeDisciplina, String cargaHoraria) {
        this.idDisciplina = idDisciplina;
        this.nomeDisciplina = nomeDisciplina;
        this.cargaHoraria = cargaHoraria;
    }

    public Integer getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(Integer idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }
    
    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }
    
    public String getCargaHoraria() {
        return cargaHoraria;
    }
    
    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void adicionarCurso(Curso curso) {
        if (!cursos.contains(curso)) {
            cursos.add(curso);
            curso.adicionarDisciplina(this);
        }
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void adicionarAluno(Aluno aluno) {
        if (!alunos.contains(aluno)) {
            alunos.add(aluno);
            aluno.adicionarDisciplina(this);
        }
    }

	@Override
	public int hashCode() {
		return Objects.hash(cargaHoraria, idDisciplina, alunos, cursos, nomeDisciplina);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Disciplina other = (Disciplina) obj;
		return Objects.equals(cargaHoraria, other.cargaHoraria) && Objects.equals(idDisciplina, other.idDisciplina)
				&& Objects.equals(alunos, other.alunos) && Objects.equals(cursos, other.cursos)
				&& Objects.equals(nomeDisciplina, other.nomeDisciplina);
	}

	@Override
	public String toString() {
		return "Disciplina [idDisciplina=" + idDisciplina + ", nomeDisciplina=" + nomeDisciplina + ", cargaHoraria="
				+ cargaHoraria + ", cursos=" + cursos + ", alunos=" + alunos + "]";
	}

}
