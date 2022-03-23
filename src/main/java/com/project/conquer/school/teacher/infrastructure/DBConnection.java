package com.project.conquer.school.teacher.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    String tabelaProfessor = "CREATE TABLE IF NOT EXISTS PROFESSOR(" +
            "                ID int(20) NOT NULL AUTO_INCREMENT, NOME VARCHAR(100) NOT NULL," +
            "                CPF VARCHAR(11) NOT NULL, DATA_NASCIMENTO DATE NOT NULL," +
            "                GENERO VARCHAR(2) NOT NULL, EMAIL VARCHAR(50)," +
            "                IDADE int(3), DATA_CRIACAO TIMESTAMP, DATA_ALTERACAO TIMESTAMP, PRIMARY KEY (ID))";

    private Connection connection;

    public void conectar() {
        try {
            System.out.println("Conectando ao banco de dados...");
            String caminhoDB = "jdbc:mysql://localhost:3306/school?autoReconnect=true&useSSL=false&useTimezone=true&serverTimezone=UTC";
            String username = "root";
            String password = "root";
            connection = DriverManager.getConnection(caminhoDB, username, password);
            System.out.println("Banco conectado...");
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro na conexão...");
            e.printStackTrace();
        }
    }

    public void criaTabelaInicial() {
        conectar();

        try {
            System.out.println("Criando tabela Professor...");
            connection.prepareStatement(tabelaProfessor).execute();
            System.out.println("Tabela criada com sucesso!");
        } catch (SQLException exception) {
            System.out.println("Ocorreu um erro na criação da tabela...");
            exception.printStackTrace();
        } finally {
            fechaConexao();
        }

    }

    public void fechaConexao() {
        try {
            if(connection != null)
                connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
