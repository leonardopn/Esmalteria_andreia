package gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.controlsfx.control.textfield.TextFields;

import gui.util.Decoracao;
import gui.util.Notificacoes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.entities.Cliente;
import model.services.Cadastro;
import model.services.Carregar;
import model.services.Salvar;

public class ViewAgendaController implements Initializable{
	
 	@FXML
    private Button btVoltar;

    @FXML
    private DatePicker dpData;

    @FXML
    private TextField txtCliente;

    @FXML
    private Button btAgendar;
    
    @FXML
    private CheckBox cb8;
    
    @FXML
    private CheckBox cb8_3;
    
    @FXML
    private CheckBox cb9;
    
    @FXML
    private CheckBox cb9_3;
    
    @FXML
    private CheckBox cb10;
    
    @FXML
    private CheckBox cb10_3;
    
    @FXML
    private CheckBox cb11;
    
    @FXML
    private CheckBox cb11_3;

    @FXML
    private CheckBox cb12;

    @FXML
    private CheckBox cb12_3;

    @FXML
    private CheckBox cb13;

    @FXML
    private CheckBox cb13_3;

    @FXML
    private CheckBox cb14;

    @FXML
    private CheckBox cb15;

    @FXML
    private CheckBox cb18;

    @FXML
    private CheckBox cb17_3;

    @FXML
    private CheckBox cb14_3;

    @FXML
    private CheckBox cb17;

    @FXML
    private CheckBox cb16_3;

    @FXML
    private CheckBox cb16;

    @FXML
    private CheckBox cb15_3;

    @FXML
    private TextField txtFuncionario;
	    
    
    @FXML
    public void onBtAgendarAction(){
    	if(txtCliente.getText().isEmpty()) {
    		Decoracao.setDecoracao(txtCliente);
			Notificacoes.mostraNotificacao("Campos vazios!", "Preencha o campo nome!");
    	}
    	else {
    		salvaHorario();
    		//vai atualizar no stage main se eu criar um agendamento
    		Carregar.carregaAgendaFuncionario(dpData.getValue());
    		ViewController.getTvAgendaTemp().refresh();
    	}
    }

    public void salvaHorario() {
    	int idCliente = 0;
    	for(Cliente cli : Cadastro.clientes) {
			if(txtCliente.getText().equals(cli.getNome())) {
				idCliente = cli.getId();
				txtCliente.setText("");;
			}
		}
    	if(txtCliente.getText() == "") {
    		JOptionPane.showMessageDialog(null,"Primeiro crie um novo cliente com todos os dados"
					+ " ou então crie um cliente com pelo menos o dado: NOME", "Cliente não encontrado", JOptionPane.ERROR_MESSAGE);
    		ViewController.getStageAgenda().close();
    	}
    	else {
    		if(cb8.isSelected() && !(cb8.isDisable())) {
        		Salvar.salvarAgendamento(txtFuncionario.getText(), idCliente, dpData.getValue(), "08:00:00");       			
        	}
        	if(cb8_3.isSelected() && !(cb8_3.isDisable())) {
        		Salvar.salvarAgendamento(txtFuncionario.getText(), idCliente, dpData.getValue(), "08:30:00");
        	}
        	if(cb9.isSelected() && !(cb9.isDisable())) {
        		Salvar.salvarAgendamento(txtFuncionario.getText(), idCliente, dpData.getValue(), "09:00:00");
        	}
        	if(cb9_3.isSelected() && !(cb9_3.isDisable())) {
        		Salvar.salvarAgendamento(txtFuncionario.getText(), idCliente, dpData.getValue(), "09:30:00");
        	}
        	if(cb10.isSelected() && !(cb10.isDisable())) {
        		Salvar.salvarAgendamento(txtFuncionario.getText(), idCliente, dpData.getValue(), "10:00:00");
        	}
        	if(cb10_3.isSelected() && !(cb10_3.isDisable())) {
        		Salvar.salvarAgendamento(txtFuncionario.getText(), idCliente, dpData.getValue(), "10:30:00");
        	}
        	if(cb11.isSelected() && !(cb11.isDisable())) {
        		Salvar.salvarAgendamento(txtFuncionario.getText(), idCliente, dpData.getValue(), "11:00:00");
        	}
        	if(cb11_3.isSelected() && !(cb11_3.isDisable())) {
        		Salvar.salvarAgendamento(txtFuncionario.getText(), idCliente, dpData.getValue(), "11:30:00");
        	}
        	if(cb12.isSelected() && !(cb12.isDisable())) {
        		Salvar.salvarAgendamento(txtFuncionario.getText(), idCliente, dpData.getValue(), "12:00:00");
        	}
        	if(cb12_3.isSelected() && !(cb12_3.isDisable())) {
        		Salvar.salvarAgendamento(txtFuncionario.getText(), idCliente, dpData.getValue(), "12:30:00");
        	}
        	if(cb13.isSelected() && !(cb13.isDisable())) {
        		Salvar.salvarAgendamento(txtFuncionario.getText(), idCliente, dpData.getValue(), "13:00:00");
        	}
        	if(cb13_3.isSelected() && !(cb13_3.isDisable())) {
        		Salvar.salvarAgendamento(txtFuncionario.getText(), idCliente, dpData.getValue(), "13:30:00");
        	}
        	if(cb14.isSelected() && !(cb14.isDisable())) {
        		Salvar.salvarAgendamento(txtFuncionario.getText(), idCliente, dpData.getValue(), "14:00:00");
        	}
        	if(cb14_3.isSelected() && !(cb14_3.isDisable())) {
        		Salvar.salvarAgendamento(txtFuncionario.getText(), idCliente, dpData.getValue(), "14:30:00");
        	}
        	if(cb15.isSelected() && !(cb15.isDisable())) {
        		Salvar.salvarAgendamento(txtFuncionario.getText(), idCliente, dpData.getValue(), "15:00:00");
        	}
        	if(cb15_3.isSelected() && !(cb15_3.isDisable())) {
        		Salvar.salvarAgendamento(txtFuncionario.getText(), idCliente, dpData.getValue(), "15:30:00");
        	}
        	if(cb16.isSelected() && !(cb16.isDisable())) {
        		Salvar.salvarAgendamento(txtFuncionario.getText(), idCliente, dpData.getValue(), "16:00:00");
        	}
        	if(cb16_3.isSelected() && !(cb16_3.isDisable())) {
        		Salvar.salvarAgendamento(txtFuncionario.getText(), idCliente, dpData.getValue(), "16:30:00");
        	}
        	if(cb17.isSelected() && !(cb17.isDisable())) {
        		Salvar.salvarAgendamento(txtFuncionario.getText(), idCliente, dpData.getValue(), "17:00:00");
        	}
        	if(cb17_3.isSelected() && !(cb17_3.isDisable())) {
        		Salvar.salvarAgendamento(txtFuncionario.getText(), idCliente, dpData.getValue(), "17:30:00");
        	}
        	if(cb18.isSelected() && !(cb18.isDisable())) {
        		Salvar.salvarAgendamento(txtFuncionario.getText(), idCliente, dpData.getValue(), "18:00:00");
        	}
    	}
    	ViewController.getStageAgenda().close();
    }
    
