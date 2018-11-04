package br.ufrpe.aluguelDeCarro.apresentacao;

import br.ufrpe.aluguelDeCarro.dados.entidades.*;
import br.ufrpe.aluguelDeCarro.excecoes.*;
import br.ufrpe.aluguelDeCarro.servicos.InputUtil;
import br.ufrpe.aluguelDeCarro.servicos.Singleton;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

/**
 * @author Fernando
 */
public class PrincipalApresentacao {
    private CarroApresentacao carroApresentacao;
    private ClienteApresentacao clienteApresentacao;
    private GerenteApresentacao gerenteApresentacao;
    private AluguelApresentacao aluguelApresentacao;
    private LoginApresentacao loginApresentacao;

    public PrincipalApresentacao() {
        this.carroApresentacao = new CarroApresentacao();
        this.clienteApresentacao = new ClienteApresentacao();
        this.gerenteApresentacao = new GerenteApresentacao();
        this.aluguelApresentacao = new AluguelApresentacao();
        this.loginApresentacao = new LoginApresentacao();
    }

    public void menus() throws IdadeExcetion, NomeException, CpfException, PlacaException, HabilitacaoException, MarcaException, ModeloException, AluguelException, CarroException {
        System.out.println("Cadastre o gerente");
        Gerente gerente = null;
        while (gerente == null)
            gerente = this.gerenteApresentacao.lerDadosPeloTeclado();
        Singleton.getInstance().getGerenteNegocio().cadastrar(gerente);
        System.out.println("Efetue o login");
        Usuario usuario = null;
        while (usuario == null)
            usuario = this.loginApresentacao.lerDadosPeloTeclado();
        Singleton.getInstance().setUsuarioLogado(usuario);
        this.opcoes();
    }

    private void opcoes() throws PlacaException, IdadeExcetion, NomeException, CpfException, HabilitacaoException, ModeloException, MarcaException, CarroException, AluguelException {
        int opcao;
        do {
            System.out.println("1 - Cadastrar carro\n2 - Cadastrar cliente\n3 - Cadastrar Aluguel\n4 - Visualizar carros\n5 - Visualizar clientes\n6 - Visualizar alugueis\n0 - Sair");
            opcao = InputUtil.getScan().nextInt();
            switch (opcao) {
                case 1:
                    Usuario usuarioLogado = Singleton.getInstance().getUsuarioLogado();
                    if(usuarioLogado instanceof Gerente) {
                        Carro carro = this.carroApresentacao.lerDadosPeloTeclado();
                        Singleton.getInstance().getCarroNegocio().cadastrar(carro, (Gerente) usuarioLogado);
                    }
                    break;
                case 2:
                    Cliente cliente = this.clienteApresentacao.lerDadosPeloTeclado();
                    Singleton.getInstance().getClienteNegocio().cadastrar(cliente);
                    break;
                case 3:
                    Aluguel aluguel = this.aluguelApresentacao.lerDadosPeloTeclado();
                    Singleton.getInstance().getAluguelNegocio().cadastrar(aluguel);
                    System.out.println(Singleton.getInstance().getAluguelNegocio().buscarPorId(1));
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
}