package com.example.backupcd;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

	
	// Salvar a mídia, insere uma nova ou atualiza
	public long salvar(Midia midia){
		long id = midia.getId();
		
		if (id != 0) {
			atualizaMidia(midia);
		}else{
			id = inserirMidia(midia);
		}
		
		return id;
	}
	/**
	 * Insere uma nova midia no banco de dados
	 * @param midia
	 */
	public long inserirMidia(Midia midia) {		
		ContentValues midiavalor = new ContentValues();
		midiavalor.put("tipo", midia.getTipo());
		midiavalor.put("descricao", midia.getDescricao());
		midiavalor.put("conteudo", midia.getConteudo());
		long id = inserir(midiavalor);
		return id;
	}
	//Insere uma nova mídia
	public long inserir(ContentValues valores) {
		SQLiteDatabase db = getWritableDatabase();		
		long id = db.insert(TABLE_NAME, null, valores);
		db.close();
		return id;
	}
	/**
	 *  Atualiza dados da midia no banco
	 *  Parâmetros uma Midia
	 *  @Return retorna o numero de linhas afetadas
	 **/
	public int atualizaMidia(Midia midia){
		ContentValues cv = new ContentValues();
		cv.put("conteudo", midia.getConteudo());
		cv.put("tipo", midia.getTipo());
		cv.put("descricao", midia.getDescricao());
		
		String id = String.valueOf(midia.getId());
		String where = "id"+" =?";
		String[] whereArgs = new String[] {id};
		
		return atualizar(cv,where,whereArgs);
	}
	
	public int atualizar(ContentValues valores, String where, String[] whereArgs){
		SQLiteDatabase db = getWritableDatabase();
		int count = db.update(TABLE_NAME, valores, where, whereArgs);
		db.close();
		
		return count;
	}
	/**
	 * Deleta uma midia no banco de dados
	 * @param id
	 * @return retorna a quantidade de dados deletados
	 */
	public int deletaMidia(int id) {
		SQLiteDatabase db = getReadableDatabase();
		int count = db.delete(TABLE_NAME, "id="+id, null);
		return count;
	}
	
	/**
	 * 
	 * @return retorna uma lista com todas as midias cadastradas
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
	public Midia listaMidiaById(int id) {
		SQLiteDatabase db = getReadableDatabase();
		
		Cursor c = db.rawQuery(" SELECT * FROM "+TABLE_NAME+" WHERE id="+id, null);
		
		if (c.moveToFirst()) {
			Midia midia = new Midia();			
			midia.setId(c.getInt(0));
			midia.setTipo(c.getString(1));
			midia.setDescricao(c.getString(2));
			midia.setConteudo(c.getString(3));
			
			return midia;			
		}
		
		return null;
	}

	/**
	 * Pesquisa mídias pelo seu conteúdo
	 * @param conteudo
	 * @return retorna uma lista de mídias
	 */
	public List<Midia> pesquisaMidiaByInputText(String conteudo) {
		SQLiteDatabase db = getReadableDatabase();
		String sql = " SELECT id, descricao FROM "+TABLE_NAME+" WHERE conteudo LIKE % "+conteudo+" %";
		Cursor c = db.rawQuery(sql, null);
		
		List<Midia> listaMidia = new ArrayList<Midia>();
		
		if(c.moveToFirst()){
			do {
				Midia midia = new Midia();
				midia.setId(c.getInt(0));
				midia.setDescricao(c.getString(1));
				
				listaMidia.add(midia);
			} while (c.moveToNext());
		}
		
		return listaMidia;
	}
}
