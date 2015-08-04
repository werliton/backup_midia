package com.example.backupcd;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetalheMidiaActivity extends AppCompatActivity {

	Button btvoltar, btdelete, btupdate;
	TextView txtNumMidia, txtTipo, txtDescricao, txtConteudo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_detalhamidia);
		
		android.support.v7.app.ActionBar actionbar = getSupportActionBar();
		actionbar.setDisplayHomeAsUpEnabled(true);		

		lerDados();
	}
	
	@Override
	protected void onResume() {
		preencheCampos();
		super.onResume();
	}

	private void lerDados() {
		txtNumMidia = (TextView) findViewById(R.id.txtNumMidia);
		txtTipo = (TextView) findViewById(R.id.txtTipo);
		txtDescricao = (TextView) findViewById(R.id.txtDescricao);
		txtConteudo = (TextView) findViewById(R.id.txtConteudo);
	}

	private void editaMidia() {

		int idMidia = Integer.parseInt(txtNumMidia.getText().toString());

		Intent it = new Intent(DetalheMidiaActivity.this,
				EditaMidiaActivity.class);
		it.putExtra("id", idMidia);
		startActivity(it);

	}

	private void deletaMidia() {

		int idMidia = Integer.parseInt(txtNumMidia.getText().toString());

		DBhelper db = new DBhelper(DetalheMidiaActivity.this);
		db.deletaMidia(idMidia);
		Toast.makeText(DetalheMidiaActivity.this, "Midia deletada com sucesso",
				Toast.LENGTH_SHORT).show();
		finish();

	}

	private void preencheCampos() {
		int idMidia = getIntent().getIntExtra("id", 0);

		DBhelper dbh = new DBhelper(DetalheMidiaActivity.this);
		Midia md = dbh.listaMidiaById(idMidia);

		txtNumMidia.setText(String.valueOf(md.getId()));
		txtTipo.setText(md.getTipo());
		txtDescricao.setText(md.getDescricao());
		txtConteudo.setText(md.getConteudo());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_detalhe, menu); return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == R.activity_detalhe.mnEditar) {
			editaMidia();
		}
		if (item.getItemId() == R.activity_detalhe.mnExcluir) {
			AlertDialog.Builder alert = new AlertDialog.Builder(DetalheMidiaActivity.this);
			alert.setIcon(android.R.drawable.ic_dialog_alert);
			alert.setTitle("Deletando uma mídia");
			alert.setMessage("Deseja realmente deletar esta mídia?");
			
			// Caso seja escolhida a opção SIM
			alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					deletaMidia();					
				}
			});
			
			// Caso escolha nao
			alert.setNegativeButton("Não", null);
			
			alert.show();
		}
		if (item.getItemId() == android.R.id.home) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

}
