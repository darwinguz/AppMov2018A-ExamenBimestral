package cynitech.wrad.examenbimestral.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cynitech.wrad.examenbimestral.MainActivity
import cynitech.wrad.examenbimestral.R
import cynitech.wrad.examenbimestral.beans.BeaComida
import kotlinx.android.synthetic.main.lista_fila_comida.view.*
import java.util.*

class AdaComida(private val comidas: ArrayList<BeaComida>) :
        RecyclerView.Adapter<AdaComida.ViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class ViewHolder(val textView: View) : RecyclerView.ViewHolder(textView) {
        val nombre = textView.lbl_nombre_lista_comida
        val descripcion = textView.lbl_descripcion_lista_comida
        val nacionalidad = textView.lbl_nacionalidad_lista_comida
        val btnIngredientes = textView.btn_ingredientes_lista_comida
    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): AdaComida.ViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context)//root=null para que se ajuste la pantalla
                .inflate(R.layout.lista_fila_comida, null, false)
        // set the view's size, margins, paddings and layout parameters
        //...
        return ViewHolder(textView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.nombre.text = comidas[position].nombrePlato
        holder.descripcion.text = comidas[position].descripcionPlato
        holder.nacionalidad.text = comidas[position].nacionalidad
        holder.btnIngredientes.setOnClickListener(View.OnClickListener { v ->
            irAActividadIngredientesComida(v.context, comidas[position])
        })
    }

    fun irAActividadIngredientesComida(context: Context, comidaSelected: BeaComida) {
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra("comida-intent", comidaSelected)
        Log.e("VERBOSE", "COMIDA ENVIADA: $comidaSelected ")

        startActivity(context, intent, null)
    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = comidas.size
}