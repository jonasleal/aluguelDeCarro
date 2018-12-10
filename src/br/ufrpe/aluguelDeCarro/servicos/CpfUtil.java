/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.servicos;

import br.ufrpe.aluguelDeCarro.excecoes.pessoa.CpfInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.pessoa.CpfObrigatorioException;

/**
 * @author JonasJr
 */
public class CpfUtil {

    public static String cpfToString(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            return "";
        }
        String cpfFormatado;
        cpfFormatado = cpf.substring(0, 3) + "." + cpf.substring(3, 6)
                + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
        return cpfFormatado;
    }

    public static void validarCPF(String cpf) throws CpfObrigatorioException, CpfInvalidoException {
        if (cpf == null || cpf.isEmpty()) {
            throw new CpfObrigatorioException();
        }
        if (cpf.equals("00000000000") || cpf.equals("11111111111")
                || cpf.equals("22222222222") || cpf.equals("33333333333")
                || cpf.equals("44444444444") || cpf.equals("55555555555")
                || cpf.equals("66666666666") || cpf.equals("77777777777")
                || cpf.equals("88888888888") || cpf.equals("99999999999")) {
            throw new CpfInvalidoException();
        }

        char dig10, dig11;
        int soma, num, resul, peso;

        try {
            soma = 0;
            peso = 10;

            for (int i = 0; i < 9; i++) {
                num = cpf.charAt(i) - 48;
                soma += (num * peso);
                peso--;
            }

            resul = 11 - (soma % 11);
            if ((resul == 10) || (resul == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (resul + 48);
            }

            soma = 0;
            peso = 11;

            for (int i = 0; i < 10; i++) {
                num = cpf.charAt(i) - 48;
                soma += (num * peso);
                peso--;
            }

            resul = 11 - (soma % 11);
            if ((resul == 10) || (resul == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (resul + 48);
            }

            if ((cpf.charAt(9) != dig10) || (cpf.charAt(10) != dig11)) {
                throw new CpfInvalidoException();
            }
        } catch (Exception e) {
            throw new CpfInvalidoException(e.getCause());
        }

    }

}
