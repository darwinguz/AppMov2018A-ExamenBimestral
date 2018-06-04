package cynitech.wrad.examenbimestral

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import cynitech.wrad.examenbimestral.modelos.ModComida
import cynitech.wrad.examenbimestral.persistencia.servicios.SerComida
import kotlinx.android.synthetic.main.activity_crear_comida.*

class CrearComidaActivity : AppCompatActivity() {
    var comidaEdit: ModComida? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_comida)

        comidaEdit = intent.getParcelableExtra("comida-edit-intent")
        Log.i("info", "COMIDA RECIBIDA POR EDITAR: $comidaEdit ")

        if (comidaEdit != null) {
            txt_nombre_comida.setText(comidaEdit?.nombrePlato)
            txt_descripcion_comida.setText(comidaEdit?.descripcionPlato)
            txt_nacionalidad_comida.setText(comidaEdit?.nacionalidad)
            txt_numero_personas_comida.setText(comidaEdit?.numeroPersonas.toString())
            chk_picante_comida.isChecked = comidaEdit?.picante!!
        }

        btn_guardar_comida.setOnClickListener(View.OnClickListener {
            guardarComida()
            irListarComida()
        })

        btn_cancelar_comida.setOnClickListener(View.OnClickListener {
            irMenuPrincipal()
        })
    }

    private fun guardarComida() {
        val servicioComida = SerComida(this)
        if (comidaEdit == null) {
            servicioComida.insert(ModComida(null, txt_nombre_comida.text.toString(), txt_descripcion_comida.text.toString(), txt_nacionalidad_comida.text.toString(), txt_numero_personas_comida.text.toString().toInt(), chk_picante_comida.isChecked, null))
        } else {
            servicioComida.update(ModComida(comidaEdit!!.id, txt_nombre_comida.text.toString(), txt_descripcion_comida.text.toString(), txt_nacionalidad_comida.text.toString(), txt_numero_personas_comida.text.toString().toInt(), chk_picante_comida.isChecked, null))
        }
    }

    private fun irListarComida() {
        val intent = Intent(this, ListarComidaActivity::class.java)
        startActivity(intent)
    }

    private fun irMenuPrincipal() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


}
