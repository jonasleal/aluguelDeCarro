package br.ufrpe.aluguelDeCarro.apresentacao;

import br.ufrpe.aluguelDeCarro.dados.entidades.*;
import br.ufrpe.aluguelDeCarro.excecoes.*;
import br.ufrpe.aluguelDeCarro.servicos.InputUtil;
import br.ufrpe.aluguelDeCarro.servicos.Singleton;

/**
 * Classe central das interações com o usuário
 * @author Fernando
 */
public class PrincipalApresentacao {
    private final CarroApresentacao carroApresentacao;
    private final ClienteApresentacao clienteApresentacao;
    private final GerenteApresentacao gerenteApresentacao;
    private final AluguelApresentacao aluguelApresentacao;
    private final LoginApresentacao loginApresentacao;

    public PrincipalApresentacao() {
        this.carroApresentacao = new CarroApresentacao();
        this.clienteApresentacao = new ClienteApresentacao();
        this.gerenteApresentacao = new GerenteApresentacao();
        this.aluguelApresentacao = new AluguelApresentacao();
        this.loginApresentacao = new LoginApresentacao();
    }

    public void menus() throws IdadeExcetion, NomeException, CpfException, PlacaException, HabilitacaoException, MarcaException, ModeloException, AluguelException, CarroException {
        cadastrarGerente();
        login();
        this.opcoes();
    }

    /**
     * efetua o login do usuário no sistema
     */
    private void login() {
        System.out.println("Efetue o login");
        Usuario usuario = null;
        while (usuario == null)
            usuario = this.loginApresentacao.lerDadosPeloTeclado();
        Singleton.getInstance().setUsuarioLogado(usuario);
    }

    /**
     * efetua o cadastro do gerente no sistema
     */
    private void cadastrarGerente() {
        System.out.println("Cadastre o gerente");
        Gerente gerente = null;
        while (gerente == null)
            gerente = this.gerenteApresentacao.lerDadosPeloTeclado();
        try {
            Singleton.getInstance().getGerenteNegocio().cadastrar(gerente);
        } catch (CpfException | IdadeExcetion | HabilitacaoException | NomeException e) {
            System.out.println(e.getMessage());
            cadastrarGerente();
        }
    }

    /**
     * mostra ao usuário as funcionalidades do sistema, e solicita que o mesmo escolha uma para executar
     */
    private void opcoes() {
        int opcao;
        do {
            System.out.println("1 - Cadastrar carro\n2 - Cadastrar cliente\n3 - Cadastrar Aluguel\n4 - Visualizar carros\n5 - Visualizar clientes\n6 - Visualizar alugueis\n0 - Sair");
            opcao = InputUtil.getScan().nextInt();
            switch (opcao) {
                case 1:
                    cadastrarCarro();
                    break;
                case 2:
                    cadastrarCliente();
                    break;
                case 3:
                    cadastrarAluguel();
                    break;
                case 4:
                    this.carroApresentacao.visualizarCarros();
                    break;
                case 5:
                    this.clienteApresentacao.visualizarClientes();
                    break;
                case 6:
                    this.aluguelApresentacao.visualizarAlugueis();
                    break;
                default:
                    break;
            }
        } while (opcao != 0);
    }

    private void cadastrarAluguel() {
        Aluguel aluguel = this.aluguelApresentacao.lerDadosPeloTeclado();
        try {
            Singleton.getInstance().getAluguelNegocio().cadastrar(aluguel);
        } catch (AluguelException e) {
            System.out.println(e.getMessage());
            cadastrarAluguel();
        }
    }

    private void cadastrarCliente() {
        Cliente cliente = this.clienteApresentacao.lerDadosPeloTeclado();
        try {
            Singleton.getInstance().getClienteNegocio().cadastrar(cliente);
        } catch (CpfException | IdadeExcetion | HabilitacaoException | NomeException e) {
            System.out.println(e.getMessage());
            cadastrarCliente();
        }
    }

    private void cadastrarCarro() {
        Usuario usuarioLogado = Singleton.getInstance().getUsuarioLogado();
        if(usuarioLogado instanceof Gerente) {
            Carro carro = this.carroApresentacao.lerDadosPeloTeclado();
            try {
                Singleton.getInstance().getCarroNegocio().cadastrar(carro, (Gerente) usuarioLogado);
            } catch (PlacaException | CpfException | NomeException | HabilitacaoException | ModeloException | CarroException | IdadeExcetion | MarcaException e) {
                System.out.println(e.getMessage());
                cadastrarCarro();
            }
        }
    }
}