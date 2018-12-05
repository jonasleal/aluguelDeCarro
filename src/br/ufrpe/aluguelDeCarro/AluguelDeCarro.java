/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro;

import br.ufrpe.aluguelDeCarro.apresentacao.PrincipalApresentacao;

/**
 * @author JonasJr
 */
public class AluguelDeCarro {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {

            PrincipalApresentacao apresentacao = new PrincipalApresentacao();

//            Gerente gerente = new Gerente("02362263045", "kol", LocalDate.now().minusYears(20), Criptografia.criptografarSenha("oi"));
//            Singleton.getInstance().getUsuarioNegocio().cadastrar(gerente);
//            Singleton.getInstance().setUsuarioLogado(gerente);
//
//            Carro carro = new Carro("pop3444","mod", "mar", Categoria.PERUA, new BigDecimal(34));
//            carro.setDirecao(Direcao.MECANICA);
//            carro.setCambio(Cambio.AUTOMATICO);
//            carro.setPortas(4);
//            carro.setOcupantes(4);
//            Singleton.getInstance().getCarroNegocio().cadastrar(carro, (Gerente) Singleton.getInstance().getUsuarioLogado());
//
//            Cliente cliente = new Cliente("02362263045", "geh", LocalDate.now().minusYears(20), "30870472789");
//            Singleton.getInstance().getClienteNegocio().cadastrar(cliente);

            apresentacao.menus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
