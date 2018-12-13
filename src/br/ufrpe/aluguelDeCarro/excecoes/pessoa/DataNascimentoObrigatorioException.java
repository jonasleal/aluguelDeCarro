package br.ufrpe.aluguelDeCarro.excecoes.pessoa;

/**
 * @author Fernando
 */
public class DataNascimentoObrigatorioException extends PessoaInvalidaException {
    public DataNascimentoObrigatorioException() {
        super("Data de nascimento é obrigatoria");
    }
}
