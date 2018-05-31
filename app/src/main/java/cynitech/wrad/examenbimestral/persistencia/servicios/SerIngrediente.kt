package cynitech.wrad.examenbimestral.persistencia.servicios

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import cynitech.wrad.examenbimestral.modelos.ModComida
import cynitech.wrad.examenbimestral.persistencia.Database
import java.util.*


class SerIngrediente(context: Context) {
    private var dbWriteable: SQLiteDatabase
    private var dbReadable: SQLiteDatabase

    init {
        val dbHelper = Database(context)
        dbWriteable = dbHelper.writableDatabase
        dbReadable = dbHelper.readableDatabase
    }

    fun insert(comida: ModComida) {
        var cv = ContentValues()
        cv.put(Database.COL_NOMBRE_COMIDA, comida.nombrePlato)
        cv.put(Database.COL_DESCRIPCION_COMIDA, comida.descripcionPlato)
        cv.put(Database.COL_NACIONALIDAD_COMIDA, comida.nacionalidad)
        cv.put(Database.COL_NUMERO_PERSONAS_COMIDA, comida.numeroPersonas.toInt())
        cv.put(Database.COL_PICANTE_COMIDA, if (comida.picante) 1 else 0)
        val idResultado = dbWriteable.insert(Database.USR_TABLE_NAME_COMIDA, null, cv)
        Log.i("database", "ERROR=-1 : EXITO!=1; ID_RESULTADO=$idResultado")
        dbWriteable.close()
    }

    fun update(comida: ModComida): Int {
        var cv = ContentValues()
        cv.put(Database.COL_NOMBRE_COMIDA, comida.nombrePlato)
        cv.put(Database.COL_DESCRIPCION_COMIDA, comida.descripcionPlato)
        cv.put(Database.COL_NACIONALIDAD_COMIDA, comida.nacionalidad)
        cv.put(Database.COL_NUMERO_PERSONAS_COMIDA, comida.numeroPersonas.toInt())
        cv.put(Database.COL_PICANTE_COMIDA, if (comida.picante) 1 else 0)
        val count = dbWriteable!!.update(Database.USR_TABLE_NAME_COMIDA, cv, "${Database.COL_ID_COMIDA}=?", arrayOf(comida.id.toString()))
        return count
    }

    fun selectAll(): ArrayList<ModComida> {
        val query = "SELECT * FROM ${Database.USR_TABLE_NAME_COMIDA}"
        val resultado = dbReadable.rawQuery(query, null)
        var datos: ArrayList<ModComida> = ArrayList()

        if (resultado.moveToFirst()) {
            do {
                val id = resultado.getString(0).toInt()
                val nombre = resultado.getString(1)
                val descripcion = resultado.getString(2)
                val nacionalidad = resultado.getString(3)
                val numeroPersonas = resultado.getString(4).toInt()
                val picante = resultado.getString(5).toInt()
                val comida = ModComida(id, nombre, descripcion, nacionalidad, numeroPersonas, picante == 1, null)
                datos.add(comida)
                Log.i("database", "Comida: ID=$id Nombre=$nombre Descripcion=$descripcion Nacionalidad=$nacionalidad NumeroDePersonas=$numeroPersonas Picante=$picante")
            } while (resultado.moveToNext())
        }
        resultado.close()
        dbReadable.close()
        return datos
    }

    fun delete(id: Int): Int {
        val count = dbWriteable!!.delete(Database.USR_TABLE_NAME_COMIDA, "${Database.COL_ID_COMIDA}=?", arrayOf(id.toString()))
        return count
    }

    fun selectById(id: Int): ModComida? {
        val query = "SELECT * FROM ${Database.USR_TABLE_NAME_COMIDA} WHERE ${Database.COL_ID_COMIDA}=$id"
        val resultado = dbReadable.rawQuery(query, null)
        var comida: ModComida? = null

        if (resultado.moveToFirst()) {
            val id = resultado.getString(0).toInt()
            val nombre = resultado.getString(1)
            val descripcion = resultado.getString(2)
            val nacionalidad = resultado.getString(3)
            val numeroPersonas = resultado.getString(4).toInt()
            val picante = resultado.getString(5).toInt()
            comida = ModComida(id, nombre, descripcion, nacionalidad, numeroPersonas, picante == 1, null)
            Log.i("database", "Comida: ID=$id Nombre=$nombre Descripcion=$descripcion Nacionalidad=$nacionalidad NumeroDePersonas=$numeroPersonas Picante=$picante")
        }
        resultado.close()
        dbReadable.close()
        return comida
    }
}


