package gui.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import gui.util.Notificacoes;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import model.dao.DaoOperacao;

public class ViewReceitaController implements Initializable {

	private boolean parada;

	@FXML
	private TextField tfDescricao;

	@FXML
	private DatePicker dpData;

	@FXML
	private TextField tfEntrada;

	@FXML
	private Button btEnviar;

	@FXML
	private ChoiceBox<String> cbFormaPagamento;

	@FXML
	private ProgressIndicator piStatus;

	@FXML
	private Label lbStatus;

	@FXML
	public void geraReceita() {
		if(tfDescricao.getText().isEmpty() || tfEntrada.getText().isEmpty() || cbFormaPagamento.getValue() == null) {
			Notificacoes.mostraNotificacao("AVISO", "Preencha todos os campos!");
		}
		else {
			piStatus.setVisible(true);
			lbStatus.setVisible(true);
			Task<Void> tarefa = new Task<Void>() {
				@Override
				protected Void call() throws Exception {
					while (parada == true) {
						Thread.sleep(0);
					}
					piStatus.setVisible(false);
					lbStatus.setVisible(false);
					return null;
				}
			};
			
			Task<Void> taskReceita = new Task<Void>() {
				@Override
				protected Void call() throws Exception {
					parada = true;
					javafx.application.Platform.runLater(() -> {
						Thread t = new Thread(tarefa);
						t.start();
					});
					DaoOperacao.salvaOperacao(tfDescricao.getText(), dpData.getValue(), tfEntrada.getText(), cbFormaPagamento.getValue());
					DaoOperacao.atualizaMontante(tfEntrada.getText());
					parada = false;
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							ViewOperacoesController.getStageReceita().close();
						}
					});
					return null;
				}
			};
			
			javafx.application.Platform.runLater(() -> {
				Thread t = new Thread(taskReceita);
				t.start();
			});
		}
		
	}

	public void carregaFormaPagamento() {
		List<String> listMetPag = Arrays.asList("Dinheiro", "Cartão");
		ObservableList<String>obFormaPagamento = FXCollections.observableArrayList(listMetPag);
		cbFormaPagamento.setItems(obFormaPagamento);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dpData.setValue(LocalDate.now());
		carregaFormaPagamento();
	}
}
