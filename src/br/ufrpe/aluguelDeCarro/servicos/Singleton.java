package br.ufrpe.aluguelDeCarro.servicos;

import br.ufrpe.aluguelDeCarro.dados.entidades.Usuario;
import br.ufrpe.aluguelDeCarro.dados.repositorios.AluguelRepositorio;
import br.ufrpe.aluguelDeCarro.dados.repositorios.CarroRepositorio;
import br.ufrpe.aluguelDeCarro.dados.repositorios.ClienteRepositorio;
import br.ufrpe.aluguelDeCarro.dados.repositorios.GerenteRepositorio;
import br.ufrpe.aluguelDeCarro.negocio.AluguelNegocio;
import br.ufrpe.aluguelDeCarro.negocio.CarroNegocio;
import br.ufrpe.aluguelDeCarro.negocio.ClienteNegocio;
import br.ufrpe.aluguelDeCarro.negocio.GerenteNegocio;

/**
 * @author Fernando
 */
public class Singleton {

    private static Singleton myself = null;

    private CarroNegocio carroNegocio;
    private ClienteNegocio clienteNegocio;
    private GerenteNegocio gerenteNegocio;
    private AluguelNegocio aluguelNegocio;

    private Usuario usuarioLogado;

    private Singleton(){
        this.carroNegocio = new CarroNegocio(new CarroRepositorio());
        this.clienteNegocio = new ClienteNegocio(new ClienteRepositorio());
        this.gerenteNegocio = new GerenteNegocio(new GerenteRepositorio());
        this.aluguelNegocio = new AluguelNegocio(new AluguelRepositorio());
    }

    public static Singleton getInstance(){
        if(myself == null)
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

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
}