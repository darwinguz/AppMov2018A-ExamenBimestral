package cynitech.wrad.examenbimestral.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cynitech.wrad.examenbimestral.R
import cynitech.wrad.examenbimestral.modelos.ModIngrediente
import kotlinx.android.synthetic.main.lista_fila_ingrediente.view.*
import java.util.*

class AdaIngrediente(private val ingredientes: ArrayList<ModIngrediente>) :
        RecyclerView.Adapter<AdaIngrediente.ViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): AdaIngrediente.ViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context)//root=null para que se ajuste la pantalla
                .inflate(R.layout.lista_fila_ingrediente, null, false)
        // set the view's size, margins, paddings and layout parameters
        //...
        return ViewHolder(textView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.view.lbl_nombre_lista_ingrediente.text = ingredientes[position].nombreIngrediente
        holder.view.lbl_cantidad_lista_ingrediente.text = ingredientes[position].cantidad.toString()
        holder.view.lbl_descripcion_lista_ingrediente.text = ingredientes[position].descripcionPreparacion
        holder.view.lbl_opcional_lista_ingrediente.text = if (ingredientes[position].opcional) R.string.str_opcional.toString() else "No ${R.string.str_opcional}"
        holder.view.lbl_tipo_lista_ingrediente.text = ingredientes[position].tipoIngrediente
        holder.view.lbl_necesita_refrigeracion_lista_ingrediente.text = if (ingredientes[position].necesitaRefrigeracion) R.string.str_necesita_refrigeracion.toString() else "No ${R.string.str_necesita_refrigeracion}"
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = ingredientes.size
}