package cynitech.wrad.examenbimestral

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_crear.setOnClickListener(View.OnClickListener {
            irCrearComida()
        })

        btn_listar.setOnClickListener(View.OnClickListener {
            irListarComida()
        })
    }

    private fun irCrearComida() {
        val intent = Intent(this, CrearComidaActivity::class.java)
        startActivity(intent)
    }

    private fun irListarComida() {
        val intent = Intent(this, ListarComidaActivity::class.java)
        startActivity(intent)
    }
}
