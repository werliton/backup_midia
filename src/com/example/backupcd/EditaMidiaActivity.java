package com.example.backupcd;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditaMidiaActivity extends ActionBarActivity {

	TextView txtnumero, txttipo, txtdescricao;
	EditText edconteudo;
	Button btsalvar, btcancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_editamidia);
		// Habilita o botão de voltar para home
		android.support.v7.app.ActionBar actionbar = getSupportActionBar();
		actionbar.setDisplayHomeAsUpEnabled(true);
		
		lerDados();
	}

	@Override
	protected void onResume() {
		super.onResume();
		int idMidia = getIntent().getIntExtra("id", 0);
		preencheCampos(idMidia);
	}

	private void preencheCampos(int idMidia) {

		DBhelper dbh = new DBhelper(EditaMidiaActivity.this);
		Midia md = dbh.listaMidiaById(idMidia);

		txtnumero.setText(String.valueOf(md.getId()));
		txttipo.setText(md.getTipo());
		txtdescricao.setText(md.getDescricao());
		edconteudo.setText(md.getConteudo());

	}

	private void cancelar() {
		finish();
	}

	private void salvaDados() {

		int id = Integer.parseInt(txtnumero.getText().toString());
		Midia midia = new Midia();
		midia.setId(id);
		midia.setConteudo(edconteudo.getText().toString());

		DBhelper db = new DBhelper(EditaMidiaActivity.this);
		db.atualizaMidia(midia);
		Toast.makeText(EditaMidiaActivity.this, "Midia atualizada",
				Toast.LENGTH_LONG).show();
		finish();

	}

	private void lerDados() {
		txtnumero = (TextView) findViewById(R.id.txtnumero);
		txttipo = (TextView) findViewById(R.id.txttipo);
		txtdescricao = (TextView) findViewById(R.id.txtdescricao);
		edconteudo = (EditText) findViewById(R.id.edconteudo);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_edita, menu); return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == R.activity_edit.mnSalvar) {
			salvaDados();
		}
		if (item.getItemId() == android.R.id.home) {
			cancelar();
		}
		
		return super.onOptionsItemSelected(item);
	}
}
