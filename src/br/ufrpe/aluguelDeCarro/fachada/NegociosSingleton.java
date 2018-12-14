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
        if (ourInstance == null)
            ourInstance = new NegociosSingleton();
        return ourInstance;
    }

    CarroNegocio getCarroNegocio() {
        return this.carroNegocio;
    }

    ClienteNegocio getClienteNegocio() {
        return this.clienteNegocio;
    }

    UsuarioNegocio getUsuarioNegocio() {
        return this.usuarioNegocio;
    }

    AluguelNegocio getAluguelNegocio() {
        return this.aluguelNegocio;
    }

    CategoriaNegocio getCategoriaNegocio() {
        return this.categoriaNegocio;
    }

    ManutencaoNegocio getManutencaoNegocio() {
        return this.manutencaoNegocio;
    }

    ReservaNegocio getReservaNegocio() {
        return this.reservaNegocio;
    }

    /**
     * @return o usuário que está utilizando o sistema no momemnto
     */
    Usuario getUsuarioLogado() {
        return this.usuarioLogado;
    }

    /**
     * @param usuarioLogado o usuário que está utilizando o sistema no momento
     */
    void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

}
