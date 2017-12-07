/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Joel
 * Une query vers la base de données
 */
public class dbStatement {
    private String table;
	protected Statement statement;
	
    private List<String> columns = new ArrayList<>();
	private List<String> columnValues = new ArrayList<>();
    
    private List<String> whereColumns = new ArrayList<>();
    private List<String> whereValues = new ArrayList<>();
    private List<String> whereType = new ArrayList<>();
    private List<String> whereLink = new ArrayList<>();
    
    public dbStatement(Statement statement){
        this.statement = statement;
    }
    
    public void setTable(String table){
        this.table = table;
    }
    
    public void setColumn(String column){
		columns.add(column);
	}
	
	public void setValue(String column, String value){
		columns.add(column);
		columnValues.add("'"+value+"'");
	}
	
	//Overload pour les valeurs non-string
	public void setValue(String column, int value){
		columns.add(column);
		columnValues.add(String.valueOf(value));
	}
	
	public void setValue(String column, float value){
		columns.add(column);
		columnValues.add(String.valueOf(value));
	}
	
	public void setValue(String column, double value){
		columns.add(column);
		columnValues.add(String.valueOf(value));
	}
    
    public void where(String column, String value, String type, String link){
    	link = link.toUpperCase();
        whereColumns.add(column);
        whereValues.add(value);
        whereType.add(type);
        if (link.equals("AND") || link.equals("OR")){
        	whereLink.add(link);
        } else {
        	whereLink.add("AND");
        }
    }
    
    public void where(String column, String value, String type){
    	this.where(column, value, type, "and");
    }
    
    public void where(String column, String value){
        this.where(column, value, "int", "and");
    }
	
	//Fonction créant la string utilisé pour le WHERE
	private String whereString() throws SQLException{
		String where = "";
		if ((whereColumns.size() != whereValues.size()) || (whereColumns.size() != whereType.size()) || (whereColumns.size() != whereLink.size())){
			throw new SQLException("Valeurs de 'WHERE' remplis inégalement");
		}
        where += " WHERE ";
        for(int i = 0; i < whereColumns.size();i++){
            //Je escape pas les valeurs, trop compliqué
            if ("string".equals(whereType.get(i))){
                where += whereColumns.get(i) + " LIKE '" + whereValues.get(i) + "'";
            } else {
                where += whereColumns.get(i) + " = " + whereValues.get(i);
            }
            if (i < (whereColumns.size()-1)){
                where += " AND ";
            }
        }
		return where;
	}
	
	//Faire un INSERT dans la BD
	protected void insert() throws SQLException{
		if (columns.size() != columnValues.size()){
			throw new SQLException("Valeurs des colonnes remplis inégalement");
		}
		
        String sql = "INSERT INTO " + table + " (" + String.join(", ", columns) +  ") VALUES ("  + String.join(", ", columnValues) + ");";
		System.out.println(sql);
		statement.executeQuery(sql);
        return;
    }
    
	//Faire un SELECT dans la BD
	protected ResultSet select() throws SQLException{
		String sql;
		if (columns.size() > 0){
			sql = "SELECT " + String.join(", ", columns) + " FROM " + table;
		} else {
			sql = "SELECT * FROM " + table;
		}
        if (whereColumns.size() > 0){
			sql += this.whereString();
        }
		sql += ";";
        ResultSet rs = null;
        rs = statement.executeQuery(sql);
        return rs;
    }
	
	//Faire un UPDATE dans la BD
	protected void update() throws SQLException{
		if (columns.size() != columnValues.size()){
			throw new SQLException("Valeurs des colonnes remplis inégalement");
		}
		if (columns.size() == 0){
			throw new SQLException("Aucune colonne à mettre à jour");
		}
		
        String sql = "UPDATE " + table + " SET ";
		for(int i = 0; i < columns.size();i++){
            //Je escape pas les valeurs, trop compliqué
            sql += columns.get(i) + " = `" + columnValues.get(i) + "`";
            if (i < (columns.size()-1)){
                sql += ", ";
			}
        }
		if (whereColumns.size() > 0){
			sql += this.whereString();
        }
		sql += ";";
		statement.executeQuery(sql);
        return;
    }
	
	//Faire un DELETE dans la BD
	protected void delete() throws SQLException{
        String sql = "DELETE FROM " + table;
		if (whereColumns.size() > 0){
			sql += this.whereString();
        }
		sql += ";";
        statement.executeQuery(sql);
        return;
    }
}