    @FXML
    public void carregaHorarios() {
    	if(ViewController.getFunTemp().getH8() != "Livre") {
    		cb8.setSelected(true);
    		cb8.setDisable(true);
    	}
    	if(ViewController.getFunTemp().getH8_3() != "Livre") {
    		cb8_3.setSelected(true);
    		cb8_3.setDisable(true);
    	}
    	if(ViewController.getFunTemp().getH9() != "Livre") {
    		cb9.setSelected(true);
    		cb9.setDisable(true);
    	}
    	if(ViewController.getFunTemp().getH9_3() != "Livre") {
    		cb9_3.setSelected(true);
    		cb9_3.setDisable(true);
    	}
    	if(ViewController.getFunTemp().getH10() != "Livre") {
    		cb10.setSelected(true);
    		cb10.setDisable(true);
    	}
    	if(ViewController.getFunTemp().getH10_3() != "Livre") {
    		cb10_3.setSelected(true);
    		cb10_3.setDisable(true);
    	}
    	if(ViewController.getFunTemp().getH11() != "Livre") {
    		cb11.setSelected(true);
    		cb11.setDisable(true);
    	}
    	if(ViewController.getFunTemp().getH11_3() != "Livre") {
    		cb11_3.setSelected(true);
    		cb11_3.setDisable(true);
    	}
    	if(ViewController.getFunTemp().getH12() != "Livre") {
			cb12.setSelected(true);
			cb12.setDisable(true);
		}
    	if(ViewController.getFunTemp().getH12_3() != "Livre") {
			cb12_3.setSelected(true);
			cb12_3.setDisable(true);
		}
    	if(ViewController.getFunTemp().getH13() != "Livre") {
			cb13.setSelected(true);
			cb13.setDisable(true);
		}
    	if(ViewController.getFunTemp().getH13_3() != "Livre") {
			cb13_3.setSelected(true);
			cb13_3.setDisable(true);
		}
    	if(ViewController.getFunTemp().getH14() != "Livre") {
			cb14.setSelected(true);
			cb14.setDisable(true);
		}
    	if(ViewController.getFunTemp().getH14_3() != "Livre") {
			cb14_3.setSelected(true);
			cb14_3.setDisable(true);
		}
    	if(ViewController.getFunTemp().getH15() != "Livre") {
			cb15.setSelected(true);
			cb15.setDisable(true);
		}
    	if(ViewController.getFunTemp().getH15_3() != "Livre") {
			cb15_3.setSelected(true);
			cb15_3.setDisable(true);
		}
    	if(ViewController.getFunTemp().getH16() != "Livre") {
			cb16.setSelected(true);
			cb16.setDisable(true);
		}
    	if(ViewController.getFunTemp().getH16_3() != "Livre") {
			cb16_3.setSelected(true);
			cb16_3.setDisable(true);
		}
    	if(ViewController.getFunTemp().getH17() != "Livre") {
			cb17.setSelected(true);
			cb17.setDisable(true);
		}
    	if(ViewController.getFunTemp().getH17_3() != "Livre") {
			cb17_3.setSelected(true);
			cb17_3.setDisable(true);
		}
    	if(ViewController.getFunTemp().getH18() != "Livre") {
			cb18.setSelected(true);
			cb18.setDisable(true);
		}
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		txtFuncionario.setText(ViewController.getFunTemp().getNome());
		txtFuncionario.setEditable(false);
		dpData.setValue(ViewController.getDpDataTemp());
		dpData.setEditable(false);
		dpData.getEditor().setEditable(false);
		carregaHorarios();
		TextFields.bindAutoCompletion(txtCliente, Cadastro.clientes);
	}
}
