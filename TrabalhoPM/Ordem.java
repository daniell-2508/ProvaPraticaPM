public class Ordem {

private int codigo;
private String nomeCliente;
private String modeloVeiculo;
private String placaVeiculo;
private String data;
private String status;
private double valorEstimado;
private Servico servico;
private Box box;

public Ordem(int codigo, String nomeCliente, String modeloVeiculo,
String placaVeiculo, String data,
double valorEstimado, Servico servico) {

this.codigo = codigo;
this.nomeCliente = nomeCliente;
this.modeloVeiculo = modeloVeiculo;
this.placaVeiculo = placaVeiculo;
this.data = data;
this.valorEstimado = valorEstimado;
this.servico = servico;

this.status = "aberta";
this.box = null;
}

public int getCodigo() {
return codigo;
}

public String getNomeCliente() {
return nomeCliente;
}

public String getModeloVeiculo() {
return modeloVeiculo;
}

public String getPlacaVeiculo() {
return placaVeiculo;
}

public String getData() {
return data;
}

public String getStatus() {
return status;
}

public double getValorEstimado() {
return valorEstimado;
}

public Servico getServico() {
return servico;
}

public Box getBox() {
return box;
}

public void setBox(Box box) {
this.box = box;
this.status = "em execução";
}

public void finalizar() {
this.status = "finalizada";
}

public void exibirDetalhes() {

    System.out.println("");
System.out.println("ORDEM");
System.out.println("Código: " + codigo);
System.out.println("Cliente: " + nomeCliente);
System.out.println("Modelo: " + modeloVeiculo);
System.out.println("Placa: " + placaVeiculo);
System.out.println("Data: " + data);
System.out.println("Status: " + status);
System.out.println("Valor estimado: R$ " + valorEstimado);
System.out.println("");
System.out.println("SERVIÇO");
System.out.println("Nome: " + servico.getNome());
System.out.println("Tempo estimado: " +
servico.getTempoEstimado() + " horas");
System.out.println("Valor: R$ " + servico.getValor());
System.out.println("Categoria: " + servico.getCategoria());
System.out.println("");
System.out.println("BOX");

if (box != null) {

System.out.println("Número: " + box.getNumero());
System.out.println("Localização: " + box.getLocalizacao());

if (box.getMecanicoResponsavel() != null) {
System.out.println("Mecânico: " +
box.getMecanicoResponsavel().getNome());
}

} else {

System.out.println("Nenhum box atribuído.");
}
}
}
