package br.ufrpe.aluguelDeCarro.apresentacao.console;

import br.ufrpe.aluguelDeCarro.excecoes.aluguel.AluguelInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.carro.CarroInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.pessoa.PessoaInvalidaException;
import br.ufrpe.aluguelDeCarro.fachada.FachadaGerente;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Aluguel;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Carro;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Cliente;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Usuario;
import br.ufrpe.aluguelDeCarro.servicos.InputUtil;

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
        FachadaGerente.getInstance().setUsuarioLogado(usuario);
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
            FachadaGerente.getInstance().cadastrarUsuario(usuario);
        } catch (PessoaInvalidaException e) {
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
            System.out.println("1 - Cadastrar carro\n2 - Cadastrar cliente\n3 - Cadastrar aluguel\n4 - Visualizar carros\n5 - Visualizar clientes\n6 - Visualizar alugueis\n0 - Sair");
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
            FachadaGerente.getInstance().cadastrarAluguel(aluguel);
        } catch (CarroInvalidoException | AluguelInvalidoException | ClienteInvalidoException e) {
            System.out.println(e.getMessage());
            cadastrarAluguel();
        }
    }

    private void cadastrarCliente() {
        Cliente cliente = this.clienteApresentacao.lerDadosPeloTeclado();
        try {
            FachadaGerente.getInstance().cadastrarCliente(cliente);
        } catch (PessoaInvalidaException | ClienteInvalidoException e) {
            System.out.println(e.getMessage());
            cadastrarCliente();
        }
    }

    private void cadastrarCarro() {
        Usuario usuarioLogado = FachadaGerente.getInstance().getUsuarioLogado();
        if (usuarioLogado.isGerente()) {
            Carro carro = this.carroApresentacao.lerDadosPeloTeclado();
            try {
                FachadaGerente.getInstance().cadastrarCarro(carro);
            } catch (CarroInvalidoException e) {
                System.out.println(e.getMessage());
                cadastrarCarro();
            }
        }
    }
}