package br.ufrpe.aluguelDeCarro.servicos;

import br.ufrpe.aluguelDeCarro.dados.entidades.Usuario;
import br.ufrpe.aluguelDeCarro.dados.repositorios.memoria.*;
import br.ufrpe.aluguelDeCarro.negocio.*;

/**
 * Esta classe serve para centralizar todas classes de negócio
 *
 * @author Fernando
 */
public class Singleton {

    private static Singleton myself = null;

    private final CarroNegocio carroNegocio;
    private final ClienteNegocio clienteNegocio;
    private final UsuarioNegocio usuarioNegocio;
    private final AluguelNegocio aluguelNegocio;
    private final CategoriaNegocio categoriaNegocio;
    private final ManutencaoNegocio manutencaoNegocio;
    private final ReservaNegocio reservaNegocio;

    private Usuario usuarioLogado;

    private Singleton() {
        this.carroNegocio = new CarroNegocio(new CarroRepositorio());
        this.clienteNegocio = new ClienteNegocio(new ClienteRepositorio());
        this.usuarioNegocio = new UsuarioNegocio(new UsuarioRepositorio());
        this.aluguelNegocio = new AluguelNegocio(new AluguelRepositorio());
        this.categoriaNegocio = new CategoriaNegocio(new CategoriaRepositorio());
        this.manutencaoNegocio = new ManutencaoNegocio(new ManutencaoRepositorio());
        this.reservaNegocio = new ReservaNegocio(new ReservaRepositorio());
    }

    /**
     * @return uma instancia da classe, caso já tenha sido inicializada simplementes a retorna, caso contrário cria uma
     * nova
     */
    public static Singleton getInstance() {
        if (myself == null)
            myself = new Singleton();
        return myself;
    }

    public CarroNegocio getCarroNegocio() {
        return carroNegocio;
    }

    public ClienteNegocio getClienteNegocio() {
        return clienteNegocio;
    }

    public UsuarioNegocio getUsuarioNegocio() {
        return usuarioNegocio;
    }

    public AluguelNegocio getAluguelNegocio() {
        return aluguelNegocio;
    }

    public CategoriaNegocio getCategoriaNegocio() {
        return categoriaNegocio;
    }

    public ManutencaoNegocio getManutencaoNegocio() {
        return manutencaoNegocio;
    }

    public ReservaNegocio getReservaNegocio() {
        return reservaNegocio;
    }

    /**
     * @return o usuário que está utilizando o sistema no momemnto
     */
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    /**
     * @param usuarioLogado o usuário que está utilizando o sistema no momento
     */
    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
}