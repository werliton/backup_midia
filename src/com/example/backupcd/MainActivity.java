package com.example.backupcd;

import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

@SuppressWarnings("deprecation")
public class MainActivity extends ActionBarActivity{
	Button btabrecadastro;
	ListView lvExibeTodosCdscadastrados;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btabrecadastro = (Button) findViewById(R.id.btabrecadastro);
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
				
				Intent it = new Intent(MainActivity.this, DetalheActivity.class);
				it.putExtra("id", idmidia);
				startActivity(it);				
			}
		
		});
	}

	private void abre_cadastro() {
		btabrecadastro.setOnClickListener(new View.OnClickListener() {			
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

	
}
