package br.ufrpe.aluguelDeCarro.fachada;

import br.ufrpe.aluguelDeCarro.dados.repositorios.memoria.*;
import br.ufrpe.aluguelDeCarro.excecoes.carro.CarroInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.categoria.CategoriaInvalidaException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.pessoa.PessoaInvalidaException;
import br.ufrpe.aluguelDeCarro.excecoes.usuario.UsuarioInvalidoException;
import br.ufrpe.aluguelDeCarro.negocio.*;
import br.ufrpe.aluguelDeCarro.negocio.entidades.*;

import java.math.BigDecimal;
import java.time.LocalDate;

class NegociosSingleton {

    private static NegociosSingleton ourInstance = new NegociosSingleton();
    private final CarroNegocio carroNegocio;
    private final ClienteNegocio clienteNegocio;
    private final UsuarioNegocio usuarioNegocio;
    private final AluguelNegocio aluguelNegocio;
    private final CategoriaNegocio categoriaNegocio;
    private final ManutencaoNegocio manutencaoNegocio;
    private final ReservaNegocio reservaNegocio;

    private NegociosSingleton() {
        CarroRepositorio carroRepositorio = new CarroRepositorio();
        ClienteRepositorio clienteRepositorio = new ClienteRepositorio();
        CategoriaRepositorio categoriaRepositorio = new CategoriaRepositorio();
        ReservaRepositorio reservaRepositorio = new ReservaRepositorio();
        AluguelRepositorio aluguelRepositorio = new AluguelRepositorio();
        this.carroNegocio = new CarroNegocio(carroRepositorio);
        this.clienteNegocio = new ClienteNegocio(clienteRepositorio);
        this.usuarioNegocio = new UsuarioNegocio(new UsuarioRepositorio());
        this.aluguelNegocio = new AluguelNegocio(aluguelRepositorio, categoriaRepositorio, carroRepositorio, reservaRepositorio);
        this.categoriaNegocio = new CategoriaNegocio(categoriaRepositorio);
        this.manutencaoNegocio = new ManutencaoNegocio(new ManutencaoRepositorio());
        this.reservaNegocio = new ReservaNegocio(reservaRepositorio, aluguelRepositorio, carroRepositorio, categoriaRepositorio);
        try {
            this.usuarioNegocio.cadastrar(new Usuario("16125653013", "Don", LocalDate.now().minusYears(20), "900150983cd24fb0d6963f7d28e17f72", true));
            System.out.println("Login: 16125653013");
            Cliente cliente = new Cliente("12636763406", "Jose", LocalDate.now().minusYears(20), "12012012012");
            this.clienteNegocio.cadastrar(cliente);
            Categoria categoria = new Categoria("Nome", new BigDecimal(20));
            categoriaNegocio.cadastrar(categoria);
            Carro carro = new Carro("pla1234", "modelo", "marca", 3, 3, categoria, Cambio.MANUAL, Direcao.MECANICA, false, false, false, false, false);
            carroNegocio.cadastrar(carro);
        } catch (PessoaInvalidaException | UsuarioInvalidoException | ClienteInvalidoException | CategoriaInvalidaException | CarroInvalidoException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
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
}
