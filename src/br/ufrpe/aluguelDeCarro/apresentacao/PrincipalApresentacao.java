package br.ufrpe.aluguelDeCarro.apresentacao;

import br.ufrpe.aluguelDeCarro.dados.entidades.Aluguel;
import br.ufrpe.aluguelDeCarro.dados.entidades.Carro;
import br.ufrpe.aluguelDeCarro.dados.entidades.Cliente;
import br.ufrpe.aluguelDeCarro.dados.entidades.Usuario;
import br.ufrpe.aluguelDeCarro.excecoes.*;
import br.ufrpe.aluguelDeCarro.servicos.InputUtil;
import br.ufrpe.aluguelDeCarro.servicos.Singleton;

/**
 * Classe central das interações com o usuário
 *
 * @author Fernando
 */
public class PrincipalApresentacao {
    private final CarroApresentacao carroApresentacao;
    private final ClienteApresentacao clienteApresentacao;
    private final UsuarioApresentacao gerenteApresentacao;
    private final AluguelApresentacao aluguelApresentacao;
    private final LoginApresentacao loginApresentacao;

    public PrincipalApresentacao() {
        this.carroApresentacao = new CarroApresentacao();
        this.clienteApresentacao = new ClienteApresentacao();
        this.gerenteApresentacao = new UsuarioApresentacao();
        this.aluguelApresentacao = new AluguelApresentacao();
        this.loginApresentacao = new LoginApresentacao();
    }

    public void menus() {
        cadastrarUsuario();
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
    private void cadastrarUsuario() {
        System.out.println("Cadastre o usuario");
        Usuario usuario = null;
        while (usuario == null)
            usuario = this.gerenteApresentacao.lerDadosPeloTeclado();
        try {
            Singleton.getInstance().getUsuarioNegocio().cadastrar(usuario);
        } catch (CpfException | IdadeExcetion | HabilitacaoException | NomeException e) {
            System.out.println(e.getMessage());
            cadastrarUsuario();
        }
    }

    /**
     * mostra ao usuário as funcionalidades do sistema, e solicita que o mesmo escolha uma para executar
     */
    private void opcoes() {
        int opcao;
        do {
            System.out.println("1 - Cadastrar carro\n2 - Cadastrar cliente\n3 - Cadastrar Aluguel\n4 - Visualizar carros\n5 - Visualizar clientes\n6 - Visualizar alugueis\n0 - Sair");
            opcao = InputUtil.solicitarNumeroInteiro();
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
        if (usuarioLogado.isGerente()) {
            Carro carro = this.carroApresentacao.lerDadosPeloTeclado();
            try {
                Singleton.getInstance().getCarroNegocio().cadastrar(carro);
            } catch (PlacaException | ModeloException | CarroException | MarcaException e) {
                System.out.println(e.getMessage());
                cadastrarCarro();
            }
        }
    }
}