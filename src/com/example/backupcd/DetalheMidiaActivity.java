package com.example.backupcd;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class DetalheMidiaActivity extends Activity{

	Button btvoltar, btdelete, btupdate;
	TextView txtNumMidia, txtTipo,txtDescricao,txtConteudo;
	ImageButton imgvoltar, imgdelete, imgedit;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_detalhamidia);
		
		lerDados();		
		preencheCampos();
		clickVoltar();
		deletaMidia();
		editaMidia();
		
	}

	private void lerDados() {
		
		txtNumMidia = (TextView) findViewById(R.id.txtNumMidia);
		txtTipo = (TextView) findViewById(R.id.txtTipo);
		txtDescricao = (TextView) findViewById(R.id.txtDescricao);
		txtConteudo = (TextView) findViewById(R.id.txtConteudo);
		
		imgvoltar = (ImageButton) findViewById(R.id.imgvoltar);
		imgdelete = (ImageButton) findViewById(R.id.imgdelete);
		imgedit = (ImageButton) findViewById(R.id.imgedit);
	}

	private void editaMidia() {
		imgedit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int idMidia = Integer.parseInt(txtNumMidia.getText().toString());
				
				/*ArrayList<String> midiaSelecionada = new ArrayList<String>();
				midiaSelecionada.add(txtNumMidia.getText().toString());
				midiaSelecionada.add(txtTipo.getText().toString());
				midiaSelecionada.add(txtDescricao.getText().toString());
				midiaSelecionada.add(txtConteudo.getText().toString());
				*/
				Intent it = new Intent(DetalheMidiaActivity.this, EditaMidiaActivity.class);
				it.putExtra("id", idMidia);
				startActivity(it);
			}
		});
		
	}

	private void deletaMidia() {		
		imgdelete.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				int idMidia = Integer.parseInt(txtNumMidia.getText().toString());
				
				DBhelper db = new DBhelper(DetalheMidiaActivity.this);
				db.deletaMidia(idMidia);
				Toast.makeText(DetalheMidiaActivity.this, "Midia deletada com sucesso", Toast.LENGTH_SHORT);
				finish();
			}
		});
		
	}

	private void preencheCampos() {
		int idMidia = getIntent().getIntExtra("id", 0);
		
		DBhelper dbh = new DBhelper(DetalheMidiaActivity.this);
		Midia md = dbh.listaUmaMidia(idMidia);
		
		txtNumMidia.setText(String.valueOf(md.getId()));
		txtTipo.setText(md.getTipo());
		txtDescricao.setText(md.getDescricao());
		txtConteudo.setText(md.getConteudo());
	}

	private void clickVoltar() {
		imgvoltar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
