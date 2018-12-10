package br.ufrpe.aluguelDeCarro;

import br.ufrpe.aluguelDeCarro.dados.entidades.*;
import br.ufrpe.aluguelDeCarro.excecoes.*;
import br.ufrpe.aluguelDeCarro.negocio.*;
import br.ufrpe.aluguelDeCarro.servicos.Singleton;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Fernando
 */
public class Test {
    private Singleton singleton = Singleton.getInstance();
    private CategoriaNegocio categoriaNegocio = singleton.getCategoriaNegocio();
    private CarroNegocio carroNegocio = singleton.getCarroNegocio();
    private ClienteNegocio clienteNegocio = singleton.getClienteNegocio();
    private AluguelNegocio aluguelNegocio = singleton.getAluguelNegocio();
    private ReservaNegocio reservaNegocio = singleton.getReservaNegocio();
    private ManutencaoNegocio manutencaoNegocio = singleton.getManutencaoNegocio();
    private UsuarioNegocio usuarioNegocio = singleton.getUsuarioNegocio();

    public static void main(String[] args) throws IdadeExcetion, CarroException, MarcaException, ModeloException, PlacaException, NomeException, HabilitacaoException, CpfException, CategoriaNaoEncontradaException, ClienteNaoEncontradoException, UsuarioNaoEncontradoException {
        Test test = new Test();
        test.cadastrar();
        Aluguel aluguel = new Aluguel(
                test.clienteNegocio.consultar(1),
                null,
                test.categoriaNegocio.consultar(1),
                test.usuarioNegocio.consultar(1));
        aluguel.setRetirada(LocalDateTime.now());
        aluguel.setDevolucaoEstimada(LocalDateTime.now().plusDays(3));
        List<Categoria> categoriasDisponiveis = test.verificarCategoriasDisponiveis(aluguel);
        System.out.println(categoriasDisponiveis);

    }

    private List<Categoria> verificarCategoriasDisponiveis(Aluguel param) {

        LocalDateTime aluguelRetirada = param.getRetirada();
        LocalDateTime aluguelDevolucao = param.getDevolucaoEstimada();

        List<Categoria> categorias = this.categoriaNegocio.consultarTodos();

        List<Reserva> reservas = this.reservaNegocio.consultarTodos();

        // um set de categorias, a categoria deve estar contida em alguma reserva que deu conflito com o aluguel
        // e existir mais de um carro disponivel naquela categoria
        Set<Categoria> collect = reservas.
                stream().
                filter(reserva -> {
                    LocalDateTime reservaRetirada = reserva.getRetiradaPrevista();
                    LocalDateTime reservaDevolucao = reserva.getDevolucaoPrevista();
                    // verifica se está dando conflito com as datas do aluguel com as reservas existentes
                    return (reservaRetirada.isAfter(aluguelRetirada) && reservaRetirada.isBefore(aluguelDevolucao)) ||
                            (reservaDevolucao.isAfter(aluguelRetirada) && reservaDevolucao.isBefore(aluguelDevolucao)) ||
                            reservaRetirada.isEqual(aluguelRetirada) || reservaRetirada.isEqual(aluguelDevolucao) ||
                            reservaDevolucao.isEqual(aluguelRetirada) || reservaDevolucao.isEqual(aluguelDevolucao) ||
                            (aluguelRetirada.isAfter(reservaRetirada) && aluguelDevolucao.isBefore(reservaDevolucao));
                }).
                filter(reserva -> quantCarros(reserva.getCategoria()) > 1).
                map(Reserva::getCategoria).
                collect(Collectors.toSet());


        // adiciona as categorias que estão contidas nas reservas que não deram conflito com o aluguel e existir mais
        // de uma carro disponivel naquela categoria
        collect.addAll(
                reservas.
                        stream().
                        filter(reserva -> {
                            LocalDateTime reservaRetirada = reserva.getRetiradaPrevista();
                            LocalDateTime reservaDevolucao = reserva.getDevolucaoPrevista();
                            // verifica quais reservas não estão tendo conflito com o aluguel
                            return !(reservaRetirada.isAfter(aluguelRetirada) && reservaRetirada.isBefore(aluguelDevolucao)) ||
                                    (reservaDevolucao.isAfter(aluguelRetirada) && reservaDevolucao.isBefore(aluguelDevolucao)) ||
                                    reservaRetirada.isEqual(aluguelRetirada) || reservaRetirada.isEqual(aluguelDevolucao) ||
                                    reservaDevolucao.isEqual(aluguelRetirada) || reservaDevolucao.isEqual(aluguelDevolucao) ||
                                    (aluguelRetirada.isAfter(reservaRetirada) && aluguelDevolucao.isBefore(reservaDevolucao));
                        }).
                        filter(reserva -> quantCarros(reserva.getCategoria()) > 1).
                        map(Reserva::getCategoria).
                        collect(Collectors.toList()));

        // adiciona as categorias que não estão nas reservas e existe mais de um carro naquela categoria
        categorias.removeAll(reservas.stream().map(Reserva::getCategoria).collect(Collectors.toList()));
        collect.addAll(categorias.stream().filter(categoria -> quantCarros(categoria) > 1).collect(Collectors.toList()));
        return new ArrayList<>(collect);
    }

