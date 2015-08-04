package com.example.backupcd;


import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditaMidiaActivity extends AppCompatActivity {

	TextView txtnumero, txtdescricao;
	EditText edconteudo;
	Button btsalvar, btcancel;
	RadioButton rbcd, rbdvd;
	Spinner spdescricao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_editamidia);
		// Habilita o botão de voltar para home
		android.support.v7.app.ActionBar actionbar = getSupportActionBar();
		actionbar.setDisplayHomeAsUpEnabled(true);
		
		lerDados();
	}
	
	private void lerDados() {
		txtnumero    = (TextView) findViewById(R.id.txtnumero);
		txtdescricao = (TextView) findViewById(R.id.txtdescricao);
		edconteudo   = (EditText) findViewById(R.id.edconteudo);
		
		rbcd = (RadioButton) findViewById(R.id.rbcd);
		rbdvd = (RadioButton) findViewById(R.id.rbdvd);

		spdescricao = (Spinner) findViewById(R.id.spdescricao);
		popula_spinner();
	}
	
	private void popula_spinner() {
		ArrayList<String> descricoes = new ArrayList<String>();
		descricoes.add("Alterar a descrição");
		descricoes.add("Documentos");
		descricoes.add("Imagens");
		descricoes.add("Misto");
		descricoes.add("Músicas");
		descricoes.add("Programas");

		ArrayAdapter<String> adp = new ArrayAdapter<String>(
				EditaMidiaActivity.this,
				android.R.layout.simple_spinner_dropdown_item, descricoes);
		spdescricao.setAdapter(adp);

	}

	@Override
	protected void onResume() {
		super.onResume();
		int idMidia = getIntent().getIntExtra("id", 0);
		preencheCampos(idMidia);
	}

	private void preencheCampos(int idMidia) {
		String tipo;
		
		DBhelper dbh = new DBhelper(EditaMidiaActivity.this);
		Midia md = dbh.listaMidiaById(idMidia);

		txtnumero.setText(String.valueOf(md.getId()));		
		
		tipo = md.getTipo();
		if (tipo.contentEquals("DVD")) {
			rbdvd.setChecked(true);			
		}
		if (tipo.contentEquals("CD")) {
			rbcd.setChecked(true);			
		}

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
		if (rbcd.isChecked())
			midia.setTipo(rbcd.getText().toString());
		if (rbdvd.isChecked())
			midia.setTipo(rbdvd.getText().toString());

		midia.setDescricao(spdescricao.getSelectedItem().toString());
		
		DBhelper db = new DBhelper(EditaMidiaActivity.this);
		if(db.atualizaMidia(midia)>0){
			Toast.makeText(EditaMidiaActivity.this, "Midia atualizada",
				Toast.LENGTH_LONG).show();
			finish();
		}else{
			Toast.makeText(EditaMidiaActivity.this, "Erro ao atualizar Midia. Tente novamente.",
					Toast.LENGTH_LONG).show();
		}

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
