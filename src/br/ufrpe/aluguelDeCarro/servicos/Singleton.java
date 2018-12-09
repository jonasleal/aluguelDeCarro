package br.ufrpe.aluguelDeCarro.servicos;

import br.ufrpe.aluguelDeCarro.dados.entidades.Usuario;
import br.ufrpe.aluguelDeCarro.dados.repositorios.memoria.AluguelRepositorio;
import br.ufrpe.aluguelDeCarro.dados.repositorios.memoria.CarroRepositorio;
import br.ufrpe.aluguelDeCarro.dados.repositorios.memoria.ClienteRepositorio;
import br.ufrpe.aluguelDeCarro.dados.repositorios.memoria.UsuarioRepositorio;
import br.ufrpe.aluguelDeCarro.negocio.AluguelNegocio;
import br.ufrpe.aluguelDeCarro.negocio.CarroNegocio;
import br.ufrpe.aluguelDeCarro.negocio.ClienteNegocio;
import br.ufrpe.aluguelDeCarro.negocio.UsuarioNegocio;

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

    private Usuario usuarioLogado;

    private Singleton() {
        this.carroNegocio = new CarroNegocio(new CarroRepositorio());
        this.clienteNegocio = new ClienteNegocio(new ClienteRepositorio());
        this.usuarioNegocio = new UsuarioNegocio(new UsuarioRepositorio());
        this.aluguelNegocio = new AluguelNegocio(new AluguelRepositorio());
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