package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;
import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import model.collection.Colecao;
import model.collection.entities.Pacote;

public class DaoPacote {
	static private PreparedStatement st = null;
	static private ResultSet rs = null;

	public static void salvarPacote(TextField tfNome, TextField tfValor, TextField tfValorMao, TextField tfQuantMao, TextField tfQuantPe, TextField tfValorPe) {
		try {
			st = DB.getConnection().prepareStatement("insert into pacotes"
					+ "(nome, valor, quant_mao, quant_pe, unit_mao, unit_pe) "
					+ "values (?, ?, ?, ?, ?, ?)");
			st.setString(1, tfNome.getText());
			st.setDouble(2, Double.parseDouble(tfValor.getText()));
			st.setInt(3, Integer.parseInt(tfQuantMao.getText()));
			st.setInt(4, Integer.parseInt(tfQuantPe.getText()));
			st.setDouble(5, Double.parseDouble(tfValorMao.getText()));
			st.setDouble(6, Double.parseDouble(tfValorPe.getText()));
			st.executeQuery();
			carregaPacote();
			
		} catch (SQLException e) {
			Alerts.showAlert("ERRO", "Algum problema aconteceu, contate o ADMINISTRADOR", e.getMessage(),
					AlertType.ERROR);
		} finally {
			DB.closeConnection();
			DB.fechaStatement(st);
		}
	}

	public static void carregaPacote() {
		Colecao.pacotes.clear();
		try {
			st = DB.getConnection().prepareStatement("select * from pacotes");
			rs = st.executeQuery();

			while (rs.next()) {
				Pacote pacote = new Pacote(rs.getInt("id"), rs.getString("nome"), rs.getDouble("valor"), rs.getInt("quant_mao"), rs.getInt("quant_pe"), rs.getDouble("unit_mao"), rs.getDouble("unit_pe"));
				Colecao.pacotes.add(pacote);
			}
		} catch (SQLException e) {
			Alerts.showAlert("ERRO", "Algum problema aconteceu, contate o ADMINISTRADOR", e.getMessage(),
					AlertType.ERROR);
		} finally {
			DB.closeConnection();
			DB.fechaResultSet(rs);
			DB.fechaStatement(st);
		}
	}
}
