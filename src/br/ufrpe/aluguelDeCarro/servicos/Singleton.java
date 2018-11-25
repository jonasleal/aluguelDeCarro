package br.ufrpe.aluguelDeCarro.servicos;

import br.ufrpe.aluguelDeCarro.dados.entidades.IUsuario;
import br.ufrpe.aluguelDeCarro.dados.repositorios.AluguelRepositorio;
import br.ufrpe.aluguelDeCarro.dados.repositorios.CarroRepositorio;
import br.ufrpe.aluguelDeCarro.dados.repositorios.ClienteRepositorio;
import br.ufrpe.aluguelDeCarro.dados.repositorios.GerenteRepositorio;
import br.ufrpe.aluguelDeCarro.negocio.AluguelNegocio;
import br.ufrpe.aluguelDeCarro.negocio.CarroNegocio;
import br.ufrpe.aluguelDeCarro.negocio.ClienteNegocio;
import br.ufrpe.aluguelDeCarro.negocio.GerenteNegocio;

/**
 * Esta classe serve para centralizar todas classes de negócio
 *
 * @author Fernando
 */
public class Singleton {

    private static Singleton myself = null;

    private final CarroNegocio carroNegocio;
    private final ClienteNegocio clienteNegocio;
    private final GerenteNegocio gerenteNegocio;
    private final AluguelNegocio aluguelNegocio;

    private IUsuario usuarioLogado;

    private Singleton() {
        this.carroNegocio = new CarroNegocio(new CarroRepositorio());
        this.clienteNegocio = new ClienteNegocio(new ClienteRepositorio());
        this.gerenteNegocio = new GerenteNegocio(new GerenteRepositorio());
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

    public GerenteNegocio getGerenteNegocio() {
        return gerenteNegocio;
    }

    public AluguelNegocio getAluguelNegocio() {
        return aluguelNegocio;
    }

    /**
     * @return o usuário que está utilizando o sistema no momemnto
     */
    public IUsuario getUsuarioLogado() {
        return usuarioLogado;
    }

    /**
     * @param usuarioLogado o usuário que está utilizando o sistema no momento
     */
    public void setUsuarioLogado(IUsuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
}