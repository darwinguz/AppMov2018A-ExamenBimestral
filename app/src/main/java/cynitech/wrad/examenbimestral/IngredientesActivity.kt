package cynitech.wrad.examenbimestral

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import cynitech.wrad.examenbimestral.adapters.AdaIngrediente
import cynitech.wrad.examenbimestral.modelos.ModComida
import cynitech.wrad.examenbimestral.persistencia.servicios.SerIngrediente
import kotlinx.android.synthetic.main.activity_ingredientes.*

class IngredientesActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    var comida: ModComida? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredientes)

        comida = intent.getParcelableExtra("comida-intent")
        Log.i("info", "COMIDA RECIBIDA: $comida ")

        if (comida != null) {
            txt_nombre_ingrediente.text = comida?.nombrePlato
            txt_descripcion_ingrediente.text = comida?.descripcionPlato
            txt_nacionalidad_ingrediente.text = comida?.nacionalidad
            txt_numero_personas_ingrediente.text = comida?.numeroPersonas.toString()
            chk_picante_ingrediente.isChecked = comida?.picante!!
        }

        viewManager = LinearLayoutManager(this)
        val dbHandler = SerIngrediente(this)
        viewAdapter = AdaIngrediente(dbHandler.selectAll())

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view_ingrediente).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }

    }
}
