package br.ufrpe.aluguelDeCarro.apresentacao.gui.gerente.controller;

import br.ufrpe.aluguelDeCarro.excecoes.CategoriaNaoEncontradaException;
import br.ufrpe.aluguelDeCarro.excecoes.aluguel.*;
import br.ufrpe.aluguelDeCarro.excecoes.bancoDeDados.IdNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.carro.CarroInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.carro.CarroNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.categoria.CategoriaInvalidaException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.pessoa.PessoaInvalidaException;
import br.ufrpe.aluguelDeCarro.excecoes.usuario.UsuarioInvalidoException;
import br.ufrpe.aluguelDeCarro.fachada.FachadaGerente;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Aluguel;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Carro;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Categoria;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Cliente;
import br.ufrpe.aluguelDeCarro.servicos.ViewUtil;
import com.jfoenix.controls.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.StringConverter;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * @author Fernando
 */
public class AluguelController implements Initializable {
    @FXML
    private TableView<Aluguel> tableView;

    @FXML
    private TableColumn<Aluguel, String> clienteColumn;

    @FXML
    private TableColumn<Aluguel, String> carroColumn;

    @FXML
    private JFXDatePicker retiradaDatePicker;

    @FXML
    private JFXTimePicker retiradaTimePicker;

    @FXML
    private JFXDatePicker devolucaoEstimadaDatePicker;

    @FXML
    private JFXTimePicker devolucaoEstimadaTimePicker;

    @FXML
    private JFXDatePicker devolucaoRealDatePicker;

    @FXML
    private JFXTimePicker devolucaoRealTimePicker;

    @FXML
    private JFXTextField valorEstimadoTextField;

    @FXML
    private JFXTextField custoAdicionalTextField;

    @FXML
    private JFXComboBox<Cliente> clienteComboBox;

    @FXML
    private JFXComboBox<Categoria> categoriaComboBox;

    @FXML
    private JFXComboBox<Carro> carroComboBox;

    @FXML
    private JFXButton novoButton;

    @FXML
    private JFXButton salvarButton;

    @FXML
    private JFXButton finalizarButton;

    private ObservableList<Aluguel> alugueis;
    private ObservableList<Categoria> categorias;
    private ObservableList<Carro> carros;
    private FachadaGerente fachada = new FachadaGerente();

    @FXML
    void novo(ActionEvent event) {
        limparSelecao();
    }

    private void limparSelecao() {
        mostrarDetalhes(null);
        tableView.getSelectionModel().clearSelection();
    }

    @FXML
    void salvar(ActionEvent event) {
        Aluguel aluguel = tableView.getSelectionModel().getSelectedItem();
        if (aluguel == null) cadastrar();
        else alterar(aluguel);
    }

    private void alterar(Aluguel aluguel) {
        try {
            lerInputs(aluguel);
            fachada.alterarAluguel(aluguel);
            limparSelecao();
            alugueis.set(alugueis.indexOf(aluguel), aluguel);
            ViewUtil.mostrarTooltip(salvarButton, "Aluguel alterado com sucesso");
        } catch (CustoAdicionalNegativoException e) {
            custoAdicionalTextField.requestFocus();
            ViewUtil.mostrarTooltip(custoAdicionalTextField, e.getMessage());
        } catch (DataEstimadaInconsistenteException | DataEstimadaPassado | DataDevolucacaoEstimadaObrigatoriaException e) {
            devolucaoEstimadaTimePicker.requestFocus();
            ViewUtil.mostrarTooltip(devolucaoEstimadaDatePicker, e.getMessage());
        } catch (DataRetiradaInconsistenteException | DataRetiradaFuturoException | DataRetiradaPassadoException | DataRetiradaObrigatoriaException e) {
            retiradaDatePicker.requestFocus();
            ViewUtil.mostrarTooltip(retiradaDatePicker, e.getMessage());
        } catch (AluguelInvalidoException | PessoaInvalidaException | UsuarioInvalidoException e) {
            ViewUtil.mostrarTooltip(salvarButton, e.getMessage());
        } catch (ClienteInvalidoException e) {
            clienteComboBox.requestFocus();
            ViewUtil.mostrarTooltip(clienteComboBox, e.getMessage());
        } catch (CarroInvalidoException e) {
            carroComboBox.requestFocus();
            ViewUtil.mostrarTooltip(carroComboBox, e.getMessage());
        } catch (CategoriaInvalidaException e) {
            categoriaComboBox.requestFocus();
            ViewUtil.mostrarTooltip(categoriaComboBox, e.getMessage());
        }
    }

