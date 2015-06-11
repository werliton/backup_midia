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
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class CadastroActivity extends Activity{

	RadioButton rbcd, rbdvd;
	Spinner spdescricao;
	EditText edconteudo;
	ImageButton imgsalvar, imgcancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_cadastro);

		rbcd = (RadioButton) findViewById(R.id.rbcd);
		rbdvd = (RadioButton) findViewById(R.id.rbdvd);
		
		spdescricao = (Spinner) findViewById(R.id.spdescricao);
		
		popula_spinner();

		edconteudo = (EditText) findViewById(R.id.edconteudo);
		imgsalvar = (ImageButton) findViewById(R.id.imgsalvar);
		imgcancel = (ImageButton) findViewById(R.id.imgcancel);

		salvar_midia();
		cancelar();
		
	}

	private void popula_spinner() {
		ArrayList<String> descricoes = new ArrayList<String>();
		descricoes.add("Documentos");
		descricoes.add("Imagens");
		descricoes.add("Misto");
		descricoes.add("Músicas");
		descricoes.add("Programas");
		
		ArrayAdapter<String> adp = new ArrayAdapter<String>(CadastroActivity.this, 
				android.R.layout.simple_spinner_dropdown_item, 
				descricoes);
		spdescricao.setAdapter(adp);
		
	}

	private void cancelar() {
		imgcancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	private void salvar_midia() {
		imgsalvar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Midia midia = new Midia();
				if (rbcd.isChecked())
					midia.setTipo(rbcd.getText().toString());
				if (rbdvd.isChecked())
					midia.setTipo(rbdvd.getText().toString());				
		
				midia.setDescricao(spdescricao.getSelectedItem().toString());
				midia.setConteudo(edconteudo.getText().toString());

				DBhelper dbh = new DBhelper(CadastroActivity.this);
				dbh.inserirMidia(midia);
				Toast.makeText(CadastroActivity.this, "Mídia salva com sucesso", Toast.LENGTH_SHORT).show();
				finish();

			}
		});
	}
}
