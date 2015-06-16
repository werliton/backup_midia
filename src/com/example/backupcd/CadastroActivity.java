package com.example.backupcd;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class CadastroActivity extends ActionBarActivity {

	RadioButton rbcd, rbdvd;
	Spinner spdescricao;
	EditText edconteudo;
	ImageButton imgsalvar, imgcancel;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_cadastro);

		android.support.v7.app.ActionBar actionbar = getSupportActionBar();
		actionbar.setDisplayHomeAsUpEnabled(true);

		rbcd = (RadioButton) findViewById(R.id.rbcd);
		rbdvd = (RadioButton) findViewById(R.id.rbdvd);

		spdescricao = (Spinner) findViewById(R.id.spdescricao);

		popula_spinner();

		edconteudo = (EditText) findViewById(R.id.edconteudo);

	}

	private void popula_spinner() {
		ArrayList<String> descricoes = new ArrayList<String>();
		descricoes.add("Documentos");
		descricoes.add("Imagens");
		descricoes.add("Misto");
		descricoes.add("Músicas");
		descricoes.add("Programas");

		ArrayAdapter<String> adp = new ArrayAdapter<String>(
				CadastroActivity.this,
				android.R.layout.simple_spinner_dropdown_item, descricoes);
		spdescricao.setAdapter(adp);

	}

	private void cancelar() {
		finish();
	}

	private void salvar_midia() {
		Midia midia = new Midia();
		if (rbcd.isChecked())
			midia.setTipo(rbcd.getText().toString());
		if (rbdvd.isChecked())
			midia.setTipo(rbdvd.getText().toString());

		midia.setDescricao(spdescricao.getSelectedItem().toString());
		midia.setConteudo(edconteudo.getText().toString());

		DBhelper dbh = new DBhelper(CadastroActivity.this);
		dbh.inserirMidia(midia);
		Toast.makeText(CadastroActivity.this, "Mídia salva com sucesso",
				Toast.LENGTH_SHORT).show();
		finish();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_cadastro, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == R.activity_cadastro.mnSalvar) {
			salvar_midia();
		}
		if (item.getItemId() == android.R.id.home) {
			cancelar();
		}

		return super.onOptionsItemSelected(item);
	}

}