    private void cadastrar() {
        try {
            Aluguel aluguel = new Aluguel();
            lerInputs(aluguel);
            fachada.cadastrarAluguel(aluguel);
            limparSelecao();
            alugueis.add(aluguel);
            ViewUtil.mostrarTooltip(salvarButton, "Aluguel salvo com sucesso");
        } catch (CustoAdicionalNegativoException e) {
            custoAdicionalTextField.requestFocus();
            ViewUtil.mostrarTooltip(custoAdicionalTextField, e.getMessage());
        } catch (DataEstimadaInconsistenteException | DataEstimadaPassado | DataDevolucacaoEstimadaObrigatoriaException e) {
            devolucaoEstimadaTimePicker.requestFocus();
            ViewUtil.mostrarTooltip(devolucaoEstimadaDatePicker, e.getMessage());
        } catch (DataRetiradaInconsistenteException | DataRetiradaFuturoException | DataRetiradaPassadoException | DataRetiradaObrigatoriaException e) {
            retiradaDatePicker.requestFocus();
            ViewUtil.mostrarTooltip(retiradaDatePicker, e.getMessage());
        } catch (CarroInvalidoException e) {
            carroComboBox.requestFocus();
            ViewUtil.mostrarTooltip(carroComboBox, e.getMessage());
        } catch (CategoriaInvalidaException e) {
            categoriaComboBox.requestFocus();
            ViewUtil.mostrarTooltip(categoriaComboBox, e.getMessage());
        } catch (AluguelInvalidoException | UsuarioInvalidoException | PessoaInvalidaException e) {
            ViewUtil.mostrarTooltip(salvarButton, e.getMessage());
        } catch (ClienteInvalidoException e) {
            clienteComboBox.requestFocus();
            ViewUtil.mostrarTooltip(clienteComboBox, e.getMessage());
        }
    }

