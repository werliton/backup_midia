package com.example.backupcd;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetalheActivity extends Activity{

	Button btvoltar;
	TextView txtTipo,txtDescricao,txtConteudo;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_detalhamidia);
		btvoltar = (Button) findViewById(R.id.btvoltar);
		txtTipo = (TextView) findViewById(R.id.txtTipo);
		txtDescricao = (TextView) findViewById(R.id.txtDescricao);
		txtConteudo = (TextView) findViewById(R.id.txtConteudo);
		btvoltar = (Button) findViewById(R.id.btvoltar);

		preenche_campos();
		click_voltar();
	}

	private void preenche_campos() {
		DBhelper dbh = new DBhelper(DetalheActivity.this);
		Midia md = dbh.listaumamidia(getIntent().getIntExtra("id", 0));
		
		txtTipo.setText(md.getTipo());
		txtDescricao.setText(md.getDescricao());
		txtConteudo.setText(md.getConteudo());
	}

	private void click_voltar() {
		btvoltar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
