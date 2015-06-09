package com.example.backupcd;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class CadastroActivity extends Activity implements OnClickListener{

	RadioButton rbcd, rbdvd,rbdocumentos,rbimagens,rbmisto,rbmusicas,rbprogramas;
	Spinner spdescricao;
	EditText edconteudo;
	Button btsalvar, btcancel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_cadastro);
				
		rbcd  = (RadioButton) findViewById(R.id.rbcd);
		rbdvd = (RadioButton) findViewById(R.id.rbdvd);
		/* descricao*/
		rbdocumentos  = (RadioButton) findViewById(R.id.rbdocumentos);
		rbimagens = (RadioButton) findViewById(R.id.rbimagens);
		rbmisto  = (RadioButton) findViewById(R.id.rbmisto);
		rbmusicas = (RadioButton) findViewById(R.id.rbmusicas);
		rbprogramas  = (RadioButton) findViewById(R.id.rbprogramas);
		
		edconteudo = (EditText) findViewById(R.id.edconteudo);
		btsalvar = (Button) findViewById(R.id.btsalvar);
		btcancel = (Button) findViewById(R.id.btcancel);
		
		btsalvar.setOnClickListener(this);
		btcancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	

	/**
	 * Cadastrando uma midia no banco de dados
	 */
	@Override
	public void onClick(View v) {
		Midia midia = new Midia();
		if(rbcd.isChecked())
			midia.setTipo(rbcd.getText().toString());
		
		if(rbdvd.isChecked())
			midia.setTipo(rbdvd.getText().toString());
		
		if(rbdocumentos.isChecked()) midia.setDescricao(rbdocumentos.getText().toString());	
		if(rbimagens.isChecked()) midia.setDescricao(rbimagens.getText().toString());
		if(rbmisto.isChecked()) midia.setDescricao(rbmisto.getText().toString());		
		if(rbmusicas.isChecked()) midia.setDescricao(rbmusicas.getText().toString());
		if(rbprogramas.isChecked()) midia.setDescricao(rbprogramas.getText().toString());
		
		midia.setConteudo(edconteudo.getText().toString());
		
		DBhelper dbh = new DBhelper(this);
		dbh.inserirMidia(midia);
		
		finish();
	}
}