    @FXML
    public void finalizar(ActionEvent event) {
        Aluguel aluguel = tableView.getSelectionModel().getSelectedItem();
        if (aluguel != null) {
            try {
                if (custoAdicionalTextField.getText() != null && !custoAdicionalTextField.getText().isEmpty()) {
                    aluguel.setCustoAdicional(new BigDecimal(custoAdicionalTextField.getText()));
                    fachada.finalizarAluguel(aluguel);
                    ViewUtil.mostrarTooltip(finalizarButton, "Aluguel finalizado com sucesso");
                    limparSelecao();
                } else {
                    custoAdicionalTextField.requestFocus();
                    ViewUtil.mostrarTooltip(custoAdicionalTextField, "Preencha o custo adicional (0 se não houver)");
                }
            } catch (PessoaInvalidaException | AluguelInvalidoException | CarroInvalidoException | IdNaoEncontradoException | UsuarioInvalidoException | CategoriaInvalidaException | ClienteInvalidoException e) {
                ViewUtil.mostrarTooltip(finalizarButton, e.getMessage());
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTabela();
        configurarComboBox();
        configurarOnActionDatas();
    }

    private void configurarOnActionDatas() {
        retiradaDatePicker.setOnAction(event -> limparComboBoxes());
        retiradaTimePicker.setOnAction(event -> limparComboBoxes());
        devolucaoRealDatePicker.setOnAction(event -> limparComboBoxes());
        devolucaoRealTimePicker.setOnAction(event -> limparComboBoxes());
        devolucaoEstimadaDatePicker.setOnAction(event -> limparComboBoxes());
        devolucaoEstimadaTimePicker.setOnAction(event -> limparComboBoxes());
    }

    private void limparComboBoxes() {
        categorias.clear();
        carros.clear();
    }

    private void configurarComboBox() {
        configurarConverter();
        clienteComboBox.getItems().addAll(fachada.consultarClientes());
        categorias = FXCollections.observableArrayList();
        categoriaComboBox.setItems(categorias);
        categoriaComboBox.setOnMouseClicked(event -> carregarCategorias());
        carros = FXCollections.observableArrayList();
        carroComboBox.setItems(carros);
        carroComboBox.setOnMouseClicked(event -> carregarCarros());
    }

    private void configurarConverter() {
        clienteComboBox.setConverter(new StringConverter<Cliente>() {
            @Override
            public String toString(Cliente cliente) {
                return cliente != null ? cliente.getCpf() : "";
            }

            @Override
            public Cliente fromString(String cpf) {
                try {
                    return fachada.consultarCliente(cpf);
                } catch (ClienteNaoEncontradoException e) {
                    return null;
                }
            }
        });
        categoriaComboBox.setConverter(new StringConverter<Categoria>() {
            @Override
            public String toString(Categoria categoria) {
                return categoria != null ? categoria.getNome() : "";
            }

            @Override
            public Categoria fromString(String nome) {
                try {
                    return fachada.consultarCategoria(nome);
                } catch (CategoriaNaoEncontradaException e) {
                    return null;
                }
            }
        });
        carroComboBox.setConverter(new StringConverter<Carro>() {
            @Override
            public String toString(Carro carro) {
                return carro != null ? carro.getPlaca() : "";
            }

            @Override
            public Carro fromString(String placa) {
                try {
                    return fachada.consultarCarro(placa);
                } catch (CarroNaoEncontradoException e) {
                    return null;
                }
            }
        });
    }

    private void carregarCarros() {
        if (!categoriaComboBox.getSelectionModel().isEmpty()) {
            carros.clear();
            carros.addAll(fachada.consultarCarros(categoriaComboBox.getValue()));
        } else {
            carroComboBox.hide();
            categoriaComboBox.requestFocus();
            ViewUtil.mostrarTooltip(carroComboBox, "Selecione uma categoria");
        }
    }

    private void carregarCategorias() {
        Aluguel aluguel = new Aluguel();
        lerInputs(aluguel);
        if (aluguel.getRetirada() != null) {
            if (aluguel.getDevolucaoEstimada() != null) {
                categorias.clear();
                categorias.addAll(fachada.consultarCategoriasDisponiveisParaAluguel(aluguel));
            } else {
                categoriaComboBox.hide();
                devolucaoEstimadaDatePicker.requestFocus();
                ViewUtil.mostrarTooltip(categoriaComboBox, "Preencha a data de devolução");
            }
        } else {
            categoriaComboBox.hide();
            retiradaDatePicker.requestFocus();
            ViewUtil.mostrarTooltip(categoriaComboBox, "Preencha a data de retirada");
        }
    }

    private void configurarTabela() {
        alugueis = FXCollections.observableArrayList();
        alugueis.addAll(fachada.consultarAlugueis());
        tableView.setItems(alugueis);
        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDetalhes(newValue));
        clienteColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getCliente().getCpf()));
        carroColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getCarro().getPlaca()));
    }

    private void lerInputs(Aluguel aluguel) {
        if (!custoAdicionalTextField.getText().isEmpty()) {
            aluguel.setCustoAdicional(new BigDecimal(custoAdicionalTextField.getText()));
        } else {
            aluguel.setCustoAdicional(BigDecimal.ZERO);
        }
        if (retiradaDatePicker.getValue() != null && retiradaTimePicker.getValue() != null) {
            aluguel.setRetirada(LocalDateTime.of(retiradaDatePicker.getValue(), retiradaTimePicker.getValue()));
        } else {
            aluguel.setRetirada(null);
        }
        if (devolucaoEstimadaDatePicker.getValue() != null && devolucaoEstimadaTimePicker.getValue() != null) {
            aluguel.setDevolucaoEstimada(LocalDateTime.of(devolucaoEstimadaDatePicker.getValue(), devolucaoEstimadaTimePicker.getValue()));
        } else {
            aluguel.setDevolucaoEstimada(null);
        }
        if (devolucaoRealDatePicker.getValue() != null && devolucaoRealTimePicker.getValue() != null) {
            aluguel.setDevolucaoReal(LocalDateTime.of(devolucaoRealDatePicker.getValue(), devolucaoRealTimePicker.getValue()));
        } else {
            aluguel.setDevolucaoReal(null);
        }
        aluguel.setCliente(clienteComboBox.getValue());
        aluguel.setCategoria(categoriaComboBox.getValue());
        aluguel.setCarro(carroComboBox.getValue());
    }

    private void mostrarDetalhes(Aluguel aluguel) {
        if (aluguel != null) {
            retiradaDatePicker.setValue(aluguel.getRetirada().toLocalDate());
            retiradaTimePicker.setValue(aluguel.getRetirada().toLocalTime());
            devolucaoEstimadaDatePicker.setValue(aluguel.getDevolucaoEstimada().toLocalDate());
            devolucaoEstimadaTimePicker.setValue(aluguel.getDevolucaoEstimada().toLocalTime());
            if (aluguel.getDevolucaoReal() != null) {
                devolucaoRealDatePicker.setValue(aluguel.getDevolucaoReal().toLocalDate());
                devolucaoRealTimePicker.setValue(aluguel.getDevolucaoReal().toLocalTime());
            }
            aluguel.calcularValorEstimado();
            valorEstimadoTextField.setText(String.valueOf(aluguel.getValorEstimado()));
            if (aluguel.getCustoAdicional() != null)
                custoAdicionalTextField.setText(String.valueOf(aluguel.getCustoAdicional()));
            carregarCategorias();
            categoriaComboBox.setValue(aluguel.getCategoria());
            if (aluguel.getCarro() != null) {
                carros.add(aluguel.getCarro());
                carroComboBox.setValue(aluguel.getCarro());
            }
            clienteComboBox.setValue(aluguel.getCliente());
        } else {
            retiradaDatePicker.setValue(null);
            retiradaTimePicker.setValue(null);
            devolucaoEstimadaDatePicker.setValue(null);
            devolucaoEstimadaTimePicker.setValue(null);
            devolucaoRealDatePicker.setValue(null);
            devolucaoRealTimePicker.setValue(null);
            valorEstimadoTextField.clear();
            custoAdicionalTextField.clear();
            categoriaComboBox.getSelectionModel().clearSelection();
            carroComboBox.getSelectionModel().clearSelection();
            clienteComboBox.getSelectionModel().clearSelection();
        }
    }

    public void setAluguel(Aluguel aluguel) {
        mostrarDetalhes(aluguel);
    }
}
