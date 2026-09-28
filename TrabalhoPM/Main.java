import java.util.ArrayList;
import java.util.Scanner;

public class Main {

public static void main(String[] args) {

Scanner entrada = new Scanner(System.in);

ArrayList<Mecanico> mecanicos = new ArrayList<>();
ArrayList<Box> boxes = new ArrayList<>();
ArrayList<Ordem> ordens = new ArrayList<>();

criarDadosIniciais(mecanicos, boxes);

int opcao;

do {

// System.out.println("\n==============================");
// System.out.println(" OFICINA MECÂNICA");
// System.out.println("==============================");
System.out.println("Oficina do Daniell:");
System.out.println("1 - Cadastrar ordem de serviço");
System.out.println("2 - Associar mecânico a um box");
System.out.println("3 - Atribuir ordem de serviço a um box");
System.out.println("4 - Exibir ordens de um box");
System.out.println("5 - Quantidade de ordens finalizadas por box");
System.out.println("6 - Buscar ordens por status");
System.out.println("7 - Exibir detalhes de uma ordem");
System.out.println("8 - Finalizar ordem");
System.out.println("0 - Sair");
System.out.print("Escolha uma opção: ");

opcao = entrada.nextInt();
entrada.nextLine();

switch (opcao) {

case 1:

System.out.println("\n===== CADASTRAR ORDEM =====");

System.out.print("Código: ");
int codigo = entrada.nextInt();
entrada.nextLine();

System.out.print("Nome do cliente: ");
String cliente = entrada.nextLine();

System.out.print("Modelo do veículo: ");
String modelo = entrada.nextLine();

System.out.print("Placa do veículo: ");
String placa = entrada.nextLine();

System.out.print("Data: ");
String data = entrada.nextLine();

System.out.print("Valor estimado: ");
double valorEstimado = entrada.nextDouble();
entrada.nextLine();

System.out.println("");
System.out.println("SERVIÇO");

System.out.print("Nome do serviço: ");
String nomeServico = entrada.nextLine();

System.out.print("Tempo estimado (horas): ");
int tempo = entrada.nextInt();

System.out.print("Valor do serviço: ");
double valorServico = entrada.nextDouble();
entrada.nextLine();

System.out.print("Categoria do serviço: ");
String categoria = entrada.nextLine();

Servico servico = new Servico(
nomeServico,
tempo,
valorServico,
categoria
);

Ordem ordem = new Ordem(
codigo,
cliente,
modelo,
placa,
data,
valorEstimado,
servico
);

ordens.add(ordem);

System.out.println("Ordem cadastrada com sucesso!");

break;

case 2:

System.out.println("");
System.out.println("ASSOCIAR MECÂNICO AO BOX");

System.out.print("CPF do mecânico: ");
String cpf = entrada.nextLine();

System.out.print("Número do box: ");
int numeroBox = entrada.nextInt();
entrada.nextLine();

Mecanico mecanicoEncontrado = null;
Box boxEncontrado = null;

for (Mecanico mecanico : mecanicos) {

if (mecanico.getCpf().equals(cpf)) {
mecanicoEncontrado = mecanico;
}
}

for (Box box : boxes) {

if (box.getNumero() == numeroBox) {
boxEncontrado = box;
}
}

if (mecanicoEncontrado == null) {

System.out.println("Mecânico não encontrado.");

} else if (boxEncontrado == null) {

System.out.println("Box não encontrado.");

} else if (mecanicoEncontrado.possuiBox()) {

System.out.println(
"Esse mecânico já é responsável por um box."
);

} else if (boxEncontrado.possuiMecanico()) {

System.out.println(
"Esse box já possui um mecânico."
);

} else {

mecanicoEncontrado.setBoxResponsavel(boxEncontrado);
boxEncontrado.setMecanicoResponsavel(mecanicoEncontrado);

System.out.println(
"Mecânico associado ao box com sucesso!"
);
}

break;

case 3:

System.out.println("");
System.out.println("ATRIBUIR ORDEM AO BOX");

System.out.print("Código da ordem: ");
int codigoOrdem = entrada.nextInt();

System.out.print("Número do box: ");
int numeroBoxOrdem = entrada.nextInt();
entrada.nextLine();

Ordem ordemEncontrada = null;
Box boxOrdem = null;

for (Ordem ordemBusca : ordens) {

if (ordemBusca.getCodigo() == codigoOrdem) {
ordemEncontrada = ordemBusca;
}
}

for (Box boxBusca : boxes) {

if (boxBusca.getNumero() == numeroBoxOrdem) {
boxOrdem = boxBusca;
}
}

if (ordemEncontrada == null) {

System.out.println("Ordem não encontrada.");

} else if (boxOrdem == null) {

System.out.println("Box não encontrado.");

} else if (!ordemEncontrada.getStatus().equals("aberta")) {

System.out.println(
"Apenas ordens abertas podem ser atribuídas."
);

} else if (!boxOrdem.possuiMecanico()) {

System.out.println(
"Esse box ainda não possui mecânico."
);

} else if (!boxOrdem.podeReceberOrdem(ordemEncontrada)) {

System.out.println(
"O box não pode receber essa ordem."
);

System.out.println(
"Verifique a capacidade ou a categoria do serviço."
);

} else {

ordemEncontrada.setBox(boxOrdem);
boxOrdem.adicionarOrdem(ordemEncontrada);

System.out.println(
"Ordem atribuída ao box com sucesso!"
);
}

break;

case 4:

System.out.println("");
System.out.println("ORDENS DO BOX");

System.out.print("Número do box: ");
int numeroConsulta = entrada.nextInt();
entrada.nextLine();

Box boxConsulta = null;

for (Box box : boxes) {

if (box.getNumero() == numeroConsulta) {
boxConsulta = box;
}
}

if (boxConsulta == null) {

System.out.println("Box não encontrado.");

} else {

System.out.println(
"\n===== BOX " +
boxConsulta.getNumero() +
" ====="
);

if (boxConsulta.getOrdens().isEmpty()) {

System.out.println(
"Nenhuma ordem atribuída."
);

} else {

for (Ordem ordemBox :
boxConsulta.getOrdens()) {

System.out.println(
"Código: " +
ordemBox.getCodigo()
);

System.out.println(
"Cliente: " +
ordemBox.getNomeCliente()
);

System.out.println(
"Status: " +
ordemBox.getStatus()
);


}

System.out.println(
"Total de ordens: " +
boxConsulta.getOrdens().size()
);
}
}

break;

case 5:

System.out.println("");
System.out.println(

"ORDENS FINALIZADAS POR BOX ");

for (Box box : boxes) {

int quantidade = 0;

for (Ordem ordemFinalizada : ordens) {

if (ordemFinalizada.getStatus().equals("finalizada")
&& ordemFinalizada.getBox() == box) {

quantidade++;
}
}

System.out.println(
"Box " + box.getNumero() +
": " + quantidade +
" ordem(ns) finalizada(s)"
);
}

break;

case 6:

System.out.println("");
System.out.println("BUSCAR POR STATUS");
System.out.println("1 - Aberta");
System.out.println("2 - Em execução");
System.out.println("3 - Finalizada");
System.out.print("Escolha: ");

int escolhaStatus = entrada.nextInt();
entrada.nextLine();

String statusBusca = "";

if (escolhaStatus == 1) {

statusBusca = "aberta";

} else if (escolhaStatus == 2) {

statusBusca = "em execução";

} else if (escolhaStatus == 3) {

statusBusca = "finalizada";

} else {

System.out.println("Opção inválida.");
break;
}

boolean encontrou = false;

for (Ordem ordemBusca : ordens) {

if (ordemBusca.getStatus()
.equals(statusBusca)) {

encontrou = true;

System.out.println("\n--------------------");

System.out.println(
"Código: " +
ordemBusca.getCodigo()
);

System.out.println(
"Cliente: " +
ordemBusca.getNomeCliente()
);

System.out.println(
"Veículo: " +
ordemBusca.getModeloVeiculo()
);

System.out.println(
"Placa: " +
ordemBusca.getPlacaVeiculo()
);

System.out.println(
"Status: " +
ordemBusca.getStatus()
);

if (ordemBusca.getBox() != null) {

System.out.println(
"Box: " +
ordemBusca.getBox().getNumero()
);

if (ordemBusca.getBox()
.getMecanicoResponsavel() != null) {

System.out.println(
"Mecânico: " +
ordemBusca.getBox()
.getMecanicoResponsavel()
.getNome()
);
}

} else {

System.out.println("Box: Nenhum");
}
}
}

if (!encontrou) {

System.out.println(
"Nenhuma ordem encontrada."
);
}

break;

case 7:

System.out.println("");
System.out.println(
"DETALHES DA ORDEM"
);

System.out.print("Código da ordem: ");
int codigoDetalhes = entrada.nextInt();
entrada.nextLine();

Ordem ordemDetalhes = null;

for (Ordem ordemBusca : ordens) {

if (ordemBusca.getCodigo()
== codigoDetalhes) {

ordemDetalhes = ordemBusca;
}
}

if (ordemDetalhes == null) {

System.out.println(
"Ordem não encontrada."
);

} else {

ordemDetalhes.exibirDetalhes();
}

break;

case 8:

System.out.println("");
System.out.println(
    
"FINALIZAR ORDEM");

System.out.print("Código da ordem: ");
int codigoFinalizar = entrada.nextInt();
entrada.nextLine();

Ordem ordemFinalizar = null;

for (Ordem ordemBusca : ordens) {

if (ordemBusca.getCodigo()
== codigoFinalizar) {

ordemFinalizar = ordemBusca;
}
}

if (ordemFinalizar == null) {

System.out.println(
"Ordem não encontrada."
);

} else if (ordemFinalizar.getBox() == null) {

System.out.println(
"Essa ordem não possui box."
);

} else {

Box boxDaOrdem = ordemFinalizar.getBox();

ordemFinalizar.finalizar();

boxDaOrdem.removerOrdem(ordemFinalizar);

System.out.println(
"Ordem finalizada com sucesso!"
);
}

break;

case 0:

System.out.println(
"Programa encerrado."
);

break;

default:

System.out.println(
"Opção inválida."
);
}

} while (opcao != 0);

entrada.close();
}

public static void criarDadosIniciais(
ArrayList<Mecanico> mecanicos,
ArrayList<Box> boxes) {

Mecanico mecanico1 = new Mecanico(
"Carlos Silva",
"111.111.111-11",
"Motor",
"3199999-1111"
);

Mecanico mecanico2 = new Mecanico(
"João Souza",
"222.222.222-22",
"Freios",
"3199999-2222"
);

Mecanico mecanico3 = new Mecanico(
"Pedro Santos",
"333.333.333-33",
"Suspensão",
"3199999-3333"
);

Box box1 = new Box(
1,
"Motor",
5,
"Área A"
);

Box box2 = new Box(
2,
"Freios",
5,
"Área B"
);

Box box3 = new Box(
3,
"Suspensão",
5,
"Área C"
);

mecanicos.add(mecanico1);
mecanicos.add(mecanico2);
mecanicos.add(mecanico3);

boxes.add(box1);
boxes.add(box2);
boxes.add(box3);

System.out.println(
"3 mecânicos e 3 boxes cadastrados."
);
}
}
