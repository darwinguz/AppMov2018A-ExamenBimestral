package cynitech.wrad.examenbimestral

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import cynitech.wrad.examenbimestral.adapters.AdaComida
import cynitech.wrad.examenbimestral.persistencia.servicios.SerComida

class ListarComidaActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_comida)

        //recycler view
        viewManager = LinearLayoutManager(this)
        val dbHandler = SerComida(this)
        viewAdapter = AdaComida(dbHandler.selectAll())

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view_comida).apply {
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
