package devandroid.mateus.appgaseta.view;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import devandroid.mateus.appgaseta.R;
import devandroid.mateus.appgaseta.controller.CombustivelController;
import devandroid.mateus.appgaseta.model.Combustivel;
import devandroid.mateus.appgaseta.util.UtilGasEta;

public class GasEtaActivity extends AppCompatActivity {

    CombustivelController controller;
    Combustivel combustivelGasolina;
    Combustivel combustivelEtanol;

    EditText editGasolina;
    EditText editEtanol;
    TextView txtResultado;
    Button btnCalcular;
    Button btnLimpar;
    Button btnSalvar;
    Button btnFinalizar;

    double precoGasolina;
    double precoEtanol;
    String resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gaseta);

        controller = new CombustivelController(GasEtaActivity.this);

        editGasolina = findViewById(R.id.editGasolina);
        editEtanol = findViewById(R.id.editEtanol);

        txtResultado = findViewById(R.id.txtResultado);

        btnCalcular = findViewById(R.id.btnCalcular);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isDadosOk = true;

                if(TextUtils.isEmpty(editGasolina.getText())){
                    editGasolina.setError("* Obrigatório");
                    editGasolina.requestFocus();
                    isDadosOk = false;
                }

                if(TextUtils.isEmpty(editEtanol.getText())){
                    editEtanol.setError("* Obrigatório");
                    editEtanol.requestFocus();
                    isDadosOk = false;
                }

                if(isDadosOk) {

                    precoGasolina = Double.parseDouble(editGasolina.getText().toString());
                    precoEtanol = Double.parseDouble(editEtanol.getText().toString());

                    resultado = UtilGasEta.calcularMelhorOpcao(precoGasolina, precoEtanol);

                    txtResultado.setText(resultado);

                    btnSalvar.setEnabled(true);
                    btnSalvar.setBackgroundColor(Color.parseColor("#000000"));


                } else {
                    Toast.makeText(GasEtaActivity.this,
                            "Por favor, digite os dados obrigatórios!",
                            Toast.LENGTH_LONG).show();

                    btnSalvar.setEnabled(false);
                }
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editEtanol.setText("");
                editGasolina.setText("");

                btnSalvar.setEnabled(false);
                btnSalvar.setBackgroundColor(Color.parseColor("#848484"));

                controller.limpar();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                combustivelGasolina = new Combustivel();
                combustivelEtanol = new Combustivel();

                combustivelGasolina.setNomeDoCombustivel("Gasolina");
                combustivelGasolina.setPrecoDoCombustivel(precoGasolina);
                combustivelEtanol.setNomeDoCombustivel("Etanol");
                combustivelEtanol.setPrecoDoCombustivel(precoEtanol);

                combustivelGasolina.setResultado(UtilGasEta.calcularMelhorOpcao(precoGasolina,
                        precoEtanol));
                combustivelEtanol.setResultado(UtilGasEta.calcularMelhorOpcao(precoGasolina,
                        precoEtanol));

                controller.salvar(combustivelGasolina);
                controller.salvar(combustivelEtanol);

                Toast.makeText(GasEtaActivity.this, "Salvo!", Toast.LENGTH_LONG).show();

            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GasEtaActivity.this, "Volte sempre", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}