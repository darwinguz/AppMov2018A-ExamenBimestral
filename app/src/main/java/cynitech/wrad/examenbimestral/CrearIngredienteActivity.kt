package cynitech.wrad.examenbimestral

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import cynitech.wrad.examenbimestral.modelos.ModComida
import cynitech.wrad.examenbimestral.modelos.ModIngrediente
import cynitech.wrad.examenbimestral.persistencia.servicios.SerIngrediente
import kotlinx.android.synthetic.main.activity_crear_comida.*
import kotlinx.android.synthetic.main.activity_crear_ingrediente.*

class CrearIngredienteActivity : AppCompatActivity() {

    var comida: ModComida? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_ingrediente)

        comida = intent.getParcelableExtra("comida-intent")

        btn_cancelar_crear_ingrediente.setOnClickListener({
            irIngredientes()
        })

        btn_guardar_crear_ingrediente.setOnClickListener({
            val ingrediente = ModIngrediente(null, txt_nombre_crear_ingrediente.text.toString(), txt_cantidad_crear_ingrediente.text.toString().toInt(), txt_descripcion_crear_ingrediente.text.toString(), chk_opcional_crear_ingrediente.isChecked, txt_tipo_crear_ingrediente.text.toString(), chk_necesita_refrigeracion_crear_ingrediente.isChecked, comida?.id!!)
            val serIngrediente = SerIngrediente(this)
            serIngrediente.insert(ingrediente)
            irIngredientes()
        })
    }

    private fun irIngredientes() {
        val intent = Intent(this, IngredientesActivity::class.java)
        intent.putExtra("comida-intent", comida)
        startActivity(intent)
    }
}
