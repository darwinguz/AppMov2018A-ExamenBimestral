package cynitech.wrad.examenbimestral.persistencia.servicios

import android.content.ContentValues
import android.content.Context
import android.util.Log
import cynitech.wrad.examenbimestral.modelos.ModComida
import cynitech.wrad.examenbimestral.persistencia.Database
import java.util.*

//TODO SINGLETON
class SerComida(context: Context) {
    private val dbHelper: Database = Database(context)

    fun insert(comida: ModComida) {
        var cv = ContentValues()
        val dbWriteable = dbHelper.writableDatabase
        cv.put(Database.COL_NOMBRE_COMIDA, comida.nombrePlato)
        cv.put(Database.COL_DESCRIPCION_COMIDA, comida.descripcionPlato)
        cv.put(Database.COL_NACIONALIDAD_COMIDA, comida.nacionalidad)
        cv.put(Database.COL_NUMERO_PERSONAS_COMIDA, comida.numeroPersonas)
        cv.put(Database.COL_PICANTE_COMIDA, if (comida.picante) 1 else 0)
        val idResultado = dbWriteable.insert(Database.USR_TABLE_NAME_COMIDA, null, cv)
        Log.i("database", "*****INSERT***** ERROR=-1 : EXITO!=1; ID_RESULTADO=$idResultado")
        dbWriteable.close()
    }

    fun update(comida: ModComida): Int {
        var cv = ContentValues()
        val dbWriteable = dbHelper.writableDatabase
        cv.put(Database.COL_NOMBRE_COMIDA, comida.nombrePlato)
        cv.put(Database.COL_DESCRIPCION_COMIDA, comida.descripcionPlato)
        cv.put(Database.COL_NACIONALIDAD_COMIDA, comida.nacionalidad)
        cv.put(Database.COL_NUMERO_PERSONAS_COMIDA, comida.numeroPersonas)
        cv.put(Database.COL_PICANTE_COMIDA, if (comida.picante) 1 else 0)
        val count = dbWriteable!!.update(Database.USR_TABLE_NAME_COMIDA, cv, "${Database.COL_ID_COMIDA}=?", arrayOf(comida.id.toString()))
        Log.i("database", "*****UPDATE***** $comida")
        return count
    }

    fun selectAll(): ArrayList<ModComida> {
        val query = "SELECT * FROM ${Database.USR_TABLE_NAME_COMIDA}"
        val dbReadable = dbHelper.readableDatabase
        val resultado = dbReadable.rawQuery(query, null)
        var datos: ArrayList<ModComida> = ArrayList()

        if (resultado.moveToFirst()) {
            do {
                val id = resultado.getInt(0)
                val nombre = resultado.getString(1)
                val descripcion = resultado.getString(2)
                val nacionalidad = resultado.getString(3)
                val numeroPersonas = resultado.getInt(4)
                val picante = resultado.getInt(5)
                val comida = ModComida(id, nombre, descripcion, nacionalidad, numeroPersonas, picante == 1, null)
                datos.add(comida)
                Log.i("database", "*****SELECT***** Comida: ID=$id Nombre=$nombre Descripcion=$descripcion Nacionalidad=$nacionalidad NumeroDePersonas=$numeroPersonas Picante=$picante")
            } while (resultado.moveToNext())
        }

        resultado.close()
        dbReadable.close()
        return datos
    }

    fun delete(id: Int): Int {
        val dbWriteable = dbHelper.writableDatabase
        val count = dbWriteable!!.delete(Database.USR_TABLE_NAME_COMIDA, "${Database.COL_ID_COMIDA}=?", arrayOf(id.toString()))
        Log.i("database", "*****DELETE***** $count")
        return count
    }

    fun selectById(id: Int): ModComida? {
        val query = "SELECT * FROM ${Database.USR_TABLE_NAME_COMIDA} WHERE ${Database.COL_ID_COMIDA}=$id"
        val dbReadable = dbHelper.readableDatabase
        val resultado = dbReadable.rawQuery(query, null)
        var comida: ModComida? = null

        if (resultado.moveToFirst()) {
            val id = resultado.getInt(0)
            val nombre = resultado.getString(1)
            val descripcion = resultado.getString(2)
            val nacionalidad = resultado.getString(3)
            val numeroPersonas = resultado.getInt(4)
            val picante = resultado.getInt(5)
            comida = ModComida(id, nombre, descripcion, nacionalidad, numeroPersonas, picante == 1, null)
            Log.i("database", "*****SELECTBYID***** Comida: ID=$id Nombre=$nombre Descripcion=$descripcion Nacionalidad=$nacionalidad NumeroDePersonas=$numeroPersonas Picante=$picante")
        }
        resultado.close()
        dbReadable.close()
        return comida
    }

    fun selectByName(name: String): ModComida? {
        val query = "SELECT * FROM ${Database.USR_TABLE_NAME_COMIDA} WHERE ${Database.COL_NOMBRE_COMIDA}='$name'"
        val dbReadable = dbHelper.readableDatabase
        val resultado = dbReadable.rawQuery(query, null)
        var comida: ModComida? = null

        if (resultado.moveToFirst()) {
            val id = resultado.getInt(0)
            val nombre = resultado.getString(1)
            val descripcion = resultado.getString(2)
            val nacionalidad = resultado.getString(3)
            val numeroPersonas = resultado.getInt(4)
            val picante = resultado.getInt(5)
            comida = ModComida(id, nombre, descripcion, nacionalidad, numeroPersonas, picante == 1, null)
            Log.i("database", "*****SELECTBYNAME***** Comida: ID=$id Nombre=$nombre Descripcion=$descripcion Nacionalidad=$nacionalidad NumeroDePersonas=$numeroPersonas Picante=$picante")
        }
        resultado.close()
        dbReadable.close()
        return comida
    }
}