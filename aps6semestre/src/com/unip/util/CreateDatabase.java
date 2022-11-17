package com.unip.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe possui como função fazer a criação do banco de dados database.db
 * e da tabela 'informacoes', que irá conter as informações necessárias
 * para a operação da aplicação.
 * @author luanz
 */
public class CreateDatabase
{
  public static void main(String[] args)
  {
    Connection connection = null;
    try
    {
      // create a database connection
      connection = DriverManager.getConnection("jdbc:sqlite:database.db");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec.

      statement.executeUpdate("DROP TABLE IF EXISTS Informacoes");
      statement.executeUpdate("CREATE TABLE Informacoes (Info TEXT, Nivel INTEGER)");
      statement.executeUpdate("INSERT INTO Informacoes VALUES ('Documentos sobre a extensão das áreas queimadas da Amazônia', 1)");
      statement.executeUpdate("INSERT INTO Informacoes VALUES ('Relatórios sobre as espécies brasileiras ameaçadas de extinção', 1)");
      statement.executeUpdate("INSERT INTO Informacoes VALUES ('Dados populacionais das tribos indígenas', 1)");
      statement.executeUpdate("INSERT INTO Informacoes VALUES ('Relatórios de receita anual do agronegócio', 2)");
      statement.executeUpdate("INSERT INTO Informacoes VALUES ('Condições climáticas e atividades sísmicas', 2)");
      statement.executeUpdate("INSERT INTO Informacoes VALUES ('Planos governamentais para ampliação da reciclagem de lixo', 3)");
      statement.executeUpdate("INSERT INTO Informacoes VALUES ('Documentos de relações diplomáticas que beneficiam o aproveitamento de \n energia elétrica limpa gerada nacionalmente', 3)");
      statement.executeUpdate("INSERT INTO Informacoes VALUES ('Palestras de reuniões da ONU sobre meio ambiente', 3)");
      statement.executeUpdate("INSERT INTO Informacoes VALUES ('Relatórios do DAE e SABESP', 3)");
      
      ResultSet rs = statement.executeQuery("select * from informacoes");
      while(rs.next())
      {
          System.out.println("Nivel: " + rs.getInt("Nivel") + "; Informacao: " + rs.getString("Info"));
      }
    }
    catch(SQLException e)
    {
      System.err.println(e.getMessage());
    }
    finally
    {
      try
      {
        if(connection != null)
          connection.close();
      }
      catch(SQLException e)
      {
        System.err.println(e.getMessage());
      }
    }
  }
}
