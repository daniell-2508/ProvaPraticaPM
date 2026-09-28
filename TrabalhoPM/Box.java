import java.util.ArrayList;

public class Box {

private int numero;
private String tipoServicoPermitido;
private int capacidadeMaxima;
private String localizacao;
private Mecanico mecanicoResponsavel;
private ArrayList<Ordem> ordens;

public Box(int numero, String tipoServicoPermitido, int capacidadeMaxima, String localizacao) {
this.numero = numero;
this.tipoServicoPermitido = tipoServicoPermitido;
this.capacidadeMaxima = capacidadeMaxima;
this.localizacao = localizacao;
this.mecanicoResponsavel = null;
this.ordens = new ArrayList<>();
}

public int getNumero() {
return numero;
}

public String getTipoServicoPermitido() {
return tipoServicoPermitido;
}

public int getCapacidadeMaxima() {
return capacidadeMaxima;
}

public String getLocalizacao() {
return localizacao;
}

public Mecanico getMecanicoResponsavel() {
return mecanicoResponsavel;
}

public ArrayList<Ordem> getOrdens() {
return ordens;
}

public boolean possuiMecanico() {
return mecanicoResponsavel != null;
}

public void setMecanicoResponsavel(Mecanico mecanico) {
this.mecanicoResponsavel = mecanico;
}

public boolean podeReceberOrdem(Ordem ordem) {

if (ordens.size() >= capacidadeMaxima) {
return false;
}

if (!tipoServicoPermitido.equalsIgnoreCase(
ordem.getServico().getCategoria())) {
return false;
}

return true;
}

public void adicionarOrdem(Ordem ordem) {
ordens.add(ordem);
}

public void removerOrdem(Ordem ordem) {
ordens.remove(ordem);
}
}

