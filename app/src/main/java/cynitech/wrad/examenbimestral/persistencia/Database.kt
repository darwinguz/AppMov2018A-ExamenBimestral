package cynitech.wrad.examenbimestral.persistencia

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


//parametros base de datos SQlite
class Database(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        val DB_NAME = "db_exam"
        val DB_VERSION = 1
        //tabla comida
        val USR_TABLE_NAME_COMIDA = "comida"
        val COL_ID_COMIDA = "id"
        val COL_NOMBRE_COMIDA = "nombre"
        val COL_DESCRIPCION_COMIDA = "descripcion"
        val COL_NACIONALIDAD_COMIDA = "nacionalidad"
        val COL_NUMERO_PERSONAS_COMIDA = "numero_personas"
        val COL_PICANTE_COMIDA = "picante"
        //tabla ingrediente
        val USR_TABLE_NAME_INGREDIENTE = "ingrediente"
        val COL_ID_INGREDIENTE = "id"
        val COL_NOMBRE_INGREDIENTE = "nombre"
        val COL_CANTIDAD_INGREDIENTE = "cantidad"
        val COL_DESCRIPCION_PREPARACION = "descripcion"
        val COL_OPCIONAL = "opcional"
        val COL_TIPO_INGREDIENTE = "tipo"
        val COL_NECESITA_REFRIGERACION = "necesita_refrigeracion"
        val COL_COMIDA_ID = "fk_id_comida"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableSQL = "CREATE TABLE ${Database.USR_TABLE_NAME_COMIDA} " +
                "(${Database.COL_ID_COMIDA} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${Database.COL_NOMBRE_COMIDA} VARCHAR(50),  " +
                "${Database.COL_DESCRIPCION_COMIDA} VARCHAR(100), " +
                "${Database.COL_NACIONALIDAD_COMIDA} VARCHAR(100), " +
                "${Database.COL_NUMERO_PERSONAS_COMIDA} INTEGER, " +
                "${Database.COL_PICANTE_COMIDA} INTEGER)"

        db?.execSQL(createTableSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + Database.DB_NAME)
    }


}