    private long quantCarros(Categoria categoria) {
        return this.carroNegocio.consultarTodos().stream().filter(carro -> carro.getCategoria().equals(categoria)).count();

    }

    private void cadastrar() throws PlacaException, MarcaException, CarroException, ModeloException, CpfException, NomeException, HabilitacaoException, IdadeExcetion, CategoriaNaoEncontradaException, UsuarioNaoEncontradoException, ClienteNaoEncontradoException {
        Categoria categoria = new Categoria("Simles", new BigDecimal("233.2"));
        Categoria categoria1 = new Categoria("Complicado", new BigDecimal("233.2"));
        this.categoriaNegocio.cadastrar(categoria);
        this.categoriaNegocio.cadastrar(categoria1);
        categoria = this.categoriaNegocio.consultar("Simles");
        categoria1 = this.categoriaNegocio.consultar("Complicado");

        Carro carro = new Carro("pla1231", "modelo", "moarca", 2, 3, categoria, Cambio.MANUAL, Direcao.MECANICA, true, true, true, true, true);
        Carro carro1 = new Carro("asi1231", "model", "oinasd", 2, 3, categoria1, Cambio.MANUAL, Direcao.MECANICA, true, true, true, true, true);
        Carro carro2 = new Carro("moi1212", "oiansd", "oinasd", 2, 3, categoria1, Cambio.MANUAL, Direcao.MECANICA, true, true, true, true, true);
        Carro carro3 = new Carro("nih1231", "oijasd", "oiasd", 2, 3, categoria1, Cambio.MANUAL, Direcao.MECANICA, true, true, true, true, true);
        Carro carro4 = new Carro("nim3244", "oijasd", "oinasd", 2, 3, categoria1, Cambio.MANUAL, Direcao.MECANICA, true, true, true, true, true);

        Cliente cliente = new Cliente("12636763406", "nome", LocalDate.now().minusYears(20), "12121212121");
        this.clienteNegocio.cadastrar(cliente);
        cliente = this.clienteNegocio.consultar(1);

        Usuario usuario = new Usuario("12636763406", "nome", LocalDate.now().minusYears(19), "oi", true);
        this.usuarioNegocio.cadastrar(usuario);
        usuario = this.usuarioNegocio.consultar(1);

        Reserva reserva = new Reserva(usuario, cliente, categoria, LocalDateTime.now(), LocalDateTime.now().plusDays(2));
        this.reservaNegocio.cadastrar(reserva);

        this.carroNegocio.cadastrar(carro);
        this.carroNegocio.cadastrar(carro1);
        this.carroNegocio.cadastrar(carro2);
        this.carroNegocio.cadastrar(carro3);
        this.carroNegocio.cadastrar(carro4);
    }
}
