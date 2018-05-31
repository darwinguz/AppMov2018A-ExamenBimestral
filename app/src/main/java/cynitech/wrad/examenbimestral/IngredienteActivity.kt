package cynitech.wrad.examenbimestral

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import cynitech.wrad.examenbimestral.modelos.ModComida

class IngredienteActivity : AppCompatActivity() {

    var comida: ModComida? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingrediente)

        comida = intent.getParcelableExtra("comida-intent")
        Log.i("ingredientes", "COMIDA RECIBIDA: $comida ")


    }
}
