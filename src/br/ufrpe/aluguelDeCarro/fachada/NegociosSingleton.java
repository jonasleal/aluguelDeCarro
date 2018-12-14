package br.ufrpe.aluguelDeCarro.fachada;

import br.ufrpe.aluguelDeCarro.dados.repositorios.memoria.*;
import br.ufrpe.aluguelDeCarro.negocio.*;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Usuario;

class NegociosSingleton {

    private static NegociosSingleton ourInstance = new NegociosSingleton();
    private final CarroNegocio carroNegocio;
    private final ClienteNegocio clienteNegocio;
    private final UsuarioNegocio usuarioNegocio;
    private final AluguelNegocio aluguelNegocio;
    private final CategoriaNegocio categoriaNegocio;
    private final ManutencaoNegocio manutencaoNegocio;
    private final ReservaNegocio reservaNegocio;
    private Usuario usuarioLogado;

    private NegociosSingleton() {
        this.carroNegocio = new CarroNegocio(new CarroRepositorio());
        this.clienteNegocio = new ClienteNegocio(new ClienteRepositorio());
        this.usuarioNegocio = new UsuarioNegocio(new UsuarioRepositorio());
        this.aluguelNegocio = new AluguelNegocio(new AluguelRepositorio());
        this.categoriaNegocio = new CategoriaNegocio(new CategoriaRepositorio());
        this.manutencaoNegocio = new ManutencaoNegocio(new ManutencaoRepositorio());
        this.reservaNegocio = new ReservaNegocio(new ReservaRepositorio());
    }

    static NegociosSingleton getInstance() {
        return ourInstance;
    }

    CarroNegocio getCarroNegocio() {
        return carroNegocio;
    }

    ClienteNegocio getClienteNegocio() {
        return clienteNegocio;
    }

    UsuarioNegocio getUsuarioNegocio() {
        return usuarioNegocio;
    }

    AluguelNegocio getAluguelNegocio() {
        return aluguelNegocio;
    }

    CategoriaNegocio getCategoriaNegocio() {
        return categoriaNegocio;
    }

    ManutencaoNegocio getManutencaoNegocio() {
        return manutencaoNegocio;
    }

    ReservaNegocio getReservaNegocio() {
        return reservaNegocio;
    }

    /**
     * @return o usuário que está utilizando o sistema no momemnto
     */
    Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    /**
     * @param usuarioLogado o usuário que está utilizando o sistema no momento
     */
    void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

}
