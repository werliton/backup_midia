package com.example.backupcd;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper{
	
	private static final String DATABASE_NAME = "backupmidia";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "midia";

	public DBhelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	/**
	 * Cria a tabela no banco de dados
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = " CREATE TABLE "+TABLE_NAME+" (" +
				" id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
				" tipo VARCHAR(3)," +
				" descricao TEXT," +
				" conteudo TEXT" +
				")";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sqlDrop = "DROP TABLE IF EXISTS "+TABLE_NAME;
		db.execSQL(sqlDrop);
		onCreate(db);
	}

	/**
	 * Insere uma midia no banco de dados
	 * @param midia
	 */
	public void inserirMidia(Midia midia) {
		SQLiteDatabase db = getWritableDatabase();
		
		ContentValues midiavalor = new ContentValues();
		midiavalor.put("tipo", midia.getTipo());
		midiavalor.put("descricao", midia.getDescricao());
		midiavalor.put("conteudo", midia.getConteudo());
		
		db.insert(TABLE_NAME, null, midiavalor);
		db.close();
	}
	/**
	 * Retorna todas as midias cadastradas
	 * @return
	 */
	public List<Midia> listatodasasmidias() {
		SQLiteDatabase db = getReadableDatabase();
		
		List<Midia> listamidia = new ArrayList<Midia>();
		
		Cursor c = db.rawQuery(" SELECT * FROM "+TABLE_NAME+" ORDER BY id DESC", null);
		
		// Verifica se tem alguma midia cadastrada
		if (c.moveToFirst()) {
			do {
				Midia m = new Midia();
				m.setId(c.getInt(0));
				m.setTipo(c.getString(1));
				m.setDescricao(c.getString(2));
				m.setConteudo(c.getString(3));
				
				listamidia.add(m);
			} while (c.moveToNext());
		}		
		db.close();
		return listamidia;
	}
	

	/**
	 * 
	 * @param id é o identificador da midia
	 * @return Um objeto Midia especifica
	 */
	public Midia listaUmaMidia(int id) {
		SQLiteDatabase db = getReadableDatabase();
		
		Midia midia = new Midia();
		Cursor c = db.rawQuery(" SELECT * FROM "+TABLE_NAME+" WHERE id="+id, null);
		
		if (c.moveToFirst()) {
			midia.setId(c.getInt(0));
			midia.setTipo(c.getString(1));
			midia.setDescricao(c.getString(2));
			midia.setConteudo(c.getString(3));
		}
		
		return midia;
	}

	public void deletaMidia(int id) {
		SQLiteDatabase db = getReadableDatabase();
		db.delete(TABLE_NAME, "id="+id, null);
	}

	public void atualizaMidia(Midia midia){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("conteudo", midia.getConteudo());
		db.update(TABLE_NAME, cv, "id="+midia.getId(), null);
		db.close();
	}
}
