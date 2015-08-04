package com.example.backupcd;

import java.util.List;
import java.util.Locale;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
	ListView lvExibeTodosCdscadastrados;
	EditText edPesquisaMidia;
	ArrayAdapter<Midia> adp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lvExibeTodosCdscadastrados = (ListView) findViewById(R.id.lvExibeTodosCdscadastrados);
		edPesquisaMidia = (EditText) findViewById(R.id.edPesquisaMidia);

		abre_detalhe_damidia();

		edPesquisaMidia.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String text = edPesquisaMidia.getText().toString()
						.toLowerCase(Locale.getDefault());
				adp.getFilter().filter(text);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}

		});

	}

	private void abre_detalhe_damidia() {
		lvExibeTodosCdscadastrados
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {

						String nome_selecionado = ((TextView) view).getText()
								.toString();
						String anome_selecionado[] = nome_selecionado
								.split("-");
						int idmidia = Integer.parseInt(anome_selecionado[0]);

						Intent it = new Intent(MainActivity.this,
								DetalheMidiaActivity.class);
						it.putExtra("id", idmidia);
						startActivity(it);
					}

				});
	}

	private void abre_cadastro() {
		Intent it = new Intent(MainActivity.this, CadastroActivity.class);
		startActivity(it);
	}

	@Override
	protected void onResume() {
		super.onResume();
		listatodasmidias_cadastradas();
	}

	private void listatodasmidias_cadastradas() {
		DBhelper dbh = new DBhelper(this);
		List<Midia> listamidia = dbh.listatodasasmidias();
		adp = new ArrayAdapter<Midia>(this,
				android.R.layout.simple_list_item_1, listamidia);
		lvExibeTodosCdscadastrados.setAdapter(adp);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		// Pega o componente
		// SearchManager smananger = (SearchManager)
		// getSystemService(Context.SEARCH_SERVICE);

		// SearchView sv = (SearchView)
		// menu.findItem(R.id.search).getActionView();
		// Insere um texto de ajuda
		// sv.setQueryHint("Pesquise");

		// Exemplo da utilização
		// sv.setOnQueryTextListener();

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == R.activity_principal.mnCadastro) {
			abre_cadastro();
		}

		if (item.getItemId() == R.activity_principal.mnSair) {
			AlertDialog.Builder msg = new AlertDialog.Builder(MainActivity.this);
			msg.setTitle("Backup Mídia");
			msg.setMessage("Você tem certeza que deseja sair?");
			msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					finish();
				}
			});

			msg.setNegativeButton("Retornar", null);
			msg.show();
		}

		if (item.getItemId() == R.activity_principal.mnSobre) {
			abre_sobre();
		}

		return super.onOptionsItemSelected(item);
	}

	private void abre_sobre() {
		Intent it = new Intent(MainActivity.this, SobreActivity.class);
		startActivity(it);
	}

}
