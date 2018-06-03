package cynitech.wrad.examenbimestral.persistencia.servicios

import android.content.ContentValues
import android.content.Context
import android.util.Log
import cynitech.wrad.examenbimestral.modelos.ModIngrediente
import cynitech.wrad.examenbimestral.persistencia.Database
import java.util.*


class SerIngrediente(context: Context) {
    private val dbHelper: Database = Database(context)

    fun insert(ingrediente: ModIngrediente) {
        var cv = ContentValues()
        val dbWriteable = dbHelper.writableDatabase
        cv.put(Database.COL_NOMBRE_INGREDIENTE, ingrediente.nombreIngrediente)
        cv.put(Database.COL_CANTIDAD_INGREDIENTE, ingrediente.cantidad)
        cv.put(Database.COL_DESCRIPCION_PREPARACION_INGREDIENTE, ingrediente.descripcionPreparacion)
        cv.put(Database.COL_OPCIONAL_INGREDIENTE, if (ingrediente.opcional) 1 else 0)
        cv.put(Database.COL_TIPO_INGREDIENTE, ingrediente.tipoIngrediente)
        cv.put(Database.COL_NECESITA_REFRIGERACION_INGREDIENTE, if (ingrediente.necesitaRefrigeracion) 1 else 0)
        cv.put(Database.COL_FK_ID_COMIDA_INGREDIENTE, ingrediente.comidaId)

        val idResultado = dbWriteable.insert(Database.USR_TABLE_NAME_INGREDIENTE, null, cv)
        Log.i("database", "ERROR=-1 : EXITO!=1; ID_RESULTADO=$idResultado")
        dbWriteable.close()
    }

    fun update(ingrediente: ModIngrediente): Int {
        var cv = ContentValues()
        val dbWriteable = dbHelper.writableDatabase
        cv.put(Database.COL_NOMBRE_INGREDIENTE, ingrediente.nombreIngrediente)
        cv.put(Database.COL_CANTIDAD_INGREDIENTE, ingrediente.cantidad)
        cv.put(Database.COL_DESCRIPCION_PREPARACION_INGREDIENTE, ingrediente.descripcionPreparacion)
        cv.put(Database.COL_OPCIONAL_INGREDIENTE, if (ingrediente.opcional) 1 else 0)
        cv.put(Database.COL_TIPO_INGREDIENTE, ingrediente.tipoIngrediente)
        cv.put(Database.COL_NECESITA_REFRIGERACION_INGREDIENTE, if (ingrediente.necesitaRefrigeracion) 1 else 0)
        cv.put(Database.COL_FK_ID_COMIDA_INGREDIENTE, ingrediente.comidaId)
        val count = dbWriteable!!.update(Database.USR_TABLE_NAME_INGREDIENTE, cv, "${Database.COL_ID_INGREDIENTE}=?", arrayOf(ingrediente.id.toString()))
        return count
    }

    fun selectAll(): ArrayList<ModIngrediente> {
        val query = "SELECT * FROM ${Database.USR_TABLE_NAME_INGREDIENTE}"
        val dbReadable = dbHelper.readableDatabase
        val resultado = dbReadable.rawQuery(query, null)
        var datos: ArrayList<ModIngrediente> = ArrayList()

        if (resultado.moveToFirst()) {
            do {
                val id = resultado.getInt(0)
                val nombre = resultado.getString(1)
                val cantidad = resultado.getInt(2)
                val descripcionPreparacion = resultado.getString(3)
                val opcional = resultado.getInt(4)
                val tipo = resultado.getString(5)
                val necesitaRefrigeracion = resultado.getInt(6)
                val comidaId = resultado.getInt(7)
                val ingrediente = ModIngrediente(id, nombre, cantidad, descripcionPreparacion, opcional == 1, tipo, necesitaRefrigeracion == 1, comidaId)
                datos.add(ingrediente)
                Log.i("database", "Ingrediente: ID=$id Nombre=$nombre Cantidad=$cantidad DescripcionPreparacion=$descripcionPreparacion Opcional=$opcional Tipo=$tipo NecesitaRefrigeracion=$necesitaRefrigeracion ComidaID=$comidaId")
            } while (resultado.moveToNext())
        }

        resultado.close()
        dbReadable.close()
        return datos
    }

    fun delete(id: Int): Int {
        val dbWriteable = dbHelper.writableDatabase
        val count = dbWriteable!!.delete(Database.USR_TABLE_NAME_INGREDIENTE, "${Database.COL_ID_INGREDIENTE}=?", arrayOf(id.toString()))
        return count
    }

    fun selectById(id: Int): ModIngrediente? {
        val query = "SELECT * FROM ${Database.USR_TABLE_NAME_INGREDIENTE} WHERE ${Database.COL_ID_INGREDIENTE}=$id"
        val dbReadable = dbHelper.readableDatabase
        val resultado = dbReadable.rawQuery(query, null)
        var ingrediente: ModIngrediente? = null


        if (resultado.moveToFirst()) {
            val id = resultado.getInt(0)
            val nombre = resultado.getString(1)
            val cantidad = resultado.getInt(2)
            val descripcionPreparacion = resultado.getString(3)
            val opcional = resultado.getInt(4)
            val tipo = resultado.getString(5)
            val necesitaRefrigeracion = resultado.getInt(6)
            val comidaId = resultado.getInt(7)
            ingrediente = ModIngrediente(id, nombre, cantidad, descripcionPreparacion, opcional == 1, tipo, necesitaRefrigeracion == 1, comidaId)
            Log.i("database", "Ingrediente: ID=$id Nombre=$nombre Cantidad=$cantidad DescripcionPreparacion=$descripcionPreparacion Opcional=$opcional Tipo=$tipo NecesitaRefrigeracion=$necesitaRefrigeracion ComidaID=$comidaId")
        }
        resultado.close()
        dbReadable.close()
        return ingrediente
    }

    fun selectAllByIdComida(idFk: Int): ArrayList<ModIngrediente> {
        val query = "SELECT * FROM ${Database.USR_TABLE_NAME_INGREDIENTE} WHERE ${Database.COL_FK_ID_COMIDA_INGREDIENTE}=$idFk"
        val dbReadable = dbHelper.readableDatabase
        val resultado = dbReadable.rawQuery(query, null)
        var datos: ArrayList<ModIngrediente> = ArrayList()

        if (resultado.moveToFirst()) {
            do {
                val id = resultado.getInt(0)
                val nombre = resultado.getString(1)
                val cantidad = resultado.getInt(2)
                val descripcionPreparacion = resultado.getString(3)
                val opcional = resultado.getInt(4)
                val tipo = resultado.getString(5)
                val necesitaRefrigeracion = resultado.getInt(6)
                val comidaId = resultado.getInt(7)
                val ingrediente = ModIngrediente(id, nombre, cantidad, descripcionPreparacion, opcional == 1, tipo, necesitaRefrigeracion == 1, comidaId)
                datos.add(ingrediente)
                Log.i("database", "*****SELECT***** Ingrediente: ID=$id Nombre=$nombre Cantidad=$cantidad DescripcionPreparacion=$descripcionPreparacion Opcional=$opcional Tipo=$tipo NecesitaRefrigeracion=$necesitaRefrigeracion ComidaID=$comidaId")
            } while (resultado.moveToNext())
        }

        resultado.close()
        dbReadable.close()
        return datos
    }
}

