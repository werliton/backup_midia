package com.example.backupcd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class SobreActivity extends AppCompatActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_sobre);				
		// Inclui a seta de voltar
		android.support.v7.app.ActionBar actionbar = getSupportActionBar();
		actionbar.setDisplayHomeAsUpEnabled(true);		

	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == android.R.id.home ) {
			// Finaliza a tela atual
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
}
