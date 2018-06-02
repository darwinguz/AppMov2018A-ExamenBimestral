package cynitech.wrad.examenbimestral.persistencia

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


//parametros base de datos SQlite
class Database(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        const val DB_NAME = "db_exam_t"
        const val DB_VERSION = 4
        //tabla comida
        const val USR_TABLE_NAME_COMIDA = "comida"
        const val COL_ID_COMIDA = "id"
        const val COL_NOMBRE_COMIDA = "nombre"
        const val COL_DESCRIPCION_COMIDA = "descripcion"
        const val COL_NACIONALIDAD_COMIDA = "nacionalidad"
        const val COL_NUMERO_PERSONAS_COMIDA = "numero_personas"
        const val COL_PICANTE_COMIDA = "picante"
        //tabla ingrediente
        const val USR_TABLE_NAME_INGREDIENTE = "ingrediente"
        const val COL_ID_INGREDIENTE = "id"
        const val COL_NOMBRE_INGREDIENTE = "nombre"
        const val COL_CANTIDAD_INGREDIENTE = "cantidad"
        const val COL_DESCRIPCION_PREPARACION_INGREDIENTE = "descripcion"
        const val COL_OPCIONAL_INGREDIENTE = "opcional"
        const val COL_TIPO_INGREDIENTE = "tipo"
        const val COL_NECESITA_REFRIGERACION_INGREDIENTE = "necesita_refrigeracion"
        const val COL_FK_ID_COMIDA_INGREDIENTE = "fk_id_comida"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var createTableSQL = "CREATE TABLE ${Database.USR_TABLE_NAME_COMIDA} " +
                "(" +
                "${Database.COL_ID_COMIDA} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${Database.COL_NOMBRE_COMIDA} VARCHAR(50), " +
                "${Database.COL_DESCRIPCION_COMIDA} VARCHAR(150), " +
                "${Database.COL_NACIONALIDAD_COMIDA} VARCHAR(50), " +
                "${Database.COL_NUMERO_PERSONAS_COMIDA} INTEGER, " +
                "${Database.COL_PICANTE_COMIDA} INTEGER" +
                "); "
        db?.execSQL(createTableSQL)
        createTableSQL = "CREATE TABLE ${Database.USR_TABLE_NAME_INGREDIENTE} " +
                "(" +
                "${Database.COL_ID_INGREDIENTE} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${Database.COL_NOMBRE_INGREDIENTE} VARCHAR(50), " +
                "${Database.COL_CANTIDAD_INGREDIENTE} INTEGER, " +
                "${Database.COL_DESCRIPCION_PREPARACION_INGREDIENTE} VARCHAR(200), " +
                "${Database.COL_OPCIONAL_INGREDIENTE} INTEGER, " +
                "${Database.COL_TIPO_INGREDIENTE} VARCHAR(50), " +
                "${Database.COL_NECESITA_REFRIGERACION_INGREDIENTE} INTEGER, " +
                "${Database.COL_FK_ID_COMIDA_INGREDIENTE} INTEGER, " +
                "FOREIGN KEY(${Database.COL_FK_ID_COMIDA_INGREDIENTE}) REFERENCES ${Database.USR_TABLE_NAME_COMIDA}(${Database.COL_ID_COMIDA})" +
                ");"
        db?.execSQL(createTableSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + Database.USR_TABLE_NAME_INGREDIENTE)
        db!!.execSQL("DROP TABLE IF EXISTS " + Database.USR_TABLE_NAME_COMIDA)
    }

}

