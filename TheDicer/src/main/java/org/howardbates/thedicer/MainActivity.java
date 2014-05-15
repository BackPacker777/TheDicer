package org.howardbates.thedicer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends Activity {

	private EditText e1;
	private EditText e2;
	private TextView t1;
	private TextView seekLabel;
	private SeekBar seekBar;
	private RadioGroup radioDieType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		radioDieType = (RadioGroup)findViewById(R.id.radioGroup);
		seekBar = (SeekBar)findViewById(R.id.seekBar);
		seekLabel = (TextView)findViewById(R.id.qtySeekLabel);
		t1 = (TextView)findViewById(R.id.displayResult);
		e2 = (EditText)findViewById(R.id.dieType);
		e1 = (EditText)findViewById(R.id.dieQty);
		seekLabel.setText(seekBar.getProgress() + "/" + seekBar.getMax());

		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			int progress = 0;

			@Override
			public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
				progress = progressValue;
				seekLabel.setText(progress + "/" + seekBar.getMax());
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// Do something here, if you want to do anything at the start of touching the seekbar
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				seekLabel.setText(progress + "/" + seekBar.getMax());
				e1.setText("" + progress);
			}
		});

		radioDieType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				if (checkedId == R.id.d2) {
					e2.setText("" + 2);
				} else if (checkedId == R.id.d4) {
					e2.setText("" + 4);
				} else if (checkedId == R.id.d6) {
					e2.setText("" + 6);
				} else if (checkedId == R.id.d8) {
					e2.setText("" + 8);
				} else if (checkedId == R.id.d10) {
					e2.setText("" + 10);
				} else if (checkedId == R.id.d12) {
					e2.setText("" + 12);
				} else if (checkedId == R.id.d20) {
					e2.setText("" + 20);
				} else {
					e2.setText("" + 100);
				}
			}
		});
	}

	public void totalButtonClick(View v) {
		boolean total = true;
		processInput(total);
	}

	public void individualButtonClick(View v) {
		boolean total = false;
		processInput(total);
	}

	public void processInput(boolean total) {
		int dieType, dieQty;
		if (e1.getText().toString().matches("")) {
			dieQty = 1;
		} else {
			dieQty = Integer.parseInt(e1.getText().toString());
		}
		if (e1.getText().toString().matches("")) {
			dieType = 0;
		} else {
			dieType = Integer.parseInt(e2.getText().toString());
		}
		ProcessRoll dieRoll;
		dieRoll = new ProcessRoll(dieQty,dieType,total);
		if (total) {
			t1.setText(Integer.toString(dieRoll.getResult()));
		} else {
			int[] rolls = dieRoll.getRolls();
			String rollText = "";
			for (int i = 0; i < rolls.length; i++) {
				if (i == rolls.length - 1) {
					rollText = rollText + Integer.toString(rolls[i]);
				} else {
					rollText = rollText + Integer.toString(rolls[i]) + ", ";
				}
			}
			t1.setText(rollText);
		}
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/

	/*@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}*/
}
