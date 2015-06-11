package com.example.backupcd;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditaMidiaActivity extends Activity{

	TextView txtnumero, txttipo, txtdescricao;
	EditText edconteudo;
	Button btsalvar, btcancel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_editamidia);
		
		
		lerDados();
		
		
		salvaDados();
		cancelar();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		int idMidia = getIntent().getIntExtra("id", 0);
		
		preencheCampos(idMidia);
	}

	private void preencheCampos(int idMidia) {
		
		DBhelper dbh = new DBhelper(EditaMidiaActivity.this);
		Midia md = dbh.listaUmaMidia(idMidia);
		
		txtnumero.setText(String.valueOf(md.getId()));
		txttipo.setText(md.getTipo());
		txtdescricao.setText(md.getDescricao());
		edconteudo.setText(md.getConteudo());
		
	}

	private void cancelar() {
		btcancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});		
	}

	private void salvaDados() {
		btsalvar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int id = Integer.parseInt(txtnumero.getText().toString());
				Midia midia = new Midia();
				midia.setId(id);
				midia.setConteudo(edconteudo.getText().toString());
				
				DBhelper db = new DBhelper(EditaMidiaActivity.this);
				db.atualizaMidia(midia);
				Toast.makeText(EditaMidiaActivity.this, "Midia atualizada", Toast.LENGTH_LONG).show();
				finish();
			}
		});		
		
	}

	private void lerDados() {
		txtnumero = (TextView) findViewById(R.id.txtnumero);
		txttipo = (TextView) findViewById(R.id.txttipo);
		txtdescricao = (TextView) findViewById(R.id.txtdescricao);
		
		edconteudo = (EditText) findViewById(R.id.edconteudo);
		btsalvar = (Button) findViewById(R.id.btsalvar);
		btcancel = (Button) findViewById(R.id.btcancel);		
	}
}
