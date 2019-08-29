package gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entities.Funcionario;
import model.services.Cadastro;
import model.services.Carregar;
import model.services.Excluir;
import model.services.Salvar;

public class ViewFuncionarioController implements Initializable{
	
	ObservableList<Funcionario> obFuncionario;
	Scene main;
	
	@FXML
	private Button btRecarregar;
	
	@FXML
	private Button btAtualizar;
	
	@FXML
	private Button btCriaFuncionario;
	
	@FXML
	private Button btVoltar;
	
	@FXML
	private Button btExcluiFuncionario;
	
	@FXML
	private TextField txtIdFuncionario;
	
	@FXML
	private TextField txtNomeFuncionario;
	
	@FXML
	private TextField txtSalarioFuncionario;
	
	@FXML
	private TableView<Funcionario> tvFuncionario = new TableView<>();
	
	@FXML
	private TableColumn<Funcionario, String> colunaNome;
	
	@FXML
    private TableColumn<Funcionario, Integer> colunaId;
	
	@FXML
    private TableColumn<Funcionario, Double> colunaSalario;
	
	@FXML
    private TableColumn<Funcionario, CheckBox> colunaSelect;
	
	@FXML
	public void onBtVoltarAction() {
		try {
			Parent fxmlMain = FXMLLoader.load(getClass().getResource("/gui/view/View.fxml"));
			main = new Scene(fxmlMain);
		}
		catch(IOException e) {
			e.printStackTrace();
	}
	Main.getStage().setScene(main);
	Main.getStage().centerOnScreen();
	}
	
	@FXML
	public void atualizaFuncionario() {
		Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getResource("/gui/view/ViewAtualizaFuncionario.fxml"));
			Scene scene = new Scene(parent);
			Main.getStage().setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	@FXML
	public void onBtDesfazerAction(){
		Carregar.carregaFuncionario();
		carregaFuncionario();
	}
	
	public void carregaFuncionario() {
		obFuncionario = FXCollections.observableArrayList(Cadastro.funcionarios);
        tvFuncionario.setItems(obFuncionario);
        tvFuncionario.refresh();
	}

	@FXML
	public void onBtCriaFuncionarioAction(){
		if(Alerts.showAlertAtualizacao()) {
			try {
				int id = Integer.parseInt(txtIdFuncionario.getText());
				String nome = txtNomeFuncionario.getText();
				Double salario = Double.parseDouble(txtSalarioFuncionario.getText());
				Funcionario fun01 = new Funcionario(nome, id, salario);
				
				Cadastro.verificaFuncionario(fun01);
				Cadastro.funcionarios.add(fun01);
				Salvar.salvarFuncionario(txtIdFuncionario, txtNomeFuncionario, txtSalarioFuncionario);
				
				//vai atualizar no stage main se adicionar funcionários
				obFuncionario = FXCollections.observableArrayList(Cadastro.funcionarios);
	    		ViewController.getTvAgendaTemp().setItems(obFuncionario);
				ViewController.getTvFuncionarioTemp().setItems(obFuncionario);
				ViewController.getStageFuncionario().close();
			}
			catch (NumberFormatException e) {
				Alerts.showAlert("Error", "Parse error", e.getMessage(), AlertType.ERROR);
			}
		}
	}
	
	public void excluirFuncionario() {
		if(Alerts.showAlertExclusao()) {
			ObservableList<Funcionario> obExcluirFuncionario = FXCollections.observableArrayList();
			
			for(Funcionario fun : obFuncionario) {
				if(fun.getSelect().isSelected()) {
					obExcluirFuncionario.add(fun);
					Excluir.excluirFuncionario(fun);
				}
			}
			obFuncionario.removeAll(obExcluirFuncionario);
			Cadastro.funcionarios.removeAll(obExcluirFuncionario);
			
			//vai atulizar no stage main se excluir funcionários
			obFuncionario = FXCollections.observableArrayList(Cadastro.funcionarios);
    		ViewController.getTvAgendaTemp().setItems(obFuncionario);
			ViewController.getTvFuncionarioTemp().setItems(obFuncionario);;
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        colunaSelect.setCellValueFactory(new PropertyValueFactory<>("select"));
		carregaFuncionario();
	}
}
