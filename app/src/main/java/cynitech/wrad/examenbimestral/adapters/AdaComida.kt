package cynitech.wrad.examenbimestral.adapters

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cynitech.wrad.examenbimestral.CrearComidaActivity
import cynitech.wrad.examenbimestral.IngredientesActivity
import cynitech.wrad.examenbimestral.ListarComidaActivity
import cynitech.wrad.examenbimestral.R
import cynitech.wrad.examenbimestral.modelos.ModComida
import cynitech.wrad.examenbimestral.persistencia.servicios.SerComida
import kotlinx.android.synthetic.main.lista_fila_comida.view.*
import java.util.*

class AdaComida(private val comidas: ArrayList<ModComida>) :
        RecyclerView.Adapter<AdaComida.ViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {
        init {
            view.lbl_nombre_lista_comida.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
            //menu!!.setHeaderTitle("Select The Action");
            val editar = menu!!.add(R.string.str_editar)
            val eliminar = menu.add(R.string.str_eliminar)
            val compartirCorreo = menu.add(R.string.str_compartir_correo)

            //FIXME ARREGLAR EL LLAMADO DEL OBJETO SELECCIONADO EN EL CONTEXT VIEW Y EL REFRESH
            editar.setOnMenuItemClickListener {
                val serComida = SerComida(view.context)
                val comidaEditar = serComida.selectByName(view.lbl_nombre_lista_comida.text.toString())
                if (comidaEditar != null) {
                    irCrearComida(view.context, comidaEditar)
                }
                true
            }

            //FIXME ARREGLAR EL LLAMADO DEL OBJETO SELECCIONADO EN EL CONTEXT VIEW Y EL REFRESH
            eliminar.setOnMenuItemClickListener {
                val builder = AlertDialog.Builder(view.context)
                builder.setMessage(R.string.str_desea_eliminar_comida)
                        .setPositiveButton(R.string.str_confirmar, { _, _ ->
                            val serComida = SerComida(view.context)
                            val comidaEliminar = serComida.selectByName(view.lbl_nombre_lista_comida.text.toString())
                            if (comidaEliminar != null) {
                                serComida.delete(comidaEliminar.id!!)
                            }
                            irListarComida()
                        })
                        .setNegativeButton(R.string.str_cancelar, null)
                val dialogo = builder.create()
                dialogo.show()
                true
            }

            compartirCorreo.setOnMenuItemClickListener {
                val serComida = SerComida(view.context)
                val comidaEnviar = serComida.selectByName(view.lbl_nombre_lista_comida.text.toString())
                if (comidaEnviar != null) {
                    enviarCorreo(comidaEnviar)
                }
                true
            }
        }

        private fun enviarCorreo(comida: ModComida) {
            val addressees = arrayOf("direccion@uno.com", "direccion@dos.com")
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/html"
            intent.putExtra(Intent.EXTRA_EMAIL, addressees)
            intent.putExtra(Intent.EXTRA_SUBJECT, "Comida - Examen Bimestral")
            intent.putExtra(Intent.EXTRA_TEXT, "Comida:\nNombre=${comida.nombrePlato}\nDescripcion=${comida.descripcionPlato}\nNacionalidad=${comida.nacionalidad}\nNumeroDePersonas=${comida.numeroPersonas}\nPicante=${comida.picante}")
            startActivity(view.context, intent, null)
        }


        private fun irListarComida() {
            val intent = Intent(view.context, ListarComidaActivity::class.java)
            startActivity(view.context, intent, null)
        }

        private fun irCrearComida(context: Context, comidaSelected: ModComida) {
            val intent = Intent(context, CrearComidaActivity::class.java)
            intent.putExtra("comida-edit-intent", comidaSelected)
            Log.e("info", "COMIDA POR EDITAR ENVIADA: $comidaSelected ")
            startActivity(context, intent, null)
        }
    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): AdaComida.ViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)//root=null para que se ajuste la pantalla
                .inflate(R.layout.lista_fila_comida, null, false)
        // set the view's size, margins, paddings and layout parameters
        //...
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.view.lbl_nombre_lista_comida.text = comidas[position].nombrePlato
        holder.view.lbl_descripcion_lista_comida.text = comidas[position].descripcionPlato
        holder.view.lbl_nacionalidad_lista_comida.text = comidas[position].nacionalidad
        holder.view.btn_ingredientes_lista_comida.setOnClickListener({ v ->
            irAActividadIngredientesComida(v.context, comidas[position])
        })

    }

    private fun irAActividadIngredientesComida(context: Context, comidaSelected: ModComida) {
        val intent = Intent(context, IngredientesActivity::class.java)
        intent.putExtra("comida-intent", comidaSelected)
        Log.e("info", "COMIDA ENVIADA: $comidaSelected ")

        startActivity(context, intent, null)
    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = comidas.size
}