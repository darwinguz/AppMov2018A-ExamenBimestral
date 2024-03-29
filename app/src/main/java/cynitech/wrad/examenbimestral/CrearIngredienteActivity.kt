package cynitech.wrad.examenbimestral

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cynitech.wrad.examenbimestral.modelos.ModComida
import cynitech.wrad.examenbimestral.modelos.ModIngrediente
import cynitech.wrad.examenbimestral.persistencia.servicios.SerComida
import cynitech.wrad.examenbimestral.persistencia.servicios.SerIngrediente
import kotlinx.android.synthetic.main.activity_crear_ingrediente.*

class CrearIngredienteActivity : AppCompatActivity() {

    var comida: ModComida? = null
    var ingredienteEdit: ModIngrediente? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_ingrediente)

        comida = intent.getParcelableExtra("comida-intent")
        ingredienteEdit = intent.getParcelableExtra("ingrediente-edit-intent")

        if (ingredienteEdit != null) {
            txt_nombre_crear_ingrediente.setText(ingredienteEdit?.nombreIngrediente)
            txt_cantidad_crear_ingrediente.setText(ingredienteEdit?.cantidad.toString())
            txt_descripcion_crear_ingrediente.setText(ingredienteEdit?.descripcionPreparacion)
            txt_tipo_crear_ingrediente.setText(ingredienteEdit?.tipoIngrediente)
            chk_opcional_crear_ingrediente.isChecked = ingredienteEdit?.opcional!!
            chk_necesita_refrigeracion_crear_ingrediente.isChecked = ingredienteEdit?.necesitaRefrigeracion!!
        }

        btn_cancelar_crear_ingrediente.setOnClickListener({
            irIngredientes()
        })

        btn_guardar_crear_ingrediente.setOnClickListener({
            val ingrediente = ModIngrediente(null, txt_nombre_crear_ingrediente.text.toString(), txt_cantidad_crear_ingrediente.text.toString().toInt(), txt_descripcion_crear_ingrediente.text.toString(), chk_opcional_crear_ingrediente.isChecked, txt_tipo_crear_ingrediente.text.toString(), chk_necesita_refrigeracion_crear_ingrediente.isChecked, null)
            val serIngrediente = SerIngrediente(this)
            if (ingredienteEdit == null) {
                ingrediente.comidaId = comida?.id!!
                serIngrediente.insert(ingrediente)
            } else {
                ingrediente.id = ingredienteEdit!!.id
                ingrediente.comidaId = ingredienteEdit!!.comidaId
                serIngrediente.update(ingrediente)
            }
            irIngredientes()
        })
    }

    private fun irIngredientes() {
        val intent = Intent(this, IngredientesActivity::class.java)
        if (comida == null) {
            comida = SerComida(this).selectById(ingredienteEdit?.comidaId!!)
        }
        intent.putExtra("comida-intent", comida)
        startActivity(intent)
    }
}
