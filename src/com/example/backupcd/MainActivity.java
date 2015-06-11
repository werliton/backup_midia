package com.example.backupcd;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.*;
import android.widget.*;


public class MainActivity extends ActionBarActivity{
	ListView lvExibeTodosCdscadastrados;
	ImageButton imgabrecadastro;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		imgabrecadastro = (ImageButton) findViewById(R.id.imgabrecadastro);
		lvExibeTodosCdscadastrados = (ListView) findViewById(R.id.lvExibeTodosCdscadastrados);
		
		abre_cadastro();		
		abre_detalhe_damidia();
		
	}
	
	private void abre_detalhe_damidia() {
		lvExibeTodosCdscadastrados.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				
				String nome_selecionado = ((TextView) view).getText().toString();
				String anome_selecionado[] = nome_selecionado.split("-");
				int idmidia = Integer.parseInt(anome_selecionado[0]);
				
				Intent it = new Intent(MainActivity.this, DetalheMidiaActivity.class);
				it.putExtra("id", idmidia);
				startActivity(it);				
			}
		
		});
	}

	private void abre_cadastro() {
		imgabrecadastro.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent it = new Intent(MainActivity.this, CadastroActivity.class);
				startActivity(it);
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();

		DBhelper dbh = new DBhelper(this);
		
		List<Midia> listamidia = dbh.listatodasasmidias();
		
		ArrayAdapter<Midia> adp = new ArrayAdapter<Midia>(this, android.R.layout.simple_list_item_1, listamidia);
		lvExibeTodosCdscadastrados.setAdapter(adp);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu); return true;
	}
	
	
}
