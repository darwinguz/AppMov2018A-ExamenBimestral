package cynitech.wrad.examenbimestral

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_crear_comida.*

class CrearComidaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_comida)

        btn_guardar_comida.setOnClickListener(View.OnClickListener {
            irListarComida()
        })

        btn_cancelar_comida.setOnClickListener(View.OnClickListener {
            irMenuPrincipal()
        })
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
