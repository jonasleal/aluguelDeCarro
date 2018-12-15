package br.ufrpe.aluguelDeCarro;

/**
 * @author Fernando
 */
public class Test {

    public static void main(String[] args) {
    }

//    private List<Categoria> consultarCategoriasDisponiveisParaAluguel(Aluguel aluguel) {
//        List<Reserva> reservas = this.reservaNegocio.consultarTodos();
//        List<Reserva> reservasComConflito = reservasComConflitoComAluguel(reservas, aluguel);
//        Set<Categoria> collect = categoriasDasReservasComConflito(reservasComConflito);
//        List<Reserva> reservasSemConflito = reservasSemConflitoComAluguel(reservas, aluguel);
//        collect.addAll(categoriasDasReservasSemConflito(reservasSemConflito));
//        collect.addAll(categoriasNaoContidasNasReservas(reservas));
//        return new ArrayList<>(collect);
//    }
//
//    private List<Categoria> categoriasNaoContidasNasReservas(List<Reserva> reservas) {
//        List<Categoria> categorias = this.categoriaNegocio.consultarTodos();
//        categorias.removeAll(reservas.stream().map(Reserva::getCategoria).collect(Collectors.toSet()));
//        return categorias;
//    }
//
//    private List<Categoria> categoriasDasReservasSemConflito(List<Reserva> reservasSemConflito) {
//        return reservasSemConflito
//                .stream()
//                .filter(reserva -> quantidadeDeCarrosPor(reserva.getCategoria()) > 1)
//                .map(Reserva::getCategoria)
//                .collect(Collectors.toList());
//    }
//
//    private Set<Categoria> categoriasDasReservasComConflito(List<Reserva> reservasComConflito) {
//        return reservasComConflito
//                .stream()
//                .filter(reserva -> quantidadeDeCarrosPor(reserva.getCategoria()) > quantidadeDeReservasPor(reservasComConflito, reserva.getCategoria()))
//                .map(Reserva::getCategoria)
//                .collect(Collectors.toSet());
//    }
//
//    private List<Reserva> reservasComConflitoComAluguel(List<Reserva> reservas, Aluguel aluguel) {
//        return reservas
//                .stream()
//                .filter(reserva -> conflitoAluguelReserva(
//                        aluguel.getRetirada(), aluguel.getDevolucaoEstimada(), reserva.getRetiradaPrevista(), reserva.getDevolucaoPrevista()))
//                .collect(Collectors.toList());
//    }
//
//    private List<Reserva> reservasSemConflitoComAluguel(List<Reserva> reservas, Aluguel aluguel) {
//        return reservas
//                .stream()
//                .filter(reserva -> !conflitoAluguelReserva(
//                        aluguel.getRetirada(), aluguel.getDevolucaoEstimada(), reserva.getRetiradaPrevista(), reserva.getDevolucaoPrevista()))
//                .collect(Collectors.toList());
//    }
//
//    private long quantidadeDeReservasPor(List<Reserva> reservas, Categoria categoria) {
//        return reservas.stream().filter(reserva -> reserva.getCategoria().equals(categoria)).count();
//    }
//
//    private boolean conflitoAluguelReserva(LocalDateTime aluguelRetirada, LocalDateTime aluguelDevolucao, LocalDateTime reservaRetirada, LocalDateTime reservaDevolucao) {
//        return estaNoIntervalo(reservaRetirada, aluguelRetirada, aluguelDevolucao) ||
//                estaNoIntervalo(reservaDevolucao, aluguelRetirada, aluguelDevolucao) ||
//                estaNoIntervalo(aluguelRetirada, reservaRetirada, reservaDevolucao);
//    }
//
//    /**
//     * Verifica se o parametro time está no intervalo (incluso) end e time
//     *
//     * @param start data inicial do intervalo
//     * @param end   data final do intervalo
//     * @param time  data a ser avaliada
//     * @return {@code true} se a data estiver contida no intervalo, {@code false} caso contrário
//     */
//    private boolean estaNoIntervalo(LocalDateTime time, LocalDateTime start, LocalDateTime end) {
//        return !time.isBefore(start) && !time.isAfter(end);
//    }
//
//    private long quantidadeDeCarrosPor(Categoria categoria) {
//        return this.carroNegocio.consultarTodos().stream().filter(carro -> carro.getCategoria().equals(categoria)).count();
//    }
//
//    private void cadastrar() throws PlacaException, MarcaException, CarroException, ModeloException, CpfException, NomeException, HabilitacaoException, IdadeExcetion, CategoriaNaoEncontradaException, UsuarioNaoEncontradoException, ClienteNaoEncontradoException {
//        Categoria categoria = new Categoria("Simles", new BigDecimal("233.2"));
//        Categoria categoria1 = new Categoria("Complicado", new BigDecimal("233.2"));
//        this.categoriaNegocio.cadastrar(categoria);
//        this.categoriaNegocio.cadastrar(categoria1);
//        categoria = this.categoriaNegocio.consultar("Simles");
//        categoria1 = this.categoriaNegocio.consultar("Complicado");
//
//        Carro carro = new Carro("pla1231", "modelo", "moarca", 2, 3, categoria, Cambio.MANUAL, Direcao.MECANICA, true, true, true, true, true);
//        Carro carro1 = new Carro("asi1231", "model", "oinasd", 2, 3, categoria1, Cambio.MANUAL, Direcao.MECANICA, true, true, true, true, true);
//        Carro carro2 = new Carro("moi1212", "oiansd", "oinasd", 2, 3, categoria1, Cambio.MANUAL, Direcao.MECANICA, true, true, true, true, true);
//        Carro carro3 = new Carro("nih1231", "oijasd", "oiasd", 2, 3, categoria1, Cambio.MANUAL, Direcao.MECANICA, true, true, true, true, true);
//        Carro carro4 = new Carro("nim3244", "oijasd", "oinasd", 2, 3, categoria1, Cambio.MANUAL, Direcao.MECANICA, true, true, true, true, true);
//
//        Cliente cliente = new Cliente("12636763406", "nome", LocalDate.now().minusYears(20), "12121212121");
//        this.clienteNegocio.cadastrar(cliente);
//        cliente = this.clienteNegocio.consultar(1);
//
//        Usuario usuario = new Usuario("12636763406", "nome", LocalDate.now().minusYears(19), "oi", true);
//        this.usuarioNegocio.cadastrar(usuario);
//        usuario = this.usuarioNegocio.consultar(1);
//
//        Reserva reserva = new Reserva(usuario, cliente, categoria1, LocalDateTime.now(), LocalDateTime.now().plusDays(2));
//        this.reservaNegocio.cadastrar(reserva);
//
//        this.carroNegocio.cadastrar(carro);
//        this.carroNegocio.cadastrar(carro1);
//        this.carroNegocio.cadastrar(carro2);
//        this.carroNegocio.cadastrar(carro3);
//        this.carroNegocio.cadastrar(carro4);
//    }
}
