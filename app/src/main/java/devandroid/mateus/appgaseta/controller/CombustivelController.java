package devandroid.mateus.appgaseta.controller;

import android.content.SharedPreferences;

import devandroid.mateus.appgaseta.model.Combustivel;
import devandroid.mateus.appgaseta.view.GasEtaActivity;

public class CombustivelController {

    SharedPreferences preferences;

    SharedPreferences.Editor dadosPreference;

    public static final String  NOME_PREFERENCES = "pref_gaseta";

    public CombustivelController(GasEtaActivity activity) {
        preferences = activity.getSharedPreferences(NOME_PREFERENCES, 0);

        dadosPreference = preferences.edit();
    }

    public void salvar(Combustivel combustivel){
        dadosPreference.putString("nomeDoCombustivel", combustivel.getNomeDoCombustivel());
        dadosPreference.putFloat("precoDoCombustivel", (float) combustivel.getPrecoDoCombustivel());
        dadosPreference.putString("resultado", combustivel.getResultado());
        dadosPreference.apply();
    }

    public void limpar(){
        dadosPreference.clear();
        dadosPreference.apply();
    }
}
