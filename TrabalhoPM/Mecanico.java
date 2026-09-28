public class Mecanico {

private String nome;
private String cpf;
private String especialidade;
private String telefone;
private Box boxResponsavel;

public Mecanico(String nome, String cpf, String especialidade, String telefone) {
this.nome = nome;
this.cpf = cpf;
this.especialidade = especialidade;
this.telefone = telefone;
this.boxResponsavel = null;
}

public String getNome() {
return nome;
}

public String getCpf() {
return cpf;
}

public String getEspecialidade() {
return especialidade;
}

public String getTelefone() {
return telefone;
}

public Box getBoxResponsavel() {
return boxResponsavel;
}

public boolean possuiBox() {
return boxResponsavel != null;
}

public void setBoxResponsavel(Box box) {
this.boxResponsavel = box;
}
}